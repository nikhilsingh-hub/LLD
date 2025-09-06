package DesignPatterns.Builder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)  // Changed to SOURCE for compile-time processing
@Target(ElementType.TYPE)
public @interface WithBuilder {
    /**
     * The name of the generated builder class. 
     * If empty, will use the annotated class name + "Builder"
     */
    String builderClassName() default "";
    
    /**
     * Whether to validate required fields before building
     */
    boolean validateRequired() default true;
    
    /**
     * Fields that are required for building the object
     */
    String[] requiredFields() default {};
    
    /**
     * Whether the generated object should be immutable
     */
    boolean immutable() default true;
    
    /**
     * Package for the generated builder class
     */
    String builderPackage() default "";
}
