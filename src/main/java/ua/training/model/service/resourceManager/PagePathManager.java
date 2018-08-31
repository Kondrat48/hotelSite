package ua.training.model.service.resourceManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class PagePathManager implements ResourceManager<String> {
    ResourceBundle bundle;

    public PagePathManager() {
        bundle = ResourceBundle.getBundle("path");
    }

    @Override
    public String getProperty(String property) {
        return bundle.getString(property);
    }
}
