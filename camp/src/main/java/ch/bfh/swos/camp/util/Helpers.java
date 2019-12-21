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

    public static double getChanceAsDouble() {
        int randomInt = getRandomInt(1, 100);
        try {
            String aftercommaDoubleString = "0." + randomInt;
            return Double.valueOf(aftercommaDoubleString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.1;
        }
    }

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
