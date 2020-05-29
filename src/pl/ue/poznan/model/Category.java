package pl.ue.poznan.model;

public class Category {
	
	Integer cid;
	String name;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category(Integer cid, String name) {
		super();
		this.cid = cid;
		this.name = name;
	}
	public Category() {
		super();
	}

}
