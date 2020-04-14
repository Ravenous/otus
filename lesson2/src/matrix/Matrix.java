package matrix;

import java.util.Random;

public class Matrix {

	int[][] firstMatrix;
	int[][] secondMatrix;
	Random rand = new Random();
	
	public Matrix() {

	}

	public Matrix(int size) {
		this.init(size);
	}

	public void init(int size) {

		firstMatrix = new int[size][size];
		secondMatrix = new int[size][size];
		
		System.out.println("firstMatrix:");
	    for (int i = 0; i < size; i++) {
			System.out.print("| ");
	        for (int j = 0; j < size; j++) {
	        	firstMatrix[i][j]=rand.nextInt(10);
				System.out.print(firstMatrix[i][j] + " ");
	        }
			System.out.println("|");
	    }		
		
		System.out.println("secondMatrix:");
	    for (int i = 0; i < size; i++) {
			System.out.print("| ");
	        for (int j = 0; j < size; j++) {
	        	secondMatrix[i][j]=rand.nextInt(10);
				System.out.print(secondMatrix[i][j] + " ");
	        }
			System.out.println("|");
	    }

	}
	
	public int[][] getFirstMatrix() {
		return firstMatrix;
	}

	public int[][] getSecondMatrix() {
		return secondMatrix;
	}	

}
