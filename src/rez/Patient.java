package rez;

public class Patient {
	
	private int id;
	private String firstName;
	private String lastName;
	private String pesel;
	
	public Patient(int id, String firstName, String lastName, String pesel) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.pesel=pesel;
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
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel=pesel;
	}
	public String toString() {
		return "["+id+"] "+firstName+" | " +lastName+" | " + pesel;
	}
}
