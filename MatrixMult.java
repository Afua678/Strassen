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

        long start = System.currentTimeMillis();
        mult(matrixA, matrixB, 4);
        long end = System.currentTimeMillis();
        double breakTime = (double)(end - start / 1000);
        int minBreak = 4; 

        for (int i = 5; i < 10; i++) { // testing when to break
            start = System.currentTimeMillis();
            mult(matrixA, matrixB, i);
            end = System.currentTimeMillis();
            double time = (double)(end - start / 1000);
            if (time < breakTime) minBreak = i;
            System.out.println("Time for breakover 2^" + i + ": " + time + " seconds");
        }

        System.out.println("The optimal breakover is 2^" + minBreak);

        scanner.close();
    }

    public static int[][] makeMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    public static int[][] mult(int[][] A, int[][] B, int i) { // for this we are going to multiply our matrix by itself
        
        if (A.length < Math.pow(2, i)) {
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
        int[][] m1 = mult(add(a00, a11), add(b00, b11), i);
        int[][] m2 = mult(add(a10, a11), b00, i);
        int[][] m3 = mult(a00, sub(b01,b11), i);
        int[][] m4 = mult(a11, sub(b10, b00), i);
        int[][] m5 = mult(add(a00, a01), b11, i);
        int[][] m6 = mult(sub(a10, a00), add(b00, b01), i);
        int[][] m7 = mult(sub(a01, a11), add(b10, b11), i);
        
        // Combining m values for matrix C
        int[][] c00 = add(m1,add(m7, sub(m4, m5)));
        int[][] c01 = add(m3, m5);
        int[][] c10 = add(m2, m4);
        int[][] c11 = add(m1, add(m6, sub(m3, m2)));
        int[][] C = join(c00,c01, c10, c11);
        // Method ends after obtaining final product matrix C

        return C;
        
    }

    private static int[][] bruteMult(int[][] A, int[][] B) {
        int n = A.length;
        int[][] product = new int[n][n];

        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < product.length; j++) {
                for (int k = 0; k < product.length; k++) {
                    product[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return product;
    }
    
    private static int[][] splitTopLeft(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[i][j];
            }
        }
        return n;

    }

    private static int[][] splitTopRight(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[i][n.length + j];
            }
        }
        return n;

    }

    private static int[][] splitBottomLeft(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[n.length + i][j];
            }
        }

        return n;
    }

    private static int[][] splitBottomRight(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[n.length + i][n.length + j];
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
        int[][] C = new int[2*n][2*n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = c00[i][j];
                C[i][n+j] = c01[i][j];
                C[n+i][j] = c10[i][j];
                C[n+i][n+j] = c11[i][j];
            }
        }

        return C;
    }

}
