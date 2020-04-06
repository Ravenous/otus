package str;

public class StrokaArray {
	
	public StrokaArray() {		
	}

	public char[] getCharArray(String str) {
		return str.toCharArray();
	}

	public char[] getCharArray(String str, int bgnIndx) {
		if ((bgnIndx >= 0) && (bgnIndx <= (str.length()-1))) {
			str = str.substring(bgnIndx);
		}
		else {
			str = "";
		}
		return str.toCharArray();
	}

	public char[] getCharArray(String str, int bgnIndx, int endIndx) {
		if ((bgnIndx > endIndx) || 
				((bgnIndx < 0) || (endIndx < 0) || 
						(bgnIndx > (str.length()-1)) || 
						(endIndx > (str.length()-1)))) {
			str = "";
		}
		return str.substring(bgnIndx, endIndx).toCharArray();
	}
	
}
