package MiniProject_AddressBook_Integrated;

public class NumberException extends Exception{
	
	public NumberException(String a) {
		super(a);
	}
	
	@Override
	public String toString() {
		
		return getMessage();
	}
}
