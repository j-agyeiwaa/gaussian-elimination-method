def main():
     n = int(input("Enter the number of equations (n): "))

     matrix = [[0.0] * (n + 1) for _ in range(n)]

     print("\nEnter the coefficients and constants for each equation:")
     for row in range(n):
          print(f"Equation {row + 1}:")
          for col in range(n + 1):
               if col < n:
                    prompt = f"  Coefficient for x{col + 1} (position [{row}][{col}]): "
               else:
                    prompt = f"  Constant term (position [{row}][{col}]): "
               matrix[row][col] = float(input(prompt))

     for pivot in range(n):
          if matrix[pivot][pivot] == 0:
               for r in range(pivot + 1, n):
                    if matrix[r][pivot] != 0:
                         matrix[pivot], matrix[r] = matrix[r], matrix[pivot]
                         break

          if matrix[pivot][pivot] == 0:
               continue

          for row in range(pivot + 1, n):
               factor = matrix[row][pivot] / matrix[pivot][pivot]
               for col in range(pivot, n + 1):
                    matrix[row][col] -= factor * matrix[pivot][col]

     print("\nUpper-Triangular Matrix:")
     for row in range(n):
          row_str = ""
          for col in range(n + 1):
               if col == n:
                    row_str += "| "
               row_str += f"{matrix[row][col]} "
          print(row_str.strip())
     no_solution = False
     infinite_solutions = False

     for row in range(n):
          all_zeros = all(matrix[row][col] == 0 for col in range(n))
          if all_zeros and matrix[row][n] != 0:
               no_solution = True
               break
          elif all_zeros and matrix[row][n] == 0:
               infinite_solutions = True

     print("\nResult:")
     if no_solution:
          print("No solution.")
     elif infinite_solutions:
          print("Infinite solutions.")
     else:
          solution = [0.0] * n
          for row in range(n - 1, -1, -1):
               total = sum(matrix[row][col] * solution[col] for col in range(row + 1, n))
               solution[row] = (matrix[row][n] - total) / matrix[row][row]

          for i in range(n):
               print(f"x{i + 1} = {solution[i]}")


if __name__ == "__main__":
     main()