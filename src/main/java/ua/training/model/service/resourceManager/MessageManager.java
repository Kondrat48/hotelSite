package ua.training.model.service.resourceManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager implements ResourceManager {

    ResourceBundle bundle;

    public MessageManager(Locale locale) {
        bundle = ResourceBundle.getBundle("messages",locale);
    }

    @Override
    public String getProperty(String property) {
        return bundle.getString(property);
    }
}
