package main;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import dao.HospitalServiceImpl;
import entity.appointment;
import exception.PatientNumberNotFoundException;

public class mainModule {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException {
		HospitalServiceImpl service = new HospitalServiceImpl();

		System.out.println("Welcome to Hospital Management!!\n");
		try {
			while (true) {

				System.out.println("\nHospital Management Menu: \n");
				System.out.println("1) Search Appointments by their ID ");
				System.out.println("2) Search Appointments for a patient ");
				System.out.println("3) Search Appointments for a doctor ");
				System.out.println("4) Schedule an Appointment");
				System.out.println("5) Update an Appointment");
				System.out.println("6) Cancel an Appointment ");
				System.out.println("7) Exit");
				System.out.print("\nEnter your choice: ");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					getAppointment(service);
					break;

				case 2:
					getAppointmentbypatient(service);
					break;

				case 3:
					getAppointmentbydoctor(service);
					break;

				case 4:
					scheduleAppointment(service);
					break;

				case 5:
					updateAppointment(service);
					break;

				case 6:
					cancelAppointment(service);
					break;

				case 7:
					System.out.println("Thank You for using Hospital Management");
					return;

				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} catch (Exception e) {
			System.out.println("\nUnexpected Error: " + e + "\nExiting Hospital Management!!");
		}
	}

	public static void getAppointment(HospitalServiceImpl service) {
		System.out.print("\nEnter AppointmentID to search: ");
		int appointmentID = scanner.nextInt();
		appointment Appointment = service.getAppointmentById(appointmentID);
		if (Appointment != null) {
			System.out.println(Appointment);
		} else {
			System.out.println("\nNo Appointments were found for given ID!!\n");
		}
	}

	public static void getAppointmentbypatient(HospitalServiceImpl service) {
		System.out.print("\nEnter PatientID to search: ");
		int PatientID = scanner.nextInt();
		try {
			List<appointment> Appointment = service.getAppointmentsForPatient(PatientID);
			if (Appointment.isEmpty()) {
				throw new PatientNumberNotFoundException();
			}
			for (appointment i : Appointment) {
				System.out.println("\n" + i);
			}
		} catch (PatientNumberNotFoundException e) {
			System.out.println(e.getMessage() + "in Appointments!");
		}
	}

	public static void getAppointmentbydoctor(HospitalServiceImpl service) {
		System.out.print("\nEnter doctorID to search: ");
		int doctorID = scanner.nextInt();
		List<appointment> Appointment = service.getAppointmentsForDoctor(doctorID);
		if (Appointment.isEmpty()) {
			System.out.println("Given DoctorID does not have any Appointments!");
		}
		for (appointment i : Appointment) {
			System.out.println("\n" + i);
		}

	}
	
	public static void scheduleAppointment(HospitalServiceImpl service) {
		appointment Appointment= new appointment();
		System.out.print("\nEnter new appointmentID: ");
		int appointmentID = scanner.nextInt();
		Appointment.setAppointmentID(appointmentID);
		System.out.print("\nEnter patientID: ");
		int patientID = scanner.nextInt();
		Appointment.setPatientID(patientID);
		System.out.print("\nEnter doctorID : ");
		int doctorID = scanner.nextInt();
		Appointment.setDoctorID(doctorID);
		scanner.nextLine();
		System.out.print("\nEnter appointmentDate: ");
		String appointmentDatestr = scanner.nextLine();
		try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate appointmentDate = LocalDate.parse(appointmentDatestr, formatter);
            java.sql.Date sqlDate = java.sql.Date.valueOf(appointmentDate);
            Appointment.setAppointmentDate(sqlDate);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }
		System.out.print("\nEnter description: ");
		String description = scanner.nextLine();
		Appointment.setDescription(description);
		 if (service.scheduleAppointment(Appointment)) {
	            System.out.println("Appointment created successfully on "+Appointment.getAppointmentDate());
	        } else {
	            System.out.println("Failed to create Appointment.");
	        }
	}
	
	public static void updateAppointment(HospitalServiceImpl service) {
		appointment Appointment= new appointment();
		System.out.print("\nEnter appointmentID to update: ");
		int appointmentID = scanner.nextInt();
		Appointment.setAppointmentID(appointmentID);
		System.out.print("\nEnter new patientID: ");
		int patientID = scanner.nextInt();
		Appointment.setPatientID(patientID);
		System.out.print("\nEnter new doctorID : ");
		int doctorID = scanner.nextInt();
		Appointment.setDoctorID(doctorID);
		scanner.nextLine();
		System.out.print("\nEnter new appointmentDate: ");
		String appointmentDatestr = scanner.nextLine();
		try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate appointmentDate = LocalDate.parse(appointmentDatestr, formatter);
            java.sql.Date sqlDate = java.sql.Date.valueOf(appointmentDate);
            Appointment.setAppointmentDate(sqlDate);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }
		System.out.print("\nEnter new description: ");
		String description = scanner.nextLine();
		Appointment.setDescription(description);
		 if (service.updateAppointment(Appointment)) {
	            System.out.println("Appointment changed successfully to "+Appointment.getAppointmentDate());
	        } else {
	            System.out.println("Failed to update Appointment.");
	        }
	}
	
	public static void cancelAppointment(HospitalServiceImpl service) {
		
		System.out.print("\nEnter appointmentID to cancel: ");
		int appointmentID = scanner.nextInt();
		 if (service.cancelAppointment(appointmentID)) {
	            System.out.println("Appointment was cancelled successfully");
	        } else {
	            System.out.println("Failed to cancel Appointment.");
	        }
	}
}
