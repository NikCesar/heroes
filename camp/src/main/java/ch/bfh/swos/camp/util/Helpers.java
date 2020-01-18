package ch.bfh.swos.camp.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Helpers {

    public static List<Integer> getDistinctRandomInts(int low, int high, int numberOfInts){
        List<Integer> range = IntStream.range(low, high).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        return range.subList(0, numberOfInts);
    }

    public static int getRandomInt(int low, int high){
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }

    public static double getRandomDouble(double low, double high) {
        Random r = new Random();
        double randDouble = (low + (high - low) * r.nextDouble());
        return round(randDouble, 2);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
/*
    public static double getChanceAsDouble() {
        return getChanceAsDouble(1, 100);
    }

    public static double getChanceAsDouble(int low, int high) {
        int randomInt = getRandomInt(low, high);
        try {
            String afterCommaDoubleString = "0." + randomInt;
            return Double.valueOf(afterCommaDoubleString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.1;
        }
    }
*/
    // returns "true" if at least one input param is either null or empty
    public static boolean isNullOrEmpty(String... str){
        for (String s : str)
        {
            if(s == null || s.isEmpty()){
                return true;
            }
        }
        return false;
    }


}
