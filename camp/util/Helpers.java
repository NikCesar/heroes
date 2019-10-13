package util;

import java.util.Random;

public class Helpers {

    public static int getRandomInt(int low, int high){
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }

}
