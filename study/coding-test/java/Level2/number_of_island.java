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

class Solution {
    private Integer island;
    private boolean[][] visited = new boolean[][];

    public int dfs(char[][] grid, int row, int columns) {
        
        if (visited[row][columns] != true) {
            visited[row][columns] = true;
            
            if (grid[row][columns] == '1') {
                row++;
                dfs(grid, row, columns);
            } else {
                columns++;
                dfs(grid, row, columns);
            }
        } else {
            
        }

    }

    public int numIslands(char[][] grid) {
        int row;
        int columns;

        if (grid == null) return 0;
        
        dfs(grid, row, columns);
    }
}
