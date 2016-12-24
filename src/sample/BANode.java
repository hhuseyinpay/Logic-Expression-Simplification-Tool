package sample;

/**
 * Created by Hasan Hüseyin Pay on 6.12.2016.
 */
public class BANode {

    private int A;
    private int B;
    private int C;
    private int D;
    private int sum;

    public BANode(int a, int b) {
        A = a;
        B = b;
        C = 2;
        D = 2;
        sum = a + b;
    }

    public BANode(int a, int b, int c) {
        A = a;
        B = b;
        C = c;
        D = 2;
        sum = a + b + c;
    }

    public BANode(int a, int b, int c, int d) {
        A = a;
        B = b;
        C = c;
        D = d;
        sum = a + b + c + d;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    public int getC() {
        return C;
    }

    public int getD() {
        return D;
    }

    public void removeA() {
        if (A != 2) {
            sum -= A;
            A = 2;
        } else {
            System.out.println("2. removeA!");
            return;
        }
    }

    public void removeB() {
        if (B != 2) {
            sum -= B;
            B = 2;
        } else {
            System.out.println("2. removeB!");
            return;
        }
    }

    public void removeC() {
        if (C != 2) {
            sum -= C;
            C = 2;
        } else {
            System.out.println("2. removeC!");
            return;
        }
    }

    public void removeD() {
        if (D != 2) {
            sum -= D;
            D = 2;
        } else {
            System.out.println("2. removeD!");
            return;
        }
    }

    public int getSum() {
        return sum;
    }
}
