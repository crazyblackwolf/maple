package com.zjf.common.constant;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: linziye
 * @description:
 * @date: 15:34 2017/12/21 .
 */
public class SystemConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfig.class);
    private static final String DEFAULT_CONFIG = PropertiesConstant.COMMON_CONFIG;
    private static Properties config = null;

    public SystemConfig() {
        this(DEFAULT_CONFIG);
    }

    public SystemConfig(String prop) {
        init(prop);
    }

    private static void init(String prop) {
        if (config == null) {
            config = new Properties();
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            try {
                config.load(contextClassLoader.getResourceAsStream(prop));
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public static String getValue(String key) {
        if (config == null) {
            init(DEFAULT_CONFIG);
        }
        return config.containsKey(key) ? config.getProperty(key) : null;
    }

    public static Integer getInt(String key) {
        String value = getValue(key);
        return StringUtils.isBlank(value) ? null : Integer.valueOf(value);
    }
}
