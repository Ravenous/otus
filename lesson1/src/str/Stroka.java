package str;

public class Stroka {
	
	private byte[] strokaBytes;
	private int strokaLenBytes;
	
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
	@Override
    public java.lang.String toString() {
//      return "String:[{"+ new String(strokaBytes) + "}]";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("String:[");
        for (byte str : strokaBytes) {
        	char ch = (char) str;
                stringBuilder.append("{").append(ch).append("}");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
    
}
