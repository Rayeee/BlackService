package me.zgy.utils;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Map;
import java.util.Properties;

/**
 * Created by Rayee on 2017/12/29.
 */
public class ConfigUtils extends PropertyPlaceholderConfigurer {

    private static final Map<String, Object> propertiesMap = Maps.newHashMap();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString().trim();
            propertiesMap.put(keyStr, props.getProperty(keyStr).toString().trim());
        }
    }

    public static String getString(String key) {
        return propertiesMap.get(key).toString();
    }

    public static Integer getInt(String key) {
        return Integer.valueOf(propertiesMap.get(key).toString());
    }

    public static Long getLong(String key) {
        return Long.valueOf(propertiesMap.get(key).toString());
    }
}
