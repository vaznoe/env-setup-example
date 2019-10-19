package com.vaznoe.example.config;

import java.util.Properties;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EnvironmentPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String environment;

    public EnvironmentPropertyPlaceholderConfigurer(String environment) {
        this.environment = environment;
    }

    protected String resolvePlaceholder(String placeholder, Properties props) {
        if (this.environment != null) {
            String value = props.getProperty(this.environment + "." + placeholder);
            if (value != null) {
                return value;
            }
        }
        return super.resolvePlaceholder(placeholder, props);
    }
}