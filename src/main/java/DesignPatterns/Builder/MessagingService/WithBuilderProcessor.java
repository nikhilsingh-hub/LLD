package DesignPatterns.Builder.MessagingService;

import DesignPatterns.Builder.WithBuilder;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("DesignPatterns.Builder.WithBuilder")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class WithBuilderProcessor extends AbstractProcessor {
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotationType : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotationType);
            
            for (Element element : annotatedElements) {
                if (element.getKind() == ElementKind.CLASS) {
                    TypeElement classElement = (TypeElement) element;
                    WithBuilder annotation = classElement.getAnnotation(WithBuilder.class);
                    
                    if (annotation != null) {
                        try {
                            generateBuilder(classElement, annotation);
                        } catch (IOException e) {
                            processingEnv.getMessager().printMessage(
                                Diagnostic.Kind.ERROR, 
                                "Failed to generate builder: " + e.getMessage(), 
                                element
                            );
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private void generateBuilder(TypeElement classElement, WithBuilder annotation) throws IOException {
        String className = classElement.getSimpleName().toString();
        String packageName = getPackageName(classElement);
        String builderClassName = annotation.builderClassName().isEmpty() ? 
            className + "Builder" : annotation.builderClassName();
        String builderPackage = annotation.builderPackage().isEmpty() ? 
            packageName : annotation.builderPackage();
        
        // Get all fields from the class
        List<VariableElement> fields = getFields(classElement);
        
        // Generate the builder class
        JavaFileObject builderFile = processingEnv.getFiler()
            .createSourceFile(builderPackage + "." + builderClassName);
        
        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
            generateBuilderCode(out, packageName, className, builderClassName, fields, annotation);
        }
    }
    
    private void generateBuilderCode(PrintWriter out, String packageName, String className, 
                                   String builderClassName, List<VariableElement> fields, 
                                   WithBuilder annotation) {
        
        // Package declaration
        if (!packageName.isEmpty()) {
            out.println("package " + packageName + ";");
            out.println();
        }
        
        // Import statements
        out.println("import java.util.Objects;");
        out.println();
        
        // Class declaration
        out.println("public class " + builderClassName + " {");
        out.println();
        
        // Private fields
        for (VariableElement field : fields) {
            String fieldName = field.getSimpleName().toString();
            String fieldType = field.asType().toString();
            out.println("    private " + fieldType + " " + fieldName + ";");
        }
        out.println();
        
        // Builder methods
        for (VariableElement field : fields) {
            String fieldName = field.getSimpleName().toString();
            String fieldType = field.asType().toString();
            String capitalizedFieldName = capitalize(fieldName);
            
            out.println("    public " + builderClassName + " " + fieldName + "(" + fieldType + " " + fieldName + ") {");
            out.println("        this." + fieldName + " = " + fieldName + ";");
            out.println("        return this;");
            out.println("    }");
            out.println();
        }
        
        // Build method
        out.println("    public " + className + " build() {");
        
        // Validation
        if (annotation.validateRequired()) {
            String[] requiredFields = annotation.requiredFields();
            if (requiredFields.length > 0) {
                for (String requiredField : requiredFields) {
                    out.println("        if (" + requiredField + " == null) {");
                    out.println("            throw new IllegalStateException(\"" + requiredField + " is required\");");
                    out.println("        }");
                }
            } else {
                // Validate all fields by default
                for (VariableElement field : fields) {
                    String fieldName = field.getSimpleName().toString();
                    out.println("        if (" + fieldName + " == null) {");
                    out.println("            throw new IllegalStateException(\"" + fieldName + " is required\");");
                    out.println("        }");
                }
            }
            out.println();
        }
        
        // Constructor call
        out.print("        return new " + className + "(");
        for (int i = 0; i < fields.size(); i++) {
            if (i > 0) out.print(", ");
            out.print(fields.get(i).getSimpleName());
        }
        out.println(");");
        out.println("    }");
        out.println();
        
        // Static factory method
        out.println("    public static " + builderClassName + " builder() {");
        out.println("        return new " + builderClassName + "();");
        out.println("    }");
        out.println();
        
        // Close class
        out.println("}");
    }
    
    private String getPackageName(TypeElement classElement) {
        Element enclosingElement = classElement.getEnclosingElement();
        if (enclosingElement.getKind() == ElementKind.PACKAGE) {
            return ((PackageElement) enclosingElement).getQualifiedName().toString();
        }
        return "";
    }
    
    private List<VariableElement> getFields(TypeElement classElement) {
        return classElement.getEnclosedElements().stream()
            .filter(element -> element.getKind() == ElementKind.FIELD)
            .map(element -> (VariableElement) element)
            .toList();
    }
    
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
} 