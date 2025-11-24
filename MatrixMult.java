package Strassen;

import java.util.Scanner;

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

}
