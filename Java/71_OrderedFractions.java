import java.util.Scanner;

public class Main {

    // multiply two unsigned numbers where the result may exceed the largest native data type
    public static void multiply(long a, long b, long[] result) {
        final int SHIFT = 4 * Long.SIZE / 8;
        final long MASK = (~0L) >>> SHIFT;

        long aHigh = a >>> SHIFT;
        long aLow = a & MASK;
        long bHigh = b >>> SHIFT;
        long bLow = b & MASK;

        long c0 = aLow * bLow;
        long c1a = aHigh * bLow;
        long c1b = aLow * bHigh;
        long c2 = aHigh * bHigh;

        long carry = ((c0 >>> SHIFT) + (c1a & MASK) + (c1b & MASK)) >>> SHIFT;

        result[0] = c2 + (c1a >>> SHIFT) + (c1b >>> SHIFT) + carry; // resultHigh
        result[1] = c0 + (c1a << SHIFT) + (c1b << SHIFT); // resultLow
    }

    // if a/b < c/d (and all numbers are positive)
    // then a*d < c*b
    public static boolean isLess(long a, long b, long c, long d) {
        long[] r1 = new long[2];
        long[] r2 = new long[2];
        multiply(a, d, r1);
        multiply(c, b, r2);
        // compare high bits
        if (r1[0] < r2[0]) {
            return true;
        }
        if (r1[0] > r2[0]) {
            return false;
        }
        // compare low bits
        return r1[1] < r2[1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tests = 1;
        tests = scanner.nextInt();
        while (tests-- > 0) {
            int a = 3;
            int b = 7;
            long limit = 1000000;
            a = scanner.nextInt();
            b = scanner.nextInt();
            limit = scanner.nextLong();

            // generate all numbers in the Farey sequence using binary subdivision
            // for each step decide whether the number was left or right of the desired fraction
            // start with 0/1 and 1/1
            long leftN = 0;
            long leftD = 1;
            long rightN = 1;
            long rightD = 1;
            while (leftD + rightD <= limit) {
                long mediantN = leftN + rightN;
                long mediantD = leftD + rightD;

                // if x1/y1 < x2/y2 (and all numbers are positive)
                // then x1*y2 < x2*y1
                if (isLess(mediantN, mediantD, a, b)) {
                    // adjust left border
                    leftN = mediantN;
                    leftD = mediantD;
                } else {
                    // adjust right border
                    rightN = mediantN;
                    rightD = mediantD;
                    // did we "hit the spot" ?
                    if (rightN == a && rightD == b)
                        break;
                }
            }

            // now the right border is the fraction we read from input
            // and we only have to adjust the left border from here on

            // "instant" result
            if (limit >= leftD + rightD) {
                long difference = limit - (leftD + rightD);
                long repeat = 1 + difference / rightD;
                leftN += repeat * rightN;
                leftD += repeat * rightD;
            }

            System.out.println(leftN + " " + leftD);
        }
        scanner.close();
    }
}
