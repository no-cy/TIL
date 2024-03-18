// Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
// You may assume all four edges of the grid are all surrounded by water.

// Example 1:
/* Input: grid = [
   ["1","1","1","1","0"],
   ["1","1","0","1","0"],
   ["1","1","0","0","0"],
   ["0","0","0","0","0"]
 ]
Output: 1 
*/

// Example 2:
/* Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/

// Constraints:
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] is '0' or '1'.

// 개선 해야될 점:
// visited를 사용하지 않고 체크하는 방법을 구현해보아야 함
class Solution {
    public Integer island = 0;
    public boolean visited[][];

    public int numIslands(char[][] grid) {
        Integer row = 0, column = 0;
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;

        visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // System.out.println("i: " + i + " j: " + j);
                island += dfs(grid, i, j);
            }
        }

        return island;
    }       

    public Integer dfs(char[][] grid, Integer row, Integer column) {
        // System.out.println("row: " + row + " column: " + column);
        if (row < 0 ||
            column < 0 ||
            row == grid.length ||
            column == grid[row].length ||
            grid[row][column] == '0' ) {
                return 0;
            }

        if (visited[row][column] == true) {
            return 0;
        }

        visited[row][column] = true;
        dfs(grid, row-1, column);
        dfs(grid, row+1, column);
        dfs(grid, row, column-1);
        dfs(grid, row, column+1);
        
        return 1;
    }
} 


