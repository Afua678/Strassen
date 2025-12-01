package Strassen;

import java.util.Scanner;
import java.lang.Math;

public class MatrixMult {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your n?");
        int num = scanner.nextInt();
        System.out.println("Starting experiment for n = " + num);

        int[][] matrixA = makeMatrix(num);
        int[][] matrixB = makeMatrix(num);

        for (int i = 5; i < 10; i++) { // testing when to break
            int time = mult(matrixA, matrixB, i);

        }
    }

    public static int[][] makeMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
                System.out.println(matrix[i][j]);
            }
        }
        return matrix;
    }

    public static int mult(int[][] A, int[][] B, int i) { // for this we are going to multiply our matrix by itself
        long start = System.currentTimeMillis();

        if (a.length < Math.pow(2, i)) {
            bruteMult(A, B);
        }

        // <---- STRASSEN TIME!!! ---->

        // Split into submatrices
        int[][] a00 = splitTopLeft(A);
        int[][] a01 = splitTopRight(A);
        int[][] a10 = splitBottomLeft(A);
        int[][] a11 = splitBottomRight(A);

        int[][] b00 = splitTopLeft(B);
        int[][] b01 = splitTopRight(B);
        int[][] b10 = splitBottomLeft(B);
        int[][] b11 = splitBottomRight(B);

        // Recursively computing m values
        int[][] m1 = strassen(add(a00, a11), add(b00, b11));
        int[][] m2 = strassen(add(a10, a11), b00);
        int[][] m3 = strassen(a00, sub(b01, b11));
        int[][] m4 = strassen(a11, sub(b10, b00));
        int[][] m5 = strassen(add(a00, a01), b11);
        int[][] m6 = strassen(sub(a10, a00), add(b00, b01));
        int[][] m7 = strassen(sub(a01, a11), add(b10, b11));

        // Combining m values for matrix C
        int[][] c00 = add(m1, add(m7, sub(m4, m5)));
        int[][] c01 = add(m3, m5);
        int[][] c10 = add(m2, m4);
        int[][] c11 = add(m1, add(m6, sub(m3, m2)));
        int[][] C = join(c00, c01, c10, c11);

        // Method ends after obtaining final product matrix C
        long end = System.currentTimeMillis();
        return (double) (end - start / 1000);
    }

    private static int[][] splitTopLeft(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[i][j];
                System.out.println(n[i][j]);
            }
        }
        return n;

    }

    private static int[][] splitTopRight(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[i][n.length + j];
                System.out.println(n[i][j]);
            }
        }
        return n;

    }

    private static int[][] splitBottomLeft(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[n.length + i][j];
                System.out.println(n[i][j]);
            }
        }

        return n;
    }

    private static int[][] splitBottomRight(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[n.length + i][n.length + j];
                System.out.println(n[i][j]);
            }
        }
        return n;
    }

    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    private static int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    private static int[][] join(int[][] c00, int[][] c01, int[][] c10, int[][] c11) {
        int n = c00.length;
        int[][] C = new int[2 * n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = c00[i][j];
                C[i][n + j] = c01[i][j];
                C[n + i][j] = c10[i][j];
                C[n + i][n + j] = c11[i][j];
            }
        }

        return C;
    }

}
