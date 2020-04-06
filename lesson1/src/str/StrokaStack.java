package str;

public class StrokaStack {
	
	private Stroka[] stack;
	private int size;    
	private int indx = 0;
	
	public StrokaStack(int initSize) {
		size = initSize;
		if (size == 0) {
			size = 1;
		}
		stack = new Stroka[size];
	}

	public void putStackEl(String str) {
		if (this.getStackEl() != null) {
			indx++;			
		}
	    if (indx == size) {
	    	size++;
	    	Stroka[] newStack = new Stroka[size];
		    System.arraycopy(stack,0,newStack,0,stack.length);
	    	stack = newStack;
	    } 
	    stack[indx] = new Stroka(str);
	}

	public void deleteStackEl() {
		System.out.println("del indx: " + indx);
				stack[indx] = null;			
		if (indx > 0) {
			indx--;			
		}
		System.out.println("del indx: " + indx);
	}
	
	public Stroka getStackEl() {
		return stack[indx];
	}
	
	public boolean isEmpty() {
		return (stack[0]==null);		
	}
	
	public int getStackSize() {
		return size;
	}

	@Override
    public java.lang.String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("String:[");
        for (Stroka str : stack) {
        	if (str != null) {
                stringBuilder.append("{").append(new String(str.getStrokaBytes())).append("},");        		
        	}
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
