import java.util.Scanner;

class Dot {
    double x, y;

    public Dot(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class Main_2166 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int N = sc.nextInt();           // N각형
        Dot[] dots = new Dot[N];
        for (int n = 0; n < N; n++){
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            dots[n] = new Dot(x, y);
        }

        // 신발끈 공식
        // | (x1*y2 + x2*y3 + ... + xn-1*yn + xn*y1) - (y1*x2 + y2*x3 + ... + yn-1*xn + yn*x1)| /2
        double a = 0;
        double b = 0;
        for (int i = 0; i < N; i++){
            Dot first = dots[i];
            Dot second;
            if (i == N-1)
                second = dots[0];
            else
                second = dots[i+1];
            a += first.x * second.y;
            b += first.y * second.x;
        }

        double result = Math.abs(a-b)/2.0;
        System.out.printf("%.1f", Math.round(result*10)/10.0);
    }
}
