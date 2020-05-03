package myjava;

import java.sql.Date;

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
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Float getAllowance() {
		return allowance;
	}

	public void setAllowance(Float allowance) {
		this.allowance = allowance;
	}

	public Integer getPosID() {
		return posID;
	}

	public void setPosID(Integer posID) {
		this.posID = posID;
	}

	public Integer getJgID() {
		return jgID;
	}

	public void setJgID(Integer jgID) {
		this.jgID = jgID;
	}

	public Integer getManagerID() {
		return managerID;
	}

	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}

	public Integer getDepID() {
		return depID;
	}

	public void setDepID(Integer depID) {
		this.depID = depID;
	}

	public Employee() {
		super();
	}

	public Employee(Integer id, String fname, String lname, Date hireDate, 
					Float salary, Float allowance, Integer posID, Integer jgID,
					Integer managerID, Integer depID) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.hireDate = hireDate;
		this.salary = salary;
		this.allowance = allowance;
		this.posID = posID;
		this.jgID = jgID;
		this.managerID = managerID;
		this.depID = depID;
	}
	
	
	
}
