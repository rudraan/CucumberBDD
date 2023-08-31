package awesomecucumber.utils;

import awesomecucumber.constants.EnvType;
import awesomecucumber.domainobjects.Product;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader; //this is made static so that it retains its value

    private ConfigLoader() { //private constructor means no other class can instantiate this class,this ensures one instance

        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch(EnvType.valueOf(env)){
            case PROD:
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            case STAGE:
                properties = PropertyUtils.propertyLoader("src/test/resources/stage_config.properties");
                break;
            default:
                throw new IllegalStateException("INVALID ENV: " +env);
        }

    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null) return prop;
        else
            throw new RuntimeException("property baseUrl is not specified " +
                    "in the stg_config.properties");
    }
}
