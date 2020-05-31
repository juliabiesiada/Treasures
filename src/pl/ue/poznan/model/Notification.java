package pl.ue.poznan.model;

import java.sql.Date;

public class Notification {
	Integer types_tid;
	String recipientUname;
	String senderUname;
	Date dateAdded;
	String msgContent;
	Integer status;
	
	public Notification() {
		super();
	}
	public Notification(Integer types_tid, String recipientUname, String senderUname, Date dateAdded, String msgContent,
			Integer status) {
		super();
		this.types_tid = types_tid;
		this.recipientUname = recipientUname;
		this.senderUname = senderUname;
		this.dateAdded = dateAdded;
		this.msgContent = msgContent;
		this.status = status;
	}
	public Integer getTypes_tid() {
		return types_tid;
	}
	public void setTypes_tid(Integer types_tid) {
		this.types_tid = types_tid;
	}
	public String getRecipientUname() {
		return recipientUname;
	}
	public void setRecipientUname(String recipientUname) {
		this.recipientUname = recipientUname;
	}
	public String getSenderUname() {
		return senderUname;
	}
	public void setSenderUname(String senderUname) {
		this.senderUname = senderUname;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
