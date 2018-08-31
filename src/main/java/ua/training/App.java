package ua.training;

import ua.training.model.entity.User;
import ua.training.model.service.resourceManager.RegexManager;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {

//        long val = 123456789;
//        DecimalFormat format = new DecimalFormat("0000000,00");
//        System.out.println(format.format(val));
//        System.out.println(val/100d);

        Pattern pattern = Pattern.compile("[A-Za-z]{0,45}");
    }
}


