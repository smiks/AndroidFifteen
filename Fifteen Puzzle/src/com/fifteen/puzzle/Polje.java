package com.fifteen.puzzle;

public class Polje {
	public static final int EMPTY_FIELD = -1;
	public int[][] polje;
	public static int SIZE;
	private static long timestart;
	private int nrMoves;

	public Polje(int size) {
		Polje.SIZE = size;
		polje = new int[size + 2][size + 2];
		nrMoves = 0;

		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				polje[i][j] = (i - 1) * size + j;
			}
		}
		polje[size][size] = EMPTY_FIELD;
	}

	public long getTime() {
		return timestart;
	}

	public int getMoves() {
		return nrMoves;
	}

	public int getValueOn(int i, int j) {
		return polje[i][j];
	}

	public void setStPotez(int a) {
		nrMoves = a;
	}

	public void setTime(long a) {
		timestart = a;
	}

	public void incMoves() {
		if (nrMoves == 0) {
			timestart = System.currentTimeMillis();
		}
		nrMoves++;
	}

	private void swap(int i, int j, int ii, int jj) {
		polje[ii][jj] = polje[i][j];
		polje[i][j] = EMPTY_FIELD;
	}

	public boolean move(int i, int j) {
		if (polje[i + 1][j] == EMPTY_FIELD) {
			swap(i, j, i + 1, j);
		}
		if (polje[i - 1][j] == EMPTY_FIELD) {
			swap(i, j, i - 1, j);
		}
		if (polje[i][j + 1] == EMPTY_FIELD) {
			swap(i, j, i, j + 1);
		}
		if (polje[i][j - 1] == EMPTY_FIELD) {
			swap(i, j, i, j - 1);
		}
		return checkeFinished();

	}

	private boolean checkeFinished() {
		boolean result = true;
		for (int i = 1; i <= Polje.SIZE; i++) {
			for (int j = 1; j <= Polje.SIZE; j++) {
				if (i == 3 && j == 3)
					result &= polje[i][j] == -1;
				else
					result &= polje[i][j] == (i - 1) * Polje.SIZE + j;
			}
		}
		return result;
	}

	public void shuffle(int limit) {
		for (int i = 0; i < limit; i++) {
			int a = (int) (Math.random() * 3 + 1);
			int b = (int) (Math.random() * 3 + 1);
			move(a, b);
		}
	}
}
