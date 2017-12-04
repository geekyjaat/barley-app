package com.barley.process;

import com.barley.util.Utilities;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanNumerals {

    private static RomanNumerals theInstance;

    private RomanNumerals() {
        romanList.add(0, new Roman(1000, "M"));
        romanList.add(1, new Roman(900, "CM"));
        romanList.add(2, new Roman(500, "D"));
        romanList.add(3, new Roman(400, "CD"));
        romanList.add(4, new Roman(100, "C"));
        romanList.add(5, new Roman(90, "XC"));
        romanList.add(6, new Roman(50, "L"));
        romanList.add(7, new Roman(40, "XL"));
        romanList.add(8, new Roman(10, "X"));
        romanList.add(9, new Roman(9, "IX"));
        romanList.add(10, new Roman(5, "V"));
        romanList.add(11, new Roman(4, "IV"));
        romanList.add(12, new Roman(1, "I"));
    }

    public static RomanNumerals getInstance() {
        if (theInstance == null) {
            theInstance = new RomanNumerals();
            return theInstance;
        } else {
            return theInstance;
        }
    }

    private List<Roman> romanList = new ArrayList<>(13);

    public static Object handleGet(Request request, Response response) {
        Map roman = getInstance().getRomanMap();
        roman.put("display", "none");
        roman.put("placeholder", "123 or XII");
        return Utilities.render(roman, "velocity/roman.vm");
    }

    public static Object handlePost(Request request, Response response) {
        String input = request.queryParams("input");
        System.out.println("Input: " + input);
        Map roman = getInstance().getRomanMap();
        if (input != null && input.length() != 0) {
            try {
                Integer.parseInt(input);
                roman.put("answer", getInstance().toRoman(Integer.parseInt(input)));
                roman.put("success", "alert-success");
            } catch (NumberFormatException nfe) {
                roman.put("answer", getInstance().toNumber(input));
                roman.put("success", "alert-success");
            } catch (Exception e) {
                roman.put("success", "alert-danger");
                roman.put("answer", e.getMessage());
            }
            roman.put("display", "");
            roman.put("placeholder", input);
        }
        return Utilities.render(roman, "velocity/roman.vm");
    }

    private Map getRomanMap() {
        // roman
        Map<String, Object> roman = new HashMap<>();
        roman.put("title", "RomanNumerals Numerals");
        roman.put("text", "To convert number to RomanNumerals Numeral & vice versa, enter the value and click applicable button.");
        roman.put("placeholder", "123 or XII");
        roman.put("success", "alert-success");
        roman.put("display", "none");
        roman.put("answer", "");
        return roman;
    }

    public String toRoman(int value) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (value > 0) {
            if (value >= romanList.get(i).number) {
                int repeat = value / romanList.get(i).number;
                for (int j = 0; j < repeat; j++) {
                    sb.append(romanList.get(i).symbol);
                }
                value %= romanList.get(i).number;
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    public int toNumber(String roman) {
        int result = 0;
        int i = 0;
        while (i < roman.length()) {
            // try + 2 length string first
            if (i + 2 <= roman.length()) {
                String str = roman.substring(i, i + 2);
                int value = getValue(str);
                if (value == 0) {
                    str = roman.substring(i, i + 1);
                    value = getValue(str);
                    i++;
                } else {
                    i += 2;
                }
                result += value;
            } else {
                String str = roman.substring(i, i + 1);
                result += getValue(str);
                i++;
            }
        }
        return result;
    }


    private int getValue(String str) {
        int result = 0;
        switch (str) {
            case "M": {
                result = 1000;
                break;
            }
            case "CM": {
                result = 900;
                break;
            }
            case "D": {
                result = 500;
                break;
            }
            case "CD": {
                result = 400;
                break;
            }
            case "C": {
                result = 100;
                break;
            }
            case "XC": {
                result = 90;
                break;
            }
            case "L": {
                result = 50;
                break;
            }
            case "XL": {
                result = 40;
                break;
            }
            case "X": {
                result = 10;
                break;
            }
            case "IX": {
                result = 9;
                break;
            }
            case "V": {
                result = 5;
                break;
            }
            case "IV": {
                result = 4;
                break;
            }
            case "I": {
                result = 1;
                break;
            }
        }
        return result;
    }

    private static class Roman {
        int number;
        String symbol;

        public Roman(int number, String symbol) {
            this.number = number;
            this.symbol = symbol;
        }
    }
}
