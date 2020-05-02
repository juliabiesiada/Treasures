package myjava;

import java.util.Date;

public class Employee {
	
	String fname;
	String lname;
	Integer id;
	Date hireDate;
	Float salary;
	Float allowance;
	Integer posID;
	Integer jgID;
	Integer managerID;
	Integer depID;
	
	public Employee(Integer id, String fname, String lname, Date hireDate, 
					Float salary, Float allowance, Integer posID, Integer jgID,
					Integer managerID, Integer depID) {
		this.fname = fname;
		this.lname = lname;
		this.id = id;
		this.hireDate = hireDate;
		this.salary = salary;
		this.allowance = allowance;
		this.posID = posID;
		this.jgID = jgID;
		this.managerID = managerID;
		this.depID = depID;
	}
	
	public String getFirstName() {
		return fname;
	}
	
	public String getLastName() {
		return lname;
	}
	
	public Integer getID() {
		return id;
	}
	
}
