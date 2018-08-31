package ua.training.model.service.resourceManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBColumnManager implements ResourceManager<String> {
    private final ResourceBundle bundle;

    public DBColumnManager(Locale locale) {
        bundle = ResourceBundle.getBundle("db_column_names",locale);
    }
    public DBColumnManager() {
        bundle = ResourceBundle.getBundle("db_column_names");
    }

    @Override
    public String getProperty(String property) {
        return bundle.getString(property);
    }
}
