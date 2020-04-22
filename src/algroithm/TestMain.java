package algroithm;

public class TestMain {
    public static void main(String[] args) {
        int a[][] = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        UniquePathsV3 u = new UniquePathsV3();
        int result = u.uniquePathsIII(a);
        System.out.println(result);
    }
}
