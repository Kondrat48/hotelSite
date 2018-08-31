package ua.training.controller.utils;

import ua.training.model.service.resourceManager.MessageManager;
import ua.training.model.service.resourceManager.RegexManager;
import ua.training.model.service.resourceManager.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

public class RegexpUtil {
    private ResourceManager messageManager,
            regexManager;

    public RegexpUtil(Locale locale) {
        messageManager = new MessageManager(locale);
        regexManager = new RegexManager();
    }

    public void validate(Map<String,String> errorMap, String parameter, String regexProperty, String parameterName){
        if(parameter!=null&&!parameter.matches((String) regexManager.getProperty(regexProperty))){
            errorMap.put("wrong"+parameterName, (String) messageManager.getProperty("wrong."+parameterName));
        }
    }
}
