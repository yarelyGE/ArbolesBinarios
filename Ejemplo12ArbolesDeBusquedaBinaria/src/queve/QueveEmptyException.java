package queve;

@SuppressWarnings("serial")
public class QueveEmptyException extends Exception {
	
	public QueveEmptyException() {
		// TODO Auto-generated constructor stub
	}
	
	public QueveEmptyException(String causa){
		super (causa);
	}
	
	public QueveEmptyException(Throwable causa){
		super (causa);
	}
	
}