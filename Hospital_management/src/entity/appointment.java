package entity;

import java.util.Date;

public class appointment {
	private int appointmentID;
	private int patientID;
	private int doctorID;
    private Date appointmentDate;
    private String description;
	
    public appointment() {
		
	}

	public appointment(int appointmentID, int patientID, int doctorID, Date appointmentDate, String description) {
		super();
		this.appointmentID = appointmentID;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.appointmentDate = appointmentDate;
		this.description = description;
	}

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Appointment: \n\nAppointmentID = " + appointmentID + "\nPatientID = " + patientID + "\nDoctorID = " + doctorID
				+ "\nAppointmentDate = " + appointmentDate + "\nDescription = " + description+"\n\t----";
	}
    
    
    
}
