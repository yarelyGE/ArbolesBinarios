package queve;

@SuppressWarnings("serial")
public class QueveFullException extends Exception {
	
	public QueveFullException() {
		// TODO Auto-generated constructor stub
	}
	
	public QueveFullException(String causa){
		super (causa);
	}
	
	public QueveFullException(Throwable causa){
		super (causa);
	}
	
}