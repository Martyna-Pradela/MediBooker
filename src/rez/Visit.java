package rez;

public class Visit {
	
	private int id;
	private int idDate;
	private int idDoctor;
	private int idPatient;
	private String comment;
	
	
	public Visit(int id, int idDoctor, int idDate, int idPatient, String comment) {
		this.id=id;
		this.idDoctor=idDoctor;
		this.idDate=idDate;
		this.idPatient=idPatient;
		this.comment=comment;
	}
	public Visit(int id, int idDate, int idPatient) {
		this.id=id;
		this.idDate=idDate;
		this.idPatient=idPatient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getIdDate() {
		return idDate;
	}
	public void setIdDate(int idDate) {
		this.idDate=idDate;
	}
	public int getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(int idPatient) {
		this.idPatient=idPatient;
	}
	

}
