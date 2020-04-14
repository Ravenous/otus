import matrix.Matrix;
import matrix.MultiplyMatrix;

public class Main {

	public static void main(String[] args) {

		Matrix matrix = new Matrix(3);
		MultiplyMatrix multiplyMatrix = new MultiplyMatrix(matrix.getFirstMatrix(),matrix.getSecondMatrix());
		
		MyThread thread1 = new MyThread(multiplyMatrix); 
		MyThread thread2 = new MyThread(multiplyMatrix); 
		MyThread thread3 = new MyThread(multiplyMatrix);
		
		thread1.start();
		thread2.start();
		thread3.start();
	}

}

class MyThread extends Thread {
    private MultiplyMatrix multiplyMatrix;
    
    MyThread(MultiplyMatrix multiplyMatrix) {
        this.multiplyMatrix = multiplyMatrix;
    }

    @Override
    public void run() {
       	multiplyMatrix.multiply();
    }
}