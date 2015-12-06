/**
 * Created by whr on 12/5/15.
 */
public class Search2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        return binarySearch(matrix, 0, matrix[0].length-1, target);
    }
    public static boolean binarySearch(int[][] matrix, int row, int col, int target) {
        if(row >= matrix.length || col < 0) return false;
        if(matrix[row][col] == target) return true;
        if(matrix[row][col] > target) return binarySearch(matrix, row, col - 1, target);
        else return binarySearch(matrix, row + 1, col, target);
    }
}
