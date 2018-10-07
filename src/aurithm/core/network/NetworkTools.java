package aurithm.core.network;

import java.io.Serializable;

public class NetworkTools
        implements Serializable, Cloneable {
    public static int[] createArrayWithZero(int size) {
        if(size < 1){
            return null;
        }
        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = 0;
        }
        return arr;
    }

    public static int[][] createArrayWithZero(int sizeX, int sizeY){
        if(sizeX < 1 || sizeY < 1){
            return null;
        }
        int[][] arr = new int[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++){
            arr[i] = createArrayWithZero(sizeY);
        }
        return arr;
    }

    public static int randomValue(double lower_bound, double upper_bound){
        return Math.round((float) (Math.random()*(upper_bound-lower_bound) + lower_bound));
    }
}
