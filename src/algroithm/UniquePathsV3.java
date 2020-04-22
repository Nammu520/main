package algroithm;

public class UniquePathsV3 {

    int n = 0;
    int m = 0;
    int b[][] = {{0,-1},{0,1},{1,0},{-1,0}};
    int sx=-1,sy=-1,ex=-1,ey=-1;
    int grid[][];
    int all = 0;
    int result = 0;
    boolean a[][];
    int now = 1;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        a = new boolean[n][m];
        for(int i=0;i < n; i++){
            for(int j= 0 ;j < m ;j++){
                if(grid[i][j] == 1){
                    sx =i;sy =j;
                    all++;
                }else if(grid[i][j] == 2){
                    ex = i;ey = j;
                    all++;
                }else if(grid[i][j] == -1){
                    a[i][j] = true;
                }else {
                    all++;
                }
            }
        }
        if(sx == -1 || sy == -1 || ex == -1 || ey == -1){
            return 0;
        }
        a[sx][sy] = true;
        String path = "(" + sx + "," + sy + ")" + "->";
        DFS(sx,sy,path);
        return result;
    }

    public void DFS(int x,int y,String path){
        System.out.println("x=" + x +";y="+ y + ";now=" + now + ";all=" + all + ";path:   " + path.substring(0,path.length() - 2));
        if(x == ex && y == ey && now == all){
            System.out.println(path.substring(0,path.length() - 2));
            result++;
            return;
        }
        if(x == ex && y == ey && now != all){
            return;
        }
        for(int i=0;i < 4;i++){
            int nx = x + b[i][0];
            int ny = y + b[i][1];
            if(checkGrid(nx,ny)){
                System.out.println(b[i][0] + "  " + b[i][1] + " " + x + " " + y);
                now++;
                a[nx][ny] = true;
                String point = "(" + nx + "," +ny + ")" + "->";
                path = path + point;
                DFS(nx,ny,path);
                a[nx][ny] = false;
                path = path.substring(0,path.length()-7);
                now--;
            }
        }

    }

    public boolean checkGrid(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == -1 || a[x][y]){
            return false;
        }
        return true;
    }
}
