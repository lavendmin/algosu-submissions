import java.util.*;

class Solution {
    static int N, M;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        int[] start = new int[2];
        int[] lever = new int[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (maps[i].charAt(j) == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }
            }
        }
        
        int startToLever = bfs(maps, start[0], start[1], 'L') ;
        int leverToExit = bfs(maps, lever[0], lever[1], 'E');
        
        if (startToLever == -1 || leverToExit == -1) return -1;
        
        return startToLever + leverToExit;
    }
    
    static int bfs(String[] maps, int i, int j, char dest) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        queue.add(new int[] {i, j, 0});
        visited[i][j] = true;
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            int time = now[2];
            
            if (maps[r].charAt(c) == dest) return time;
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                    if (maps[nr].charAt(nc) != 'X'&& !visited[nr][nc]) {
                        queue.add(new int[] {nr, nc, time + 1});
                        visited[nr][nc] = true;
                    }
                }
            }
            
        }
        
        return -1;
    }
}