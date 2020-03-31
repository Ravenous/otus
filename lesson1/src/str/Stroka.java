package str;

public class Stroka {
	
	byte[] strokaBytes;
	int strokaLenBytes;
	
	public Stroka() {		
	}
	
	public Stroka(String str) {
		this.setStrokaBytes(str);
		this.setStrokaLenBytes(str);
	}
	
	public Stroka(char c) {
		String str = String.valueOf(c);
		this.setStrokaBytes(str);
		this.setStrokaLenBytes(str);
	}
	
	public void setStrokaValue(String str) {
		this.setStrokaBytes(str);
		this.setStrokaLenBytes(str);
	}

	public byte[] getStrokaBytes() {
		return strokaBytes;
	}

	public int getStrokaLenBytes() {
		return strokaLenBytes;
	}

	private void setStrokaBytes(String str) {
		this.strokaBytes = str.getBytes();
	}

	private void setStrokaLenBytes(String str) {
		this.strokaLenBytes = str.getBytes().length;
	}

	public void clearStroka() {
		this.setStrokaBytes("");
		this.setStrokaLenBytes("");
	}	

}
