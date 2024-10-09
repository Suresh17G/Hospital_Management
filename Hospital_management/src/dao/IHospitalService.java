package dao;

import java.util.List;

import entity.appointment;

public interface IHospitalService {

	appointment getAppointmentById(int appointmentID);
	
	List<appointment> getAppointmentsForPatient(int patientID);
	
	List<appointment> getAppointmentsForDoctor(int doctorID);
	
	boolean scheduleAppointment(appointment Appointment);
	
	boolean updateAppointment(appointment Appointment);
	
	boolean cancelAppointment(int appointmentID);
}
