package rez;

public class Data {
	
	private int id;
	private String date;
	private String time;
	private int idDoctor;
	

	public Data(int id, String date, String time, int idDoctor) {
		this.id= id;
		this.date=date;
		this.time=time;
		this.idDoctor=idDoctor;
	}
	public Data(int id, String date, String time) {
		this.id= id;
		this.date=date;
		this.time=time;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getIdDoctor() {
		return idDoctor;
	}
	public void setIdDoctor(int idDoctor) {
		this.idDoctor=idDoctor;
	}
	public String toString() {
		return "["+id+"] - " +date+", "+time;
	}
}

