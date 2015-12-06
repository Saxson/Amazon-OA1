/**
 * Created by whr on 12/4/15.
 */
public class MaximumMinimumPath {
    public static int getMaxMin(int[][] matrix) {
        int[] res = {Integer.MIN_VALUE};
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res[0];//看测试用例定返回值
        dfs(matrix, 0, 0, Integer.MAX_VALUE, res);
        return res[0];
    }
    public static void dfs(int[][] matrix, int i, int j, int minPath, int[] res) {
        if(i == matrix.length || j == matrix[0].length) return;
        minPath = Math.min(minPath, matrix[i][j]);
        if(i == matrix.length - 1 && j == matrix[0].length - 1){
            res[0] = Math.max(res[0], minPath);
        }
        dfs(matrix, i + 1, j, minPath, res);
        dfs(matrix, i, j + 1, minPath, res);
    }

    //dp 方法2
    public static int helper(int[][] matrix){
        int[] result = new int[matrix[0].length];
        result[0] = matrix[0][0];
        for(int i=1; i<matrix[0].length; i++){
            result[i] = Math.min(result[i-1], matrix[0][i]);
        }
        for(int i=1; i<matrix.length; i++){
            result[0] = Math.min(matrix[i][0], result[0]);
            for(int j=1; j<matrix[0].length; j++){
                result[j] = (result[j]<matrix[i][j] && result[j-1]<matrix[i][j])?Math.max(result[j-1], result[j]):matrix[i][j];
            }
        }
        return result[result.length-1];
    }

    public static void main(String[] args) {
        int[][] matrix = {{8,4,7},{6,5,9}};
        System.out.println(helper(matrix));
    }
}
