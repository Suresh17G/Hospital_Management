package entity;

import java.util.Date;
public class patient {
	 	private int patientID;
	    private String firstName;
	    private String lastName;
	    private Date dateOfBirth;
	    private String gender;
	    private String contactNumber;
	    private String address;
	    

		public patient() {
		
		}
		public patient(int patientID, String firstName, String lastName, Date dateOfBirth, String gender,
				String contactNumber, String address) {
			this.patientID = patientID;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dateOfBirth = dateOfBirth;
			this.gender = gender;
			this.contactNumber = contactNumber;
			this.address = address;
		}
		
		public int getPatientID() {
			return patientID;
		}
		public void setPatientID(int patientID) {
			this.patientID = patientID;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Date getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "Patient: \nPatientID=" + patientID + "\nFirstName=" + firstName + "\nLastName=" + lastName
					+ "\nDateOfBirth=" + dateOfBirth + "\nGender=" + gender + "\nContactNumber=" + contactNumber
					+ "\nAddress=" + address + "\n\t----";
		}
	    

}
