package Strassen;

import java.util.Scanner;
import java.lang.Math;

public class MatrixMult {
    public static void main(String[] args) {
        System.out.println("What is your n?");
        int num = scanner.nextInt();
        System.out.println("Starting experiment for n = " + num);

        int[][] strass = makeMatrix(num);

        for (int i = 5; i < 10; i++) { // testing when to break
            int time = mult(strass, i);

        }
    }

    public static int[][] makeMatrix(int n) {
        int[][] strass = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                strass[i][j] = (int) Math.random() * 10;
            }
        }
        return strass;
    }

    public static int[][] mult(int[][] s, int i) { // for this we are going to multiply our matrix by itself
        int time = 0;
        int[][] a = splitTopLeft(s);
        int[][] b = splitTopRight(s);
        int[][] c = splitBottomLeft(s);
        int[][] d = splitBottomRight(s);

        if (a.length < Math.pow(2, i)) {
            // do regular multiplication
            // and return it
        } else {
            // do strassen multiplication
        }

        return mult(a) + mult(b) + mult(c) + mult(d); // returns time
    }

}
