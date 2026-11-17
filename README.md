# Gaussian Elimination Method

A multi-language implementation of the **Gaussian Elimination Method** for solving systems of linear equations of any dimension (n × n). Available in **Java**, **Python**, **C++**, and **JavaScript**.

---

## Features

* **Dynamic Matrix Dimensions:** Supports any number of equations (n), automatically constructing an n × (n + 1) augmented matrix from user input.
* **Partial Pivoting (Row Swapping):** Automatically detects zero pivots and swaps rows to prevent division-by-zero errors.
* **Forward Elimination:** Reduces the augmented matrix into upper-triangular form.
* **Solution Classification:** Identifies systems with **no solution** or **infinite solutions** before solving.
* **Back-Substitution:** Computes and displays the values of all unknown variables (\(x_1, x_2, \dots, x_n\)).

---

## Project Structure

```text
GaussianEliminationMethod/
├── GaussianElimination.java   # Java implementation
├── GaussianElimination.py     # Python implementation
├── gaussian_elimination.cpp   # C++ implementation
├── gaussianElimination.js     # JavaScript (Node.js) implementation
└── README.md                  # Project documentation
```

---

## How to Run

### 1. Java
```bash
javac GaussianElimination.java
java GaussianElimination
```

### 2. Python
```bash
python GaussianElimination.py
```

### 3. C++
```bash
g++ gaussian_elimination.cpp -o gaussian
./gaussian
```

### 4. JavaScript (Node.js)
```bash
node gaussianElimination.js
```

---

## Example Usage

Given the system of 3 linear equations:
* x₁ + 2x₂ + 3x₃ = 14
* 4x₁ + 5x₂ + 6x₃ = 32
* 7x₁ + 8x₂ + 10x₃ = 53

**Input:**
```text
Enter the number of equations (n): 3
Equation 1: 1, 2, 3 | 14
Equation 2: 4, 5, 6 | 32
Equation 3: 7, 8, 10 | 53
```

**Output:**
```text
Upper-Triangular Matrix:
1.0 2.0 3.0 | 14.0
0.0 -3.0 -6.0 | -24.0
0.0 0.0 1.0 | 3.0

Result:
x1 = 1.0
x2 = 2.0
x3 = 3.0
```