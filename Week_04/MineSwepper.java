import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yuping on 2020/7/18.
 */
public class MineSwepper {


    public char[][] updateBoard(char[][] board, int[] click) {
        //init
        int m = board.length;
        int n = board[0].length;

        //bfs
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            if (board[row][col] == 'M') { // 如果是地雷，则设置成 X ，并且结束搜索
                board[row][col] = 'X';
                break;
            } else {
                // 扫描节点 (row, col) 八个方向有多少个地雷,如果地雷数大于0，显示地雷数
                // {-1,-1}
                int count = 0;
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue; //当前点不做啥
                        int r = row + i;
                        int c = col + j;
                        if (r < 0 || r >= m || c < 0 || c >= n) continue; //边界判断
                        if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                    }
                }
                if (count > 0) {
                    // 如果有地雷的话，则停止搜索
                    // 并且将节点 (row, col) 的值设置为地雷的数量
                    board[row][col] = (char)(count + '0');
                } else {
                    // 继续 BFS ，从找到的空白白板继续开始搜索
                    board[row][col] = 'B';
                    // 搜索节点 (row, col) 八个方向
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i == 0 && j == 0) continue;
                            int r = row + i;
                            int c = col + j;
                            if (r < 0 || r >= m || c < 0 || c >= n) continue;
                            if (board[r][c] == 'E') {
                                queue.add(new int[]{r, c});
                                board[r][c] = 'B';
                            }
                        }
                    }
                }
            }
        }
        return board;
    }


    public char[][] updateBoardWithDFS(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int row = click[0], col = click[1];

        if (board[row][col] == 'M') { // Mine
            board[row][col] = 'X';
        }
        else { // Empty
            // Get number of mines first.
            int count = 0;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) continue;
                    int r = row + i, c = col + j;
                    if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                    if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                }
            }

            if (count > 0) { // If it is not a 'B', stop further DFS.
                board[row][col] = (char)(count + '0');
            }
            else { // Continue DFS to adjacent cells.
                board[row][col] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = row + i, c = col + j;
                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
                        if (board[r][c] == 'E') updateBoard(board, new int[] {r, c});
                    }
                }
            }
        }

        return board;
    }


}
