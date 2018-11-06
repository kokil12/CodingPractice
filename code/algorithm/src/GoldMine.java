import java.util.Arrays;
import java.util.Map;

public class GoldMine {
    public static void main(String[] args) {
        int input[][] = {{1,3,1,5},{2,2,4,1},{5,0,2,3},{0,6,1,2}};

        int m = 4, n = 4;

        System.out.print(getMaxGold(input, m, n));
    }

    private static int getMaxGold(int[][] input, int m, int n) {
        int intermediate[][] = new int[4][4];

        for (int[] rows : intermediate) {
            Arrays.fill(rows, 0);
        }

        for (int col=n-1;col>=0;col--) {
            for (int row=0;row<m;row++) {
                int right = (col==n-1) ? 0 : intermediate[row][col+1];
                int rightUp = (row==0 || col==n-1) ? 0 : intermediate[row-1][col+1];
                int rightDown = (row==m-1 || col==n-1) ? 0 : intermediate[row+1][col+1];
                intermediate[row][col] = input[row][col] + Math.max(right, Math.max(rightUp, rightDown));
            }
        }

        int result = intermediate[0][0];
        for (int row = 1;row<m;row++) {
            if (result<intermediate[row][0])
                result = intermediate[row][0];
        }
        return result;
    }
}
