package com.example.digitalgrouptest.models;

import com.example.digitalgrouptest.tools.Constraints;

import java.util.ArrayList;

/**
 * Created by Andrei on 07.07.2018.
 */

public class DataGenerator {

    public static ArrayList<Integer> makeNumbersList(int digits, int size) {
        int maxSize = (int)(Math.pow(10, digits) - Math.pow(10, digits-1));
        if (size > maxSize) {
            size = maxSize;
        }
        ArrayList<Integer> result = new ArrayList<>(size);
        int start = (int)Math.round(Math.pow(10, digits-1));
        for (int i = 0; i < size; i++) {
            result.add(start+i);
        }
        return result;
    }

    public static ArrayList<String> makeStringsList(int length, int size) {
        if (size > 28) { //trim to alphabet size
            size = 28;
        }
        ArrayList<String> result = new ArrayList<>(size);
        Character start = 'A';
        for (int i = start; i < start+size; i++) {
            char c = (char) i;
            StringBuilder resultString = new StringBuilder("");
            for (int j = 0; j < length; j++) { //making string like "BBBB" (char='B', length=4)
                resultString = resultString.append(c);
            }
            result.add(resultString.toString());
        }

        return result;
    }

    public static ArrayList<String> makeCategoriesList() { //Select numbers or strings
        ArrayList<String> categories = new ArrayList<>();
        categories.add(Constraints.CATEGORY_NUMBERS);
        categories.add(Constraints.CATEGORY_STRINGS);
        return categories;
    }

    public static ArrayList<Integer> makeSubcategoriesList() { //Select length of numbers/strings
        ArrayList<Integer> subcategories = new ArrayList<>();
        for (int i = 1; i < Constraints.MAX_ITEM_LENGTH+1; i++) {
            subcategories.add(i);
        }
        return subcategories;
    }
}
