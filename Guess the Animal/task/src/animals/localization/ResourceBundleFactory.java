package animals.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleFactory {
    private static final ResourceBundle res;

    static {
        String userLanguage = System.getProperty("user.language");
        Locale.setDefault(new Locale(userLanguage));
        String bundleClassName = switch(userLanguage){
            case "en" -> "animals.localization.App";
            case "eo" -> "animals.localization.App_eo";
            default -> throw new RuntimeException("Unknown language: "+userLanguage);
        };
        res = ResourceBundle.getBundle(bundleClassName);
    }
    public static ResourceBundle getResource(){
        return res;
    }
}
