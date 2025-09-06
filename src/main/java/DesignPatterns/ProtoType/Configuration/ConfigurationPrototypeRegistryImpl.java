package DesignPatterns.ProtoType.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationPrototypeRegistryImpl implements ConfigurationPrototypeRegistry{
    Map<ConfigurationType, Configuration> register = new HashMap<>();

    @Override
    public void addPrototype(Configuration user) {
        if(user == null) throw new IllegalArgumentException("error");
        Boolean isAvailable = Arrays.stream(ConfigurationType.values())
                .anyMatch(type -> type.name() == user.getType().name());
        if(!isAvailable) throw new IllegalArgumentException("error");
        register.put(user.getType(), user);
    }

    @Override
    public Configuration getPrototype(ConfigurationType type) {
        if(!register.containsKey(type)) throw new IllegalArgumentException("Type does not exist");
        return register.get(type);
    }

    @Override
    public Configuration clone(ConfigurationType type) {
        if(!register.containsKey(type)) throw new IllegalArgumentException("Type does not exist");
        return register.get(type).cloneObject();
    }
}
