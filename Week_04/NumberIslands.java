/**
 * Created by yuping on 2020/7/17.
 */
public class NumberIslands {

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        //根据row 和 col开始遍历岛屿数量
        int row = grid.length;
        int col = grid[0].length;

        int numIslands = 0;
        for (int r = 0; r < row; r++) {
            for (int c=0; c < col; c++) {
                if (grid[r][c] == '1') {
                    ++ numIslands;
                    //开始遍历,标记为其他颜色
                    dfs(grid, r, c);
                }
            }
        }

        return numIslands;


    }

    public void dfs(char[][] grid, int r, int c) {
        //结束标治
        if (!inArea(grid,r,c) || grid[r][c] != '1') { //不是陆地就结束了,超出了边界也要结束
            return ;
        }
        //把岛屿标治为其他颜色
        grid[r][c] = 2;
        //访问上下边界
        dfs(grid, r-1, c);
        dfs(grid, r+1, c);
        dfs(grid, r, c-1);
        dfs(grid, r, c+1);

    }

    private boolean inArea(char[][] grid, int r, int c) {
        return 0 <=r && r < grid.length && 0 <= c && c < grid[0].length ;
    }


}
