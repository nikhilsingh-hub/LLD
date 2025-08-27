package DesignPatterns.Singleton.FileConfig;

public class FileBasedConfigurationManagerImpl extends FileBasedConfigurationManager {
    private static volatile FileBasedConfigurationManagerImpl singletonInstance;
    private FileBasedConfigurationManagerImpl(){
        super();
    }

    @Override
    public String getConfiguration(String key) {
       return properties.getProperty(key);
    }

    @Override
    public <T> T getConfiguration(String key, Class<T> type) {
        String value = properties.getProperty(key);
        if(value == null)  return null;
        return convert(value, type);
    }

    @Override
    public void setConfiguration(String key, String value) {
       properties.setProperty(key, value);
    }

    @Override
    public <T> void setConfiguration(String key, T value) {
        if (value == null) {
            properties.remove(key);
        } else {
            properties.setProperty(key, value.toString());
        }
    }

    @Override
    public void removeConfiguration(String key) {
        properties.remove(key);
    }

    @Override
    public void clear() {
        properties.clear();
    }

    public static FileBasedConfigurationManager getInstance() {
        if(singletonInstance == null){
            synchronized (FileBasedConfigurationManagerImpl.class){
                if(singletonInstance == null){
                    singletonInstance = new FileBasedConfigurationManagerImpl();
                }
            }
        }
        return singletonInstance;
    }

    public static void resetInstance() {
        singletonInstance = null;
    }

}
