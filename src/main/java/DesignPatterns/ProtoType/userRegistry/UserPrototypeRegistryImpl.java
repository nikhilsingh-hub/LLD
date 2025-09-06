package DesignPatterns.ProtoType.userRegistry;

import java.util.*;

public class UserPrototypeRegistryImpl implements UserPrototypeRegistry {
    private Map<UserType, User> prototypes = new HashMap<>();

    @Override
    public void addPrototype(User user) {
        if (user == null || user.getType() == null) {
            throw new IllegalArgumentException("User and UserType cannot be null");
        }
        prototypes.put(user.getType(), user);
    }

    @Override
    public User getPrototype(UserType type) {
        if (type == null) {
            throw new IllegalArgumentException("UserType cannot be null");
        }
        return prototypes.get(type);
    }

    @Override
    public User clone(UserType type) {
        if (type == null) {
            throw new IllegalArgumentException("UserType cannot be null");
        }
        User prototype = prototypes.get(type);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype found for type: " + type);
        }
        return prototype.cloneObject();
    }

    public boolean hasPrototype(UserType type) {
        return prototypes.containsKey(type);
    }

    public int getPrototypeCount() {
        return prototypes.size();
    }

    public void clearPrototypes() {
        prototypes.clear();
    }
}
