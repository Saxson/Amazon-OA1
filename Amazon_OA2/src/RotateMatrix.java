/**
 * Created by whr on 12/5/15.
 */
public class RotateMatrix {
    public static int[][] rotateMatrix(int[][] matrix, int flag) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return matrix;
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (flag == 1) {//right rotate,clockwise
                    res[j][m - i - 1] = matrix[i][j];
                } else {//left rotate
                    res[n - j - 1][i] = matrix[i][j];
                }
            }
        }
        return res;
    }

//    public static void print(int[][] matrix) {
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    public static void main(String[] args) {
//        int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
//        //int[][] resRight = rotateMatrix(matrix, 1);
//        int[][] resLeft = rotateMatrix(matrix, 0);
//        //print(resRight);
//        print(resLeft);
//    }
}
