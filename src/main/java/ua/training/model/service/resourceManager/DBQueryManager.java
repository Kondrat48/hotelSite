package ua.training.model.service.resourceManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBQueryManager implements ResourceManager<String> {

    private final ResourceBundle bundle;

    public DBQueryManager() {
        bundle = ResourceBundle.getBundle("sql_query");
    }

    @Override
    public String getProperty(String property) {
        return bundle.getString(property);
    }
}