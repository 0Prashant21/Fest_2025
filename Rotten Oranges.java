import java.util.*;

public class RottenOranges {
    static class Cell {
        int x, y, time;
        Cell(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<Cell> q = new LinkedList<>();
        int fresh = 0, time = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) q.add(new Cell(i, j, 0));
                else if (grid[i][j] == 1) fresh++;
            }
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Cell c = q.poll();
            time = c.time;
            for (int k = 0; k < 4; k++) {
                int nx = c.x + dx[k], ny = c.y + dy[k];
                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && grid[nx][ny] == 1) {
                    grid[nx][ny] = 2;
                    fresh--;
                    q.add(new Cell(nx, ny, c.time + 1));
                }
            }
        }

        return fresh == 0 ? time : -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {2,1,1},
            {1,1,0},
            {0,1,1}
        };
        System.out.println(orangesRotting(grid));
    }
}
