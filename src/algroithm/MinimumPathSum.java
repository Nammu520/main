package algroithm;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] resolve = init(grid);
        for(int i = 1; i< grid.length;i++){
            for (int  j = 1; j< grid[0].length;j++){
                resolve[i][j] = Math.min(resolve[i-1][j], resolve[i][j-1]) + grid[i][j];
            }
        }
        return resolve[grid.length - 1][grid[0].length - 1];
    }

    private int[][] init(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int resolve[][] = new int[grid.length - 1][grid[0].length - 1];
        resolve[0][0] = grid[0][0];
        for(int i=1; i < n ;i++){
            resolve[i][0] =resolve[i-1][0] + grid[i][0];
        }
        for(int j=1; j< m; j++){
            resolve[0][j] = resolve[0][j-1] + grid[0][j];
        }
        return resolve;
    }
}
