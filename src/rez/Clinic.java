package rez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Clinic {
	
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:clinic.db";
	
	private Connection conn;
	private Statement stat;
	
	public Clinic() {
		try {
			Class.forName(Clinic.DRIVER);
		}catch (ClassNotFoundException e) {
			System.err.println("Brak sterownika JDBC");
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(DB_URL);
			stat=conn.createStatement();
		}catch (SQLException e) {
			System.err.println("Problem z otwarciem po³aczenia");
			e.printStackTrace();
		}
		createTables();
	}
	public boolean createTables() {
		String createDate = "CREATE TABLE IF NOT EXISTS data (id_date INTEGER PRIMARY KEY AUTOINCREMENT, date varchar(12), time varchar(12), idDoctor int)";
		String createDoctor = "CREATE TABLE IF NOT EXISTS doctor (id_doctor INTEGER PRIMARY KEY AUTOINCREMENT, first_name varchar(255), last_name varchar(255), speciality varchar(40))";
		String createPatient = "CREATE TABLE IF NOT EXISTS patient (id_patient INTEGER PRIMARY KEY AUTOINCREMENT, first_name varchar(255), last_name varchar(255), pesel varchar(30))";
		String createVisit = "CREATE TABLE IF NOT EXISTS visit (id_visit INTEGER PRIMARY KEY AUTOINCREMENT,id_doctor, id_date int, id_patient int, comment varchar(400))";
		
		try {
			stat.execute(createDate);
			stat.execute(createDoctor);
			stat.execute(createPatient);
			stat.execute(createVisit);
		}catch (SQLException e) {
			System.err.println("B³¹d przy tworzeniu tabeli");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean dropDate() {
		String dropDate = "DROP TABLE data";
		try {
			stat.execute(dropDate);
		}catch(SQLException e) {
			System.out.println("B³¹d przy usuwaniu tabeli");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean deleteTables() {
		String deleteDate = "DELETE FROM data";
		String deleteDoctor = "DELETE FROM doctor";
		String deletePatient = "DELETE FROM patient";
		String deleteVisit = "DELETE FROM visit";
		try {
			stat.execute(deleteDate);
			stat.execute(deleteDoctor);
			stat.execute(deletePatient);
			stat.execute(deleteVisit);
		}catch (SQLException e) {
			System.err.println("B³¹d przy usuwaniu zawartoœci tabeli");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean insertDate(String data, String time, int idDoctor) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement(
					"insert into data values(NULL, ?,?,?);");
			prepStmt.setString(1, data);
			prepStmt.setString(2, time);
			prepStmt.setInt(3, idDoctor);
			prepStmt.execute();
		}catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu daty");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean insertDoctor(String firstName, String lastName, String speciality) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement(
					"insert into doctor values(NULL, ?,?,?);");
			prepStmt.setString(1, firstName);
			prepStmt.setString(2, lastName);
			prepStmt.setString(3, speciality);
			prepStmt.execute();
		}catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu doktora");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean insertPatient(String firstName, String lastName, String pesel) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement(
					"insert into patient values(NULL, ?,?,?);");
			prepStmt.setString(1, firstName);
			prepStmt.setString(2, lastName);
			prepStmt.setString(3, pesel);
			prepStmt.execute();
		}catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu pacjenta");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean insertVisit(int idDate, int idDoctor, int idPatient, String comment) {
		try {
			PreparedStatement prepStmt = conn.prepareStatement(
					"insert into visit values(NULL, ?,?,?);");
			prepStmt.setInt(1, idDate);
			prepStmt.setInt(2, idPatient);
			prepStmt.setString(3, comment);
			prepStmt.execute();
		}catch (SQLException e) {
			System.err.println("B³¹d przy wstawianiu daty");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public List<Data>selectDate(int idData){
		List<Data> data = new LinkedList<Data>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM data where idDoctor = " + idData);
            int id, idDoctor;
            String date1, time;
            while(result.next()) {
                id = result.getInt("id_date");
                idDoctor = result.getInt("idDoctor");
                date1 = result.getString("date");
                time = result.getString("time");
                data.add(new Data(id, date1, time, idDoctor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }
	public List<Doctor>selectDoctor(){
		List<Doctor> doctor = new LinkedList<Doctor>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM doctor");
            int id;
            String firstName, lastName, speciality;
            while(result.next()) {
                id = result.getInt("id_doctor");
                firstName = result.getString("first_name");
                lastName = result.getString("last_name");
                speciality = result.getString("speciality");
                doctor.add(new Doctor(id, firstName, lastName, speciality));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return doctor;
    }
	public List<Patient>selectPatient(){
		List<Patient> patient = new LinkedList<Patient>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM patient");
            int id;
            String firstName, lastName, pesel;
            while(result.next()) {
                id = result.getInt("id_patient");
                firstName = result.getString("first_name");
                lastName = result.getString("last_name");
                pesel = result.getString("pesel");
                patient.add(new Patient(id, firstName, lastName, pesel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return patient;
    }
	public List<Visit>selectVisit(){
		List<Visit> visit = new LinkedList<Visit>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM visit");
            int id, idDoctor, idDate, idPatient;
            String comment;
            while(result.next()) {
                id = result.getInt("id_visit");
                idDoctor = result.getInt("id_doctor");
                idDate = result.getInt("id_date");
                idPatient = result.getInt("id_patient");
                comment = result.getString("comment");
                visit.add(new Visit(id, idDoctor, idDate, idPatient, comment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return visit;
    }
	
	public void deleteDate(int id) {
		String deleteDate = "DELETE FROM data WHERE id_date = " + id;
		try {
			stat.execute(deleteDate);
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}

	public List<Data>testDate(int idData){
		List<Data> data = new LinkedList<Data>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM data where id_date = " + idData);
            int id;
            String date1, time;
            while(result.next()) {
                id = result.getInt("id_date");
                date1 = result.getString("date");
                time = result.getString("time");
                data.add(new Data(id, date1, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }
	
	public List<Doctor>testDoctor(int idD){
		List<Doctor> doctor = new LinkedList<Doctor>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM doctor where id_doctor = " + idD);
            int id;
            String firstName, lastName, speciality;
            while(result.next()) {
                id = result.getInt("id_doctor");
                firstName = result.getString("first_name");
                lastName = result.getString("last_name");
                speciality = result.getString("speciality");
                doctor.add(new Doctor(id, firstName, lastName, speciality));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return doctor;
    }
	
	public void idPatientt(String pesel) {
		try {
			ResultSet result = stat.executeQuery("select id_patient from patient where pesel =" + pesel);
			int id;
			id = result.getInt("id_patient");
			System.out.println(id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
}
