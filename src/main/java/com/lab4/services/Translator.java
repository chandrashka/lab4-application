package com.lab4.services;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class Translator {

    public String writeResult(double angle, Locale locale, double scalMult) {
        String result;
        if (locale != null) {
            String tempLocale = locale.toString();
            if (tempLocale.toLowerCase().equals("ukrainian") || tempLocale.toLowerCase().equals("ukraine")) {
                if (angle == -100) {
                    result = "Некоректний ввод, неможливо провести розрахунки";
                } else if (scalMult != 0 && Math.ceil(angle) != 90) {
                    result = "Пряма перетинає поверхню під кутом: " + angle;
                } else if (scalMult != 0) {
                    result = "Пряма перпендикулярна поверхні";
                } else {
                    result = "Пряма паралельна поверхні або лежить в ній";
                }
            } else {
                if (angle == -100) {
                    result = "Bad input, calculations are not possible";
                } else if (scalMult != 0 && Math.ceil(angle) != 90) {
                    result = "A line cross over the surface at an angle: " + angle;
                } else if (scalMult != 0) {
                    result = "A line perpendicular to the surface";
                } else {
                    result = "A line is parallel to the surface or belongs to it";
                }
            }
        } else {
            if (angle == -100) {
                result = "Bad input, calculations are not possible";
            } else if (scalMult != 0 && Math.ceil(angle) != 90) {
                result = "A line cross over the surface at an angle: " + angle;
            } else if (scalMult != 0) {
                result = "A line perpendicular to the surface";
            } else {
                result = "A line is parallel to the surface or belongs to it";
            }
        }
        return result;
    }
}
