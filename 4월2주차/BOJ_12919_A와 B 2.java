import java.util.*;
import java.io.*;

class Main {
    static String S, T;
    static StringBuilder sb;
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        sb = new StringBuilder(T);
        solution();
        
        System.out.println(answer);
    }

    static void solution() {
        if (sb.length() == S.length()) {
            if (sb.toString().equals(S)) answer = 1;
            return;
        }

        boolean isPossibleOpA = sb.toString().charAt(sb.length() - 1) == 'A';
        boolean isPossibleOpB = sb.toString().charAt(0) == 'B';

        if (!isPossibleOpA && !isPossibleOpB) return;
        
        if (isPossibleOpA && isPossibleOpB) {
            sb.deleteCharAt(sb.length() - 1);
            solution();
            sb.append("A");

            sb.deleteCharAt(0).reverse();
            solution();
            sb.append("B").reverse();
        } else if (isPossibleOpA && !isPossibleOpB) {
            sb.deleteCharAt(sb.length() - 1);
            solution();
            sb.append("A");
        } else if (!isPossibleOpA && isPossibleOpB) {
            sb.deleteCharAt(0).reverse();
            solution();
            sb.append("B").reverse();
        }
    }

}