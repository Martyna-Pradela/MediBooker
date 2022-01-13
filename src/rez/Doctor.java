package rez;

public class Doctor {
	
	private int id;
	private String firstName;
	private String lastName;
	private String speciality;
	
	public Doctor(int id, String firstName, String lastName, String speciality) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.speciality=speciality;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality=speciality;
	}
	public String toString() {
		return "["+id+"] - " +speciality +" - " + firstName +" "+lastName;
	}

}
