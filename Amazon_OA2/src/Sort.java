/**
 * Created by whr on 12/4/15.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
class Point{
    public int x;
    public int y;
    public Point(int _x, int _y) {
        x = _x;
        y = _y;
    }
}
public class Sort {
    public static void main(String[] args) {
        Point[] points =  {new Point(1,2), new Point(0,0), new Point(3,2)};
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.x, o2.x); //升序，小根堆
            }
        });

        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(2,3,1,5,4));
        Collections.sort(nums, new Comparator<Integer>() {//默认升序排列
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });

    }
}
