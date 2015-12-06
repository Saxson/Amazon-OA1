/**
 * Created by whr on 12/2/15.
 */

public class RectangleOverlap {
    class Point{
        public double x,y;
        public Point(double _x, double _y) {
            x = _x;
            y = _y;
        }
    }
    public static boolean isOverlap(Point A, Point B, Point C, Point D) {
        if(B.x <= C.x || B.y >= C.y || A.x >= D.x || A.y <= D.y) return false;
        else return true;
    }
}
