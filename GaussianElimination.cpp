#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
     int n;
     cout << "Enter the number of equations (n): ";
     cin >> n;
 
     vector<vector<double>> matrix(n, vector<double>(n + 1));

     cout << "\nEnter the coefficients and constants for each equation:\n";
     for (int row = 0; row < n; row++) {
          cout << "Equation " << (row + 1) << ":\n";
          for (int col = 0; col <= n; col++) {
               if (col < n) {
                    cout << "  Coefficient for x" << (col + 1) << " (position [" << row << "][" << col << "]): ";
               } else {
                    cout << "  Constant term (position [" << row << "][" << col << "]): ";
               }
               cin >> matrix[row][col];
          }
     }

     for (int pivot = 0; pivot < n; pivot++) {
          if (matrix[pivot][pivot] == 0) {
               for (int r = pivot + 1; r < n; r++) {
                    if (matrix[r][pivot] != 0) {
                         swap(matrix[pivot], matrix[r]);
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
                    matrix[row][col] -= factor * matrix[pivot][col];
               }
          }
     }

     cout << "\nUpper-Triangular Matrix:\n";
     for (int row = 0; row < n; row++) {
          for (int col = 0; col <= n; col++) {
               if (col == n) {
                    cout << "| ";
               }
               cout << matrix[row][col] << " ";
          }
          cout << "\n";
     }

     bool noSolution = false;
     bool infiniteSolutions = false;

     for (int row = 0; row < n; row++) {
          bool allZeros = true;
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

     cout << "\nResult:\n";
     if (noSolution) {
          cout << "No solution.\n";
     } else if (infiniteSolutions) {
          cout << "Infinite solutions.\n";
     } else {
          vector<double> solution(n);
          for (int row = n - 1; row >= 0; row--) {
               double sum = 0;
               for (int col = row + 1; col < n; col++) {
                    sum += matrix[row][col] * solution[col];
               }
               solution[row] = (matrix[row][n] - sum) / matrix[row][row];
          }

          for (int i = 0; i < n; i++) {
               cout << "x" << (i + 1) << " = " << solution[i] << "\n";
          }
     }

     return 0;
}