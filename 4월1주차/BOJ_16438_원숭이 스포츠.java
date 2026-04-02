import java.util.*;
import java.io.*;

class Main {
    static int N;
    static char[][] answer;
    static int len;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());    

        answer = new char[7][N];
        for (int i = 0; i < 7; i++) {
            Arrays.fill(answer[i], 'A');
        }
        
        char[] arr = new char[N];
        solution(0, N - 1, 0);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(String.valueOf(answer[i])).append('\n');
        }

        String firstStr = String.valueOf(answer[0]);
        for (int i = 0; i < 7 - len; i++) {
            sb.append(firstStr).append('\n');
        }
        
        bw.write(sb.toString());
        bw.flush();
    }

    static void solution(int left, int right, int depth) { 
        len = Math.max(len, depth);
        
        if (left == right) {
            return;
        }   
        
        int mid = (left + right) / 2;
        
        for (int i = left; i <= mid; i++) {
            answer[depth][i] = 'A';
        }
        
        for (int i = mid + 1; i <= right; i++) {
            answer[depth][i] = 'B';
        }       
        
        solution(left, mid, depth + 1);
        solution(mid + 1, right, depth + 1);

    }
}