package pl.ue.poznan.model;

import java.sql.Date;

//this class will contain all the useful, but random methods used in the project

public class Extra {

	public Extra() {
		super();
	}
	
	public java.sql.Date convertDate(java.util.Date uDate) {
		
		java.sql.Date sqlDate = new java.sql.Date(uDate.getTime());
		return sqlDate;
		
	}
	

}
