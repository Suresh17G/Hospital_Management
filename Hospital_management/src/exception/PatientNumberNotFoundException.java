package exception;

public class PatientNumberNotFoundException extends Exception {
	public PatientNumberNotFoundException() {
		super("Given Patient ID does not exist.");
	}
	
	@Override
	public String toString(){
		return "PatientNumberNotFoundException: "+getMessage();
		
	}
}
