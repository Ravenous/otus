package matrix;

import java.math.BigInteger;

public class MultiplyMatrix {
	
	private int[][] firstMatrix;
	private int[][] secondMatrix;
	private static BigInteger [][] resultMatrix;
	
	public MultiplyMatrix(int[][] firstMatrix, int[][] secondMatrix) {
		
		this.firstMatrix = firstMatrix;
		this.secondMatrix = secondMatrix;
		resultMatrix = new BigInteger[firstMatrix.length][firstMatrix[0].length];
	}	
	
    public void multiply() {
    	
         for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[i].length; j++) { 
           	if (resultMatrix[i][j] != null) {
            		continue;
            	}
            	resultMatrix[i][j] = multiplyMatrixCell(firstMatrix, secondMatrix, i, j);
            }
         }
    }
	
	public BigInteger multiplyMatrixCell(int[][] firstMatrix, int[][] secondMatrix, int row, int col) {

		BigInteger cell = BigInteger.valueOf(0);
		
		for (int i = 0; i < secondMatrix.length; i++) {
	    	cell = cell.add(BigInteger.valueOf(firstMatrix[row][i]).
	    			multiply(BigInteger.valueOf(secondMatrix[i][col])));
        	System.out.println(Thread.currentThread().getName()
        			+ " firstMatrix["+row+"]["+i+"] " + firstMatrix[row][i] 
                		+ " secondMatrix["+i+"]["+col+"] " + secondMatrix[i][col]
                        		+ " resultMatrix["+row+"]"+"["+col+"] " + cell);

		}		
		return cell;
	}
	
    public void show() {
    	System.out.println("resultMatrix:");
        for (int i = 0; i < resultMatrix.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < resultMatrix[i].length; j++) {
            	System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println("|");
        }
    }
}
