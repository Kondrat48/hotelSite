package ua.training.controller.utils;

import ua.training.controller.command.CommandEnum;
import ua.training.model.service.resourceManager.PagePathManager;

import java.util.Iterator;
import java.util.Map;

public class PathBuilderUtil {

    public String buildPath(CommandEnum command){
        return buildPath(command, null);
    }

    public String buildPath(CommandEnum command, Map<String,Object> params){
        return buildPathWithParams("/app/" + command.name().toLowerCase(), null);
    }

    public String buildPath(String pagePathProperty){
        return buildPath(pagePathProperty, null);
    }

    public String buildPath(String pagePathProperty, Map<String,Object> params){
        return buildPathWithParams(new PagePathManager().getProperty(pagePathProperty), params);
    }
    private String buildPathWithParams(String path, Map<String,Object> params){
        StringBuilder builder = new StringBuilder();
        builder.append(path);
        if(params!=null&&params.size()>0){
            builder.append('?');
            Iterator<String> iterator = params.keySet().iterator();
            String temp;
            while (iterator.hasNext()){
                temp = iterator.next();
                builder.append(temp).append('=').append(params.get(temp));
                if(iterator.hasNext()){
                    builder.append('&');
                }
            }
        }
        return builder.toString();
    }
}
