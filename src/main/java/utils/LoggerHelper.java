package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {
    static PropertyUtils appProperty = new PropertyUtils();
    private static boolean root = false;

    public static Logger getLogger(Class cls) {
        if (root) {
            return Logger.getLogger(cls);
        }
        PropertyConfigurator.configure(appProperty.getPropertyValue("log4jpath"));
        root = true;
        return Logger.getLogger(cls);
    }
}

