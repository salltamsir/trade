package org.ibanfirst.trade.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleUtil {
    private static final String BUNDLE_BASE_NAME = "resourcebundle.resources";
    private ResourceBundleUtil(){}

    public static String getStringValue(String key){
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME);

        try {
            return bundle.getString(key);
        } catch (MissingResourceException ex){
            return "";
        }
    }
}
