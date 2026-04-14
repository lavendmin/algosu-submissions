import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    static int N, K, len;
    static Character[][] numbers;
    static BigInteger[] pow36;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] arr = new String[N];
        len = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            len = Math.max(len, arr[i].length());
        }
        K = Integer.parseInt(br.readLine());

        numbers = new Character[N][len];
        for (int i = 0; i < N; i++) {
            String num = arr[i];
            int numLen = num.length();
            for (int j = 0; j < numLen; j++) {
                numbers[i][len - numLen + j] = num.charAt(j);
            }
        }

        // 각 자리 36 거듭제곱 미리 계산
        pow36 = new BigInteger[len];
        pow36[len - 1] = BigInteger.ONE;
        for (int i = len - 2; i >= 0; i--) {
            pow36[i] = pow36[i + 1].multiply(BigInteger.valueOf(36));
        }

        changeNumbers();
        System.out.println(getSum());
    }

    static void changeNumbers() {
        BigInteger[] gain = new BigInteger[36];
        Arrays.fill(gain, BigInteger.ZERO);

        // gain 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < len; j++) {
                if (numbers[i][j] == null) continue;

                int value = toValue(numbers[i][j]);
                int diff = 35 - value;

                gain[value] = gain[value].add(
                    pow36[j].multiply(BigInteger.valueOf(diff))
                );
            }
        }

        // 정렬
        Integer[] order = new Integer[36];
        for (int i = 0; i < 36; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> gain[b].compareTo(gain[a]));

        // K개 고르기
        Set<Character> targetNumbers = new HashSet<>();
        for (int i = 0; i < K; i++) {
            if (gain[order[i]].equals(BigInteger.ZERO)) break;
            targetNumbers.add(toChar(order[i]));
        }

        // Z로 바꾸기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < len; j++) {
                if (numbers[i][j] != null && targetNumbers.contains(numbers[i][j])) {
                    numbers[i][j] = 'Z';
                }
            }
        }
    }

    static String getSum() {
        BigInteger sum = BigInteger.ZERO;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < len; j++) {
                if (numbers[i][j] == null) continue;

                int value = toValue(numbers[i][j]);
                sum = sum.add(pow36[j].multiply(BigInteger.valueOf(value)));
            }
        }

        return toBase36(sum);
    }

    static int toValue(char ch) {
        if ('0' <= ch && ch <= '9') return ch - '0';
        return ch - 'A' + 10;
    }

    static char toChar(int value) {
        if (0 <= value && value <= 9) return (char) ('0' + value);
        return (char) ('A' + value - 10);
    }

    static String toBase36(BigInteger num) {
        if (num.equals(BigInteger.ZERO)) return "0";

        StringBuilder sb = new StringBuilder();
        BigInteger base = BigInteger.valueOf(36);

        while (num.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = num.divideAndRemainder(base);
            sb.append(toChar(divRem[1].intValue()));
            num = divRem[0];
        }

        return sb.reverse().toString();
    }
}