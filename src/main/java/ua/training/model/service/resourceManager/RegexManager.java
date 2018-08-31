package ua.training.model.service.resourceManager;

import java.util.ResourceBundle;

public class RegexManager implements ResourceManager {
    private ResourceBundle bundle;

    public RegexManager() {
        bundle = ResourceBundle.getBundle("regexp");
    }

    @Override
    public String getProperty(String property) {
        return bundle.getString(property);
    }
}
