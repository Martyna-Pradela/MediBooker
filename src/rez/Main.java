package rez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) {
		
		String firstName, lastName, pesel, conf;
		int idDoctor, idDate, idPatient;
		
		Scanner sc = new Scanner(System.in);
	
		Clinic c = new Clinic();
		
		System.out.println("Podaj imie");
		firstName = sc.nextLine();
		System.out.println("Podaj nazwisko");
		lastName = sc.nextLine();
		System.out.println("Podaj pesel");
		pesel = sc.nextLine();
		
		List<Doctor> doctor = c.selectDoctor();
		System.out.println("Lista doktorów:");
		for(Doctor d : doctor) {
			System.out.println(d);
		}
		System.out.println("Wybierz lekarza");
		idDoctor = sc.nextInt();
		List<Data> data = c.selectDate(idDoctor);
		System.out.println("Daty:");
		for(Data da : data) {
			System.out.println(da);
		}
		idDate = sc.nextInt();
		System.out.println();
		System.out.println("Twój wybór");
		List<Doctor>doc = c.testDoctor(idDoctor);
		for(Doctor doct : doc) {
			System.out.println("Lekarz: " + doct);
		}
		List<Data>da = c.testDate(idDate);
		for(Data dat : da) {
			System.out.println("Data: " +  dat);
		}
		System.out.println();
		System.out.println("Potwierdzasz?\ntak/nie");
		conf = sc.nextLine();
		
//			c.insertPatient(firstName, lastName, pesel);
			c.deleteDate(idDate);
//			c.idPatientt(pesel);
//			int number = sc.nextInt();
//			System.out.println("Podaj uwagi");
//			String uwagi = sc.nextLine();
//			c.insertVisit(idDate, idDoctor, number, uwagi);
		
		c.closeConnection();
	}
	
}
