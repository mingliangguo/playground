import java.util.*;

public class LocalMax {
  public List<Integer> localMax(int[][] matrix)  {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
      return new ArrayList<>();

    int M = matrix.length, N = matrix[0].length;
    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        int val = matrix[i][j];
        if ((i - 1 < 0 || val > matrix[i - 1][j])
            && (j - 1 < 0 || val > matrix[i][j - 1])
            && (i - 1 < 0 || j - 1 < 0 || val > matrix[i - 1][j - 1])
            && (i - 1 < 0 || j + 1 >= N || val > matrix[i - 1][j + 1])
            && (i + 1 >= M || val > matrix[i + 1][j])
            && (j + 1 >= N || val > matrix[i][j + 1])
            && (i + 1 >= M || j + 1 >= N || val > matrix[i + 1][j + 1])
            && (i + 1 >= M || j - 1 < 0 || val > matrix[i + 1][j - 1])) {
          ans.add(val);
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[][] matrix = new int[][] {
      {7, 49, 73, 58, 30},
        {72, 44, 78, 23, 9},
        {40, 65, 92, 42, 87},
        {3, 27, 29, 40, 12},
    };
    LocalMax lm = new LocalMax();
    System.out.println(lm.localMax(matrix));
  }
}
