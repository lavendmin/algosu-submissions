import java.util.*;
import java.io.*;

class Main {
    static int n, m;
    static int[][] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(arr[i], Integer.MAX_VALUE);
            arr[i][i] = 0;    
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a][b] = Math.min(arr[a][b], c);
        }

        floydWarshall();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = arr[i][j] == Integer.MAX_VALUE ? 0 : arr[i][j];
                sb.append(cost).append(' ');
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void floydWarshall() {
        for (int k = 1; k <= n; k++) { // k : 경유지
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (arr[i][k] == Integer.MAX_VALUE || arr[k][j] == Integer.MAX_VALUE) continue;
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
    }
}