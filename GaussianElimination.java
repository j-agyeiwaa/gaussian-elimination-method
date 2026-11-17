import java.util.Scanner;

public class GaussianElimination {
     public static void main(String[] args) {
          Scanner input = new Scanner(System.in);

          System.out.print("Enter the number of equations (n): ");
          int n = input.nextInt();

          double[][] matrix = new double[n][n + 1];

          System.out.println("\nEnter the coefficients and constants for each equation:");
          for (int row = 0; row < n; row++) {
               System.out.println("Equation " + (row + 1) + ":");
               for (int col = 0; col <= n; col++) {
                    if (col < n) {
                         System.out.print("  Coefficient for x" + (col + 1) + " (position [" + row + "][" + col + "]): ");
                    } else {
                         System.out.print("  Constant term (position [" + row + "][" + col + "]): ");
                    }
                    matrix[row][col] = input.nextDouble();
               }
          }

          for (int pivot = 0; pivot < n; pivot++) {
               if (matrix[pivot][pivot] == 0) {
                    for (int r = pivot + 1; r < n; r++) {
                         if (matrix[r][pivot] != 0) {
                         double[] temp = matrix[pivot];
                         matrix[pivot] = matrix[r];
                         matrix[r] = temp;
                         break;
                         }
                    }
               }

               if (matrix[pivot][pivot] == 0) {
                    continue;
               }

               for (int row = pivot + 1; row < n; row++) {
                    double factor = matrix[row][pivot] / matrix[pivot][pivot];
                    for (int col = pivot; col <= n; col++) {
                         matrix[row][col] = matrix[row][col] - (factor * matrix[pivot][col]);
                    }
               }
          }

          System.out.println("\nUpper-Triangular Matrix:");
          for (int row = 0; row < n; row++) {
               for (int col = 0; col <= n; col++) {
                    if (col == n) {
                         System.out.print("| ");
                    }
                    System.out.print(matrix[row][col] + " ");
               }
               System.out.println();
          }

          boolean noSolution = false;
          boolean infiniteSolutions = false;

          for (int row = 0; row < n; row++) {
               boolean allZeros = true;
               for (int col = 0; col < n; col++) {
                    if (matrix[row][col] != 0) {
                         allZeros = false;
                         break;
                    }
               }

               if (allZeros && matrix[row][n] != 0) {
                    noSolution = true;
                    break;
               } else if (allZeros && matrix[row][n] == 0) {
                    infiniteSolutions = true;
               }
          }

          System.out.println("\nResult:");
          if (noSolution) {
               System.out.println("No solution.");
          } else if (infiniteSolutions) {
               System.out.println("Infinite solutions.");
          } else {
               double[] solution = new double[n];

               for (int row = n - 1; row >= 0; row--) {
                    double sum = 0;
                    for (int col = row + 1; col < n; col++) {
                         sum += matrix[row][col] * solution[col];
                    }
                    solution[row] = (matrix[row][n] - sum) / matrix[row][row];
               }

               for (int i = 0; i < n; i++) {
                    System.out.println("x" + (i + 1) + " = " + solution[i]);
               }
          }

          input.close();
     }
}