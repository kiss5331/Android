package com.dalkomsoft02.ganggongui.todayfeed;

import toast.library.meal.MealLibrary;

/**
 * Created by ganggongui on 14. 12. 15..
 */
public class FoodparserThread implements Runnable {


    public static String[] dateNew;

    public static String[] FoodNew;

    public static String CODE = "";


    @Override
    public void run() {


        dateNew = MealLibrary.getDateNew("sen.go.kr", CODE, "4", "04", "2");
        FoodNew = MealLibrary.getMealNew("sen.go.kr", CODE, "4", "04", "2");


    }
}
