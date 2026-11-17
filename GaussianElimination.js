const readline = require('readline/promises');
const { stdin: input, stdout: output } = require('process');

async function main() {
     const rl = readline.createInterface({ input, output });

     const n = parseInt(await rl.question('Enter the number of equations (n): '), 10);

     const matrix = Array.from({ length: n }, () => Array(n + 1).fill(0));

     console.log('\nEnter the coefficients and constants for each equation:');
     for (let row = 0; row < n; row++) {
          console.log(`Equation ${row + 1}:`);
          for (let col = 0; col <= n; col++) {
               const promptText = col < n
                    ? `  Coefficient for x${col + 1} (position [${row}][${col}]): `
                    : `  Constant term (position [${row}][${col}]): `;
               matrix[row][col] = parseFloat(await rl.question(promptText));
          }
     }

     for (let pivot = 0; pivot < n; pivot++) {
          if (matrix[pivot][pivot] === 0) {
               for (let r = pivot + 1; r < n; r++) {
                    if (matrix[r][pivot] !== 0) {
                         [matrix[pivot], matrix[r]] = [matrix[r], matrix[pivot]];
                         break;
                    }
               }
          }

          if (matrix[pivot][pivot] === 0) {
               continue;
          }

          for (let row = pivot + 1; row < n; row++) {
               const factor = matrix[row][pivot] / matrix[pivot][pivot];
               for (let col = pivot; col <= n; col++) {
                    matrix[row][col] -= factor * matrix[pivot][col];
               }
          }
     }

     console.log('\nUpper-Triangular Matrix:');
     for (let row = 0; row < n; row++) {
          let line = '';
          for (let col = 0; col <= n; col++) {
               if (col === n) line += '| ';
               line += matrix[row][col] + ' ';
          }
          console.log(line.trim());
     }

     let noSolution = false;
     let infiniteSolutions = false;

     for (let row = 0; row < n; row++) {
          let allZeros = true;
          for (let col = 0; col < n; col++) {
               if (matrix[row][col] !== 0) {
                    allZeros = false;
                    break;
               }
          }

          if (allZeros && matrix[row][n] !== 0) {
               noSolution = true;
               break;
          } else if (allZeros && matrix[row][n] === 0) {
               infiniteSolutions = true;
          }
     }

     console.log('\nResult:');
     if (noSolution) {
          console.log('No solution.');
     } else if (infiniteSolutions) {
          console.log('Infinite solutions.');
     } else {
          const solution = new Array(n).fill(0);
          for (let row = n - 1; row >= 0; row--) {
               let sum = 0;
               for (let col = row + 1; col < n; col++) {
                    sum += matrix[row][col] * solution[col];
               }
               solution[row] = (matrix[row][n] - sum) / matrix[row][row];
          }

          for (let i = 0; i < n; i++) {
               console.log(`x${i + 1} = ${solution[i]}`);
          }
     }

     rl.close();
}

main();