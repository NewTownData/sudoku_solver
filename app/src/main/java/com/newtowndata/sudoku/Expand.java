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
public class Expand {

	public static final HashSet<Integer> FULL_SET = new HashSet<Integer>(9);

	static {
		for (int i = 1; i <= 9; i++) {
			FULL_SET.add(i);
		}
	}

	public static HashSet<Integer> getAvailableNumbers(int[][] matrix, int row, int col) {
		HashSet<Integer> avail = new HashSet<Integer>(FULL_SET);

		removeLineNumbersFromSet(avail, matrix[row]);
		removeLineNumbersFromSet(avail, Check.getMatrixLine(matrix, col));
		removeLineNumbersFromSet(avail,
				Check.getMatrixSquare(matrix, Check.getMatrixSquareNumber(row, col)));

		return avail;
	}

	public static void removeLineNumbersFromSet(HashSet<Integer> set, int[] line) {
		for (int x : line) {
			set.remove(x);
		}
	}

}
