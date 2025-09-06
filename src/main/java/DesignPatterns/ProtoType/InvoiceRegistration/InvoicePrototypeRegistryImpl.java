package DesignPatterns.ProtoType.InvoiceRegistration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InvoicePrototypeRegistryImpl implements InvoicePrototypeRegistry{
    Map<InvoiceType, Invoice> register = new HashMap<>();

    @Override
    public void addPrototype(Invoice user) {
        if(user == null) throw new IllegalArgumentException("Null Invoice Provided");
        if(user.getType()==null) throw new IllegalArgumentException("Invalid Invoice Type Provided");
        
        // Check if the type exists as a valid enum value
        boolean isValidType = Arrays.stream(InvoiceType.values())
                .anyMatch(type -> type == user.getType());
        if(!isValidType) throw new IllegalArgumentException("Invoice Type does not exist as enum: " + user.getType());
        
        register.put(user.getType(), user);
    }

    @Override
    public Invoice getPrototype(InvoiceType type) {
        if(!register.containsKey(type)) throw new IllegalArgumentException("Type does not exist");
        return register.get(type);
    }

    @Override
    public Invoice clone(InvoiceType type) {
        if(!register.containsKey(type)) throw new IllegalArgumentException("Type does not exist");
        return register.get(type).cloneObject();
    }
}
