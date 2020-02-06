package com.pilvo.apiServices.util;

import com.pilvo.apiServices.globalVariable.Constant;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

public class Util {
    private static Properties properties;
    public static ObjectMapper objectMapper;

    public static String getEnvironment() {
        Constant.ENV = System.getProperty("env");
        if (Constant.ENV != null) {
            if (Constant.ENV.equalsIgnoreCase("qa")) {
                return "qa.properties";
            } else if (Constant.ENV.equalsIgnoreCase("prod")) return "prod.properties";
        }
        return "local.properties";
    }

    synchronized public static Properties loadProperty(String path) throws Exception {
        if (properties == null) {
            properties = new Properties();
            properties.load(new FileInputStream(path));
            return properties;
        }
        return properties;
    }

    public static Map<String, String> readAllPropertiesStartsWith(String propertyKey) {
        System.out.println("*** Enter in readAllPropertyValueStartsWith Function *** ");
        HashMap<String, String> prop = new HashMap<String, String>();
        properties.forEach((key, value) -> prop.put(key.toString(), value.toString()));
        Map<String, String> propmap = prop.entrySet()
                .stream().filter(p -> p.getKey().startsWith(propertyKey))
                .collect(Collectors.toMap(map -> map.getKey().replace(propertyKey + "_", ""), map -> map.getValue()));
        return propmap;
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            return new ObjectMapper().configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        }
        return objectMapper;
    }

    public static int RandomGenerate(int count) {
        return new Random().nextInt(count);
    }
}
