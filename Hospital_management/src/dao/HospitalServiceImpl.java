package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.appointment;
import util.DBConnection;

public class HospitalServiceImpl implements IHospitalService {

	private Connection connection;

	public HospitalServiceImpl() throws ClassNotFoundException {
		this.connection = DBConnection.getConnection();
	}

	@Override
	public appointment getAppointmentById(int appointmentID) {

		String sql = "SELECT * FROM appointment WHERE appointmentID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, appointmentID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int patientID = rs.getInt(2);
				int doctorID = rs.getInt(3);
				Date appointmentDate = rs.getDate(4);
				String description = rs.getString(5);

				return new appointment(appointmentID, patientID, doctorID, appointmentDate, description);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<appointment> getAppointmentsForPatient(int patientID) {
		List<appointment> appointmentList = new ArrayList<>();
		;
		String sql = "SELECT * FROM appointment WHERE patientID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, patientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int appointmentID = rs.getInt(1);
				int doctorID = rs.getInt(3);
				Date appointmentDate = rs.getDate(4);
				String description = rs.getString(5);
				appointmentList.add(new appointment(appointmentID, patientID, doctorID, appointmentDate, description));
			}
			return appointmentList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<appointment> getAppointmentsForDoctor(int doctorID) {
		List<appointment> appointmentList = new ArrayList<>();
		;
		String sql = "SELECT * FROM appointment WHERE doctorID = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, doctorID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int appointmentID = rs.getInt(1);
				int patientID = rs.getInt(2);
				Date appointmentDate = rs.getDate(4);
				String description = rs.getString(5);
				appointmentList.add(new appointment(appointmentID, patientID, doctorID, appointmentDate, description));
			}
			return appointmentList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean scheduleAppointment(appointment Appointment) {
		String sql = "INSERT INTO appointment (appointmentID,patientID,doctorID,appointmentDate,description) VALUES (?,?, ?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, Appointment.getAppointmentID());
			ps.setInt(2, Appointment.getPatientID());
			ps.setInt(3, Appointment.getDoctorID());
			ps.setDate(4, new java.sql.Date(Appointment.getAppointmentDate().getTime()));
			ps.setString(5, Appointment.getDescription());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateAppointment(appointment Appointment) {
		String sql = "UPDATE appointment SET patientID=?,doctorID=?,appointmentDate=?,description=? where appointmentID=?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setInt(1, Appointment.getPatientID());
			ps.setInt(2, Appointment.getDoctorID());
			ps.setDate(3, new java.sql.Date(Appointment.getAppointmentDate().getTime()));
			ps.setString(4, Appointment.getDescription());
			ps.setInt(5, Appointment.getAppointmentID());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean cancelAppointment(int appointmentID) {
		String sql = "DELETE FROM appointment where appointmentID=?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			
			ps.setInt(1, appointmentID);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
