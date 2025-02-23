//
// 공간 O(mn) 사용
//
class Solution {
    public void setZeroes(int[][] matrix) {
        boolean[][] visits = new boolean[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (matrix[row][column] == 0 && visits[row][column] == false) {
                    makeZeroes(matrix, row, column, visits);
                }
            }
        }

        return;
    }

    public void makeZeroes(int[][] matrix, int row, int column, boolean[][] visits)
    {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[row][i] != 0) {
                matrix[row][i] = 0;
                visits[row][i] = true;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][column] != 0) {
                matrix[i][column] = 0;
                visits[i][column] = true;
            }
        }
    }
}

//
// 공간 O(1) 사용
//
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean firstRowZero = false, firstColZero = false;

        // 1. 첫 번째 행과 열이 0을 포함하는지 확인
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) firstColZero = true;
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) firstRowZero = true;
        }

        // 2. 첫 번째 행과 열을 이용하여 0이 있어야 하는 위치 표시
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 3. 첫 번째 행과 열을 기준으로 행/열을 0으로 변경
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 4. 첫 번째 행/열을 0으로 변경
        if (firstRowZero) {
            for (int j = 0; j < n; j++) matrix[0][j] = 0;
        }
        if (firstColZero) {
            for (int i = 0; i < m; i++) matrix[i][0] = 0;
        }
    }
}
