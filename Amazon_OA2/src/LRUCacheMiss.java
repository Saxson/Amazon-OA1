/**
 * Created by whr on 12/5/15.
 */
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
public class LRUCacheMiss {
    public static int countMiss(int[] input, int size) {
        if(input == null || input.length == 0) return 0;
        if(size == 0) return input.length;
        int count = 0;
        List<Integer> cache = new LinkedList<Integer>();
        for(int i = 0; i < input.length; i++) {
            if(cache.contains(input[i])) {
                cache.remove(new Integer(input[i]));
            } else {
                count++;
                if(cache.size() == size) cache.remove(0);
            }
            cache.add(input[i]);
        }
        return count;
    }


    public static int countMiss2(int[] input, int size) {
        if(input == null || input.length == 0) return 0;
        if(size == 0) return input.length;
        int count = 0;
        final int capacity = size;
        LinkedHashMap<Integer, Boolean> cache = new LinkedHashMap<Integer, Boolean>(capacity, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
        for(int num : input) {
            if(cache.get(num) == null) {
                count++;
                cache.put(num, true);
            }
        }
        return count;
    }

//    public static void main(String[] args) {
//        int[] input = {1, 2, 3 ,4, 5, 4, 1};
//        System.out.println(countMiss2(input,4));
//    }
}
