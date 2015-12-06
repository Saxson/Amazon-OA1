/**
 * Created by whr on 12/4/15.
 */
//注意返回类型
public class Maze {
    public static boolean findPath(int[][] maze) {
        if(maze == null || maze.length == 0 || maze[0].length == 0) return false;
        return canFindPath(0, 0, maze);
    }
    public static boolean canFindPath(int i, int j, int[][] maze) {
        if(i < 0 || i >= maze.length || j < 0 || j >= maze[0].length || maze[i][j] == 0) return false;
        if(maze[i][j] == 9) return true;
        maze[i][j] = 0;
        boolean res = canFindPath(i+1,j,maze) || canFindPath(i,j+1,maze) || canFindPath(i-1,j,maze) || canFindPath(i,j-1,maze);
        maze[i][j] = 1;
        return res;
    }
}
