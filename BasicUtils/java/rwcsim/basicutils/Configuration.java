package rwcsim.basicutils;


import java.io.IOException;
import java.util.Properties;

public class Configuration  {
    public static final int CONFIGURATION_ERROR = 1;


    private static Configuration.NullableConfiguration INSTANCE = null;
    protected Properties properties = new Properties();
    public static Configuration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration.NullableConfiguration();
        }
        return INSTANCE;
    }

    public void reloadProperties() {
        try {
            this.properties = this.load("resources/base.properties");
            this.properties.putAll(System.getProperties());
        } catch (Exception e) {
            System.out.println(e);
            System.exit(CONFIGURATION_ERROR);
        }
    }

    private Properties load(String propFileName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties results = new Properties();
        Properties temp = new Properties();
        temp.load(classLoader.getSystemResourceAsStream(propFileName));
        String includes = temp.getProperty("includefiles");
        if (includes != null) {
            String[] var6 = includes.split(",");
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                String file = var6[var8];
                results.putAll(this.load(file.trim()));
            }
        }

        results.putAll(temp);
        return results;
    }


    public static class NullableConfiguration extends Configuration {
        NullableConfiguration() {
        }

        public String getPropertyNullable(String propertyName) {
            return this.properties.getProperty(propertyName);
        }
    }


    public String get(String property) {
        return this.properties.getProperty(property);
    }

    public int getInt(String property) {
        return Integer.parseInt(this.properties.getProperty(property));
    }
    public long getLong(String property) {
        return Long.parseLong(this.properties.getProperty(property));
    }
    public float getFloat(String property) {
        return Float.parseFloat(this.properties.getProperty(property));
    }
    public double getDouble(String property) {
        return Double.parseDouble(this.properties.getProperty(property));
    }
    public boolean getBoolean(String property) {
        return Boolean.getBoolean(this.properties.getProperty(property));
    }
}
