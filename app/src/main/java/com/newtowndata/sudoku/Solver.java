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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 *
 * @author Voyta Krizek, https://github.com/NewTownData
 */
public class Solver {

	public static void copyMatrix(int[][] from, int[][] to) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				to[i][j] = from[i][j];
			}
		}
	}

	public static boolean solveIt(int[][] matrix, int[][] newMatrix) {
		ArrayList<SpecClass> solver = new ArrayList<SpecClass>(81);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				newMatrix[i][j] = matrix[i][j];

				if (matrix[i][j] == 0) {
					solver.add(new SpecClass(i, j, Expand.getAvailableNumbers(matrix, i, j)));
				}
			}
		}

		Collections.sort(solver);

		int watchdog = 10000;
		while (solver.size() > 0) {
			SpecClass first = solver.get(0);

			if (first.getSize() == 0) {
				return false;
			} else if (first.getSize() == 1) {
				solver.remove(0);

				newMatrix[first.getRow()][first.getCol()] = first.getFirstInt();
			} else {
				solver.clear();

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (newMatrix[i][j] == 0) {
							solver.add(new SpecClass(i, j, Expand.getAvailableNumbers(newMatrix, i, j)));
						}
					}
				}

				Collections.sort(solver);

				if (solver.get(0).getSize() > 1) {
					return solveAI(solver, newMatrix);
				}
			}

			watchdog--;
			if (watchdog < 0) {
				System.err.println("ERROR !!!");
				System.err.println(solver.size());
				System.err.println(solver);
				System.err.println(solver.get(0));
				return false;
			}
		}

		return true;
	}

	private static boolean solveAI(ArrayList<SpecClass> solver, int[][] newMatrix) {
		SpecClass first = solver.get(0);
		solver.clear();
		for (int iter : first.getAvailableSet()) {
			int[][] newMatrix2 = new int[9][9];
			int[][] newMatrix3 = new int[9][9];

			copyMatrix(newMatrix, newMatrix2);
			newMatrix2[first.getRow()][first.getCol()] = iter;

			if (solveIt(newMatrix2, newMatrix3)) {
				if (!Check.checkMatrix(newMatrix3)) {
					continue;
				}
				copyMatrix(newMatrix3, newMatrix);
				return true;
			}
		}

		return false;
	}

	private static class SpecClass implements Comparable<SpecClass>, Cloneable {

		private int row;
		private int col;
		private HashSet<Integer> available;

		public SpecClass(int row, int col, HashSet<Integer> available) {
			this.row = row;
			this.col = col;
			this.available = available;
		}

		public int compareTo(SpecClass o) {
			return getSize().compareTo(o.getSize());
		}

		public Integer getSize() {
			return available.size();
		}

		public int getRow() {
			return row;
		}

		public int getCol() {
			return col;
		}

		public int getFirstInt() {
			return available.iterator().next();
		}

		public HashSet<Integer> getAvailableSet() {
			return available;
		}

		@Override
		@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
		public boolean equals(Object obj) {
			return hashCode() == obj.hashCode();
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 19 * hash + this.row;
			hash = 19 * hash + this.col;
			return hash;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return new SpecClass(row, col, new HashSet<Integer>(available));
		}

		@Override
		public String toString() {
			return getSize() + " -> " + row + "x" + col;
		}
	}
}
