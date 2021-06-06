/**
 * Copyright 2021 Voyta Krizek, https://github.com/NewTownData
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.newtowndata.sudoku;

import java.util.HashSet;

/**
 *
 * @author Voyta Krizek, https://github.com/NewTownData
 */
public class Check {

	public static boolean checkVector(int[] vector) {
		HashSet<Integer> used = new HashSet<Integer>(9);

		for (int x : vector) {
			if (x == 0) {
				continue;
			}

			if (used.contains(x)) {
				return false;
			} else {
				used.add(x);
			}
		}

		return true;
	}

	public static boolean checkMatrix(int[][] matrix) {
		for (int i = 0; i < 9; i++) {
			if (!checkVector(matrix[i])) {
				return false;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (!checkVector(getMatrixLine(matrix, i))) {
				return false;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (!checkVector(getMatrixSquare(matrix, i))) {
				return false;
			}
		}


		return true;
	}

	public static int[] getMatrixLine(int[][] matrix, int num) {
		int[] line = new int[9];
		for (int i = 0; i < line.length; i++) {
			line[i] = matrix[i][num];
		}

		return line;
	}

	public static int getMatrixSquareNumber(int row, int col) {
		return (((int) (col / 3)) * 3) + (row / 3);
	}

	public static int[] getMatrixSquareRowCol(int num) {
		return new int[] {((int) (num % 3)) * 3, // row
				((int) (num / 3)) * 3 // col
		};
	}

	public static int[] getMatrixSquare(int[][] matrix, int num) {
		int[] xy = getMatrixSquareRowCol(num);
		int row = xy[0];
		int col = xy[1];

		int[] line = new int[9];
		int pos = 0;
		for (int i = row; i < row + 3; i++) {
			for (int j = col; j < col + 3; j++) {
				line[pos++] = matrix[i][j];
			}
		}

		return line;
	}
}
