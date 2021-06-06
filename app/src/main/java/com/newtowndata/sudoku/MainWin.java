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

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Voyta Krizek, https://github.com/NewTownData
 */
public class MainWin extends javax.swing.JFrame {

	public static final String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
	
	public int[][] solvedSudoku = new int[9][9];
	public int[][] filledSudoku = new int[9][9];

	public static final int[][] initialSudoku = {
		// @formatter:off
		{4, 9, 0, 8, 0, 0, 0, 6, 0},
		{1, 0, 0, 4, 0, 0, 8, 3, 0},
		{0, 0, 0, 0, 0, 6, 0, 0, 0},
		{0, 0, 5, 7, 0, 0, 2, 0, 0},
		{0, 0, 0, 5, 0, 4, 0, 0, 0},
		{0, 0, 6, 0, 0, 8, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 5, 1, 0, 0, 3, 0, 0, 7},
		{0, 3, 0, 0, 0, 9, 0, 8, 5}
		// @formatter:on
	};

	private SudokuTable model;

	/** Creates new form MainWin */
	public MainWin() {
		filledSudoku = initialSudoku;
		model = new SudokuTable();

		initComponents();

		this.setTitle("Sudoku Force");

		jTable1.setDefaultRenderer(Object.class, new TableBg());
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT
	 * modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTable1.setModel(model);
		jTable1.setCellSelectionEnabled(true);
		jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable1);

		jButton1.setText("Solve");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Clear");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Revert");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(jButton1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton3)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jButton2)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19,
								Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1).addComponent(jButton2).addComponent(jButton3))
						.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		filledSudoku = new int[9][9];

		model.setSolved(false);

		jTable1.validate();
		jTable1.repaint();

		System.out.println("Cleared");
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		System.out.println("Solving ...");

		if (!Solver.solveIt(filledSudoku, solvedSudoku)) {
			Alert.showError("Can't solve this sudoku!\n\nPartial solve added.");
		}

		model.setSolved(true);

		jTable1.validate();
		jTable1.repaint();

		System.out.println("Solved");
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jTable1MouseClicked
		if (evt.getButton() == MouseEvent.BUTTON3) {
			int row = jTable1.rowAtPoint(evt.getPoint());
			int col = jTable1.columnAtPoint(evt.getPoint());

			System.out.println(Expand.getAvailableNumbers(filledSudoku, row, col).toString());
		}
	}// GEN-LAST:event_jTable1MouseClicked

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		model.setSolved(false);

		jTable1.validate();
		jTable1.repaint();
	}// GEN-LAST:event_jButton3ActionPerformed

	private class TableBg extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected,
				boolean focused, int row, int column) {
			setEnabled(table == null || table.isEnabled());

			int x = row / 3;
			int y = column / 3;

			if ((x % 2) == (y % 2)) {
				setBackground(new Color(0xee, 0xee, 0xee));
			} else {
				setBackground(null);
			}

			setForeground(null);
			if (model.isSolved()) {
				if (filledSudoku[row][column] == 0) {
					setForeground(Color.red);
				}
			}

			super.getTableCellRendererComponent(table, value, selected, focused, row, column);

			return this;
		}
	}

	private class SudokuTable extends AbstractTableModel {

		private int[][] refArr = filledSudoku;
		private boolean solved = false;

		public void setSolved(boolean solved) {
			this.solved = solved;

			if (solved) {
				refArr = solvedSudoku;
			} else {
				refArr = filledSudoku;
			}
		}

		public boolean isSolved() {
			return solved;
		}

		public int getRowCount() {
			return columns.length;
		}

		public int getColumnCount() {
			return columns.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			if (refArr[rowIndex][columnIndex] == 0) {
				return "";
			} else {
				return refArr[rowIndex][columnIndex];
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			try {
				String s = aValue.toString();
				if (s.isEmpty()) {
					if (refArr[rowIndex][columnIndex] == 0) {
						return;
					} else {
						refArr[rowIndex][columnIndex] = 0;
						return;
					}
				}

				char x = s.charAt(0);
				int y = Integer.parseInt(Character.toString(x));
				int lastVal = refArr[rowIndex][columnIndex];

				if (y > 0 && y < 10) {
					refArr[rowIndex][columnIndex] = y;
					if (!Check.checkMatrix(refArr)) {
						Alert.showError("Invalid number.\n\nSyntax error!");
						refArr[rowIndex][columnIndex] = lastVal;
					}
				} else {
					Alert.showError("Invalid number.\n\nPossible values are between 1 and 9.");
				}
			} catch (Exception e) {
				Alert.showError("Invalid input!");
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (!solved) {
				return true;
			}

			if (refArr[rowIndex][columnIndex] == 0) {
				return true;
			}

			return false;
		}

		@Override
		public String getColumnName(int column) {
			return columns[column];
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	// End of variables declaration//GEN-END:variables
}