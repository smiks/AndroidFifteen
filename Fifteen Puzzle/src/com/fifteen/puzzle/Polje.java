package com.fifteen.puzzle;

public class Polje {
	public static final int EMPTY_FIELD = -1;
	public int[][] polje;
	public static int SIZE;

	public Polje(int size) {
		Polje.SIZE = size;
		polje = new int[size + 2][size + 2];

		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				polje[i][j] = (i - 1) * size + j;
			}
		}
		polje[size][size] = EMPTY_FIELD;
	}

	private void swap(int i, int j,int ii, int jj) {
		polje[ii][jj] = polje[i][j];
		polje[i][j] =  EMPTY_FIELD;
	}
	
	public boolean move(int i, int j){
		if(polje[i+1][j] == EMPTY_FIELD){
			swap(i, j, i+1, j);
		}
		if(polje[i-1][j] == EMPTY_FIELD){
			swap(i, j, i-1, j);
		}
		if(polje[i][j+1] == EMPTY_FIELD){
			swap(i, j, i, j+1);
		}
		if(polje[i][j-1] == EMPTY_FIELD){
			swap(i, j, i, j-1);
		}
		return checkeFinished();
		
	}
	
	private boolean checkeFinished() {
		boolean result = true;
		for (int i = 1; i <= Polje.SIZE; i++) {
			for (int j = 1; j <= Polje.SIZE; j++) {
				result &= polje[i][j] == (i - 1) * Polje.SIZE + j;
			}
		}
		return result;
	}

	public void shuffle(){
		for (int i = 0; i < 1000; i++) {
			move((int) Math.random()*3+1, (int) Math.random()*3+1);
		}
	}
	
	public int getValueOn(int i, int j){
		return polje[i][j];
	}
	
}
