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

    public static double mult(int[][] A, int[][] B, int i) { // for this we are going to multiply our matrix by itself
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



        mult(a);
        mult(b);
        mult(c);
        mult(d); // do the methods
        
        long end = System.currentTimeMillis();
        return (double)(end - start / 1000);
    }
    
    public static int[][] splitTopLeft(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[i][j];
                System.out.println(n[i][j]);
            }
        }
        return n;

    }

    public static int[][] splitTopRight(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[i][n.length + j];
                System.out.println(n[i][j]);
            }
        }
        return n;

    }

    public static int[][] splitBottomLeft(int[][] s) {
        int[][] n = new int[s.length / 2][s.length / 2];

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n.length; j++) {
                n[i][j] = s[n.length + i][j];
                System.out.println(n[i][j]);
            }
        }

        return n;
    }

    public static int[][] splitBottomRight(int[][] s) {
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

    
}
