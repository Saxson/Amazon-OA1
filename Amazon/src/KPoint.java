/**
 * Created by whr on 12/2/15.
 */
import java.util.Comparator;
import java.util.PriorityQueue;


public class KPoint {

    static class Point{
        public double x,y;
        public Point(double _x, double _y) {
            x = _x;
            y = _y;
        }
    }

    public static double getDistance(Point a, Point b) {
        return Math.pow((a.x-b.x),2) + Math.pow((a.y-b.y),2);
    }

    public static Point[] getKPoints(Point[] points, Point origin, int k) {
        if(points == null || points.length < k || k <= 0) return new Point[0];
        Point[] res = new Point[k];
        PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                double distance1 = getDistance(a,origin);
                double distance2 = getDistance(b,origin);
                return Double.compare(distance2,distance1);
            }
        });
        for(int i = 0; i < points.length; i++) {
            if(i < k) {
                maxHeap.offer(points[i]);
            } else {
                if(getDistance(points[i],origin) < getDistance(maxHeap.peek(),origin)) {
                    maxHeap.poll();
                    maxHeap.offer(points[i]);
                }
            }
        }
        int i = 0;
        while(!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        Point[] points = {new Point(2,3),new Point(3,5),new Point(1,0)};
        Point origin = new Point(1,2);
        Point[] res = getKPoints(points,origin,2);
        for(Point p : res) {
            System.out.print(p.x + "," + p.y + " ");
        }
    }

}
