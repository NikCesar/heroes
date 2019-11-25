package ch.bfh.swos.camp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Helpers {

    public static List<Integer> getDistinctRandomInts(int low, int high, int numberOfInts){
        List<Integer> range = IntStream.range(low, high).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        return range.subList(0, numberOfInts-1);
    }


    public static int getRandomInt(int low, int high){
        Random r = new Random();
        return r.nextInt(high-low) + low;
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
