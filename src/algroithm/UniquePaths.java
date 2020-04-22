package algroithm;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int resolve[][] = init(m,n);
        for(int i=1;i < m;i++ ){
            for(int j = 1;j < n; j++){
                resolve[i][j] = resolve[i-1][j] + resolve[i][j-1];
            }
        }
        return resolve[m-1][n-1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int resolve[][] = init_v2(obstacleGrid);
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return 0;
        }
        for(int i=1 ; i< m; i++){
            for(int j = 1 ;j < n ;j++){
                if(obstacleGrid[i-1][j] == 1 && obstacleGrid[i][j-1] == 1){
                    resolve[i][j] = 0;
                }else if(obstacleGrid[i-1][j] == 1 && obstacleGrid[i][j-1] != 1){
                    resolve[i][j] = resolve[i][j-1];
                }else if(obstacleGrid[i-1][j] != 1 && obstacleGrid[i][j-1] == 1){
                    resolve[i][j] = resolve[i-1][j];
                }else {
                    resolve[i][j] = resolve[i-1][j] + resolve[i][j-1];
                }
            }
        }
        return resolve[m-1][n-1];
    }

    private int[][] init_v2(int[][] obstacleGrid){
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int resolve[][] = new int[m][n];
        if(obstacleGrid[0][0] == 1) {
            resolve[0][0] = 0;
        }else{
            resolve[0][0] = 1;
        }
        for(int i=1;i<n;i++) {
            if(obstacleGrid[0][i] != 1){
                resolve[0][i] = 1;
            }else{
                for(int j = i ;i < n ;i++){
                    resolve[0][j] = 0;
                }
                break;
            }
        }

        for(int i=1;i<m;i++) {
            if(obstacleGrid[i][0] != 1){
                resolve[i][0] = 1;
            }else{
                for(int j = i ;i < m ;i++){
                    resolve[j][0] = 0;
                }
                break;
            }
        }
        return resolve;
    }

    private int[][] init(int m,int n){
        int resolve[][] = new int[m][n];
        resolve[0][0] = 1;
        for(int i=0;i<n;i++) resolve[0][i] = 1;
        for(int j=0;j<m;j++) resolve[j][0] = 1;
        return resolve;
    }
}
