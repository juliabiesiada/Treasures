package pl.ue.poznan.model;

import java.sql.Date;

public class Notification {
	Integer nid;
	Integer types_tid;
	String recipientUname;
	String senderUname;
	Date dateAdded;
	String msgContent;
	Integer status;
	Integer offers_oid;
	Date date_req;
	String requestedOffer;
	
	public Notification() {
		super();
	}
	//for msg
	public Notification(Integer nid, Integer types_tid, String recipientUname, String senderUname, Date dateAdded, String msgContent,
			Integer status) {
		super();
		this.nid = nid;
		this.types_tid = types_tid;
		this.recipientUname = recipientUname;
		this.senderUname = senderUname;
		this.dateAdded = dateAdded;
		this.msgContent = msgContent;
		this.status = status;
	}
	//for request
	public Notification(Integer nid, Integer types_tid, String recipientUname, String senderUname, Date dateAdded,
			Integer status, Integer offers_oid, Date date_req) {
		super();
		this.nid = nid;
		this.types_tid = types_tid;
		this.recipientUname = recipientUname;
		this.senderUname = senderUname;
		this.dateAdded = dateAdded;
		this.status = status;
		this.offers_oid = offers_oid;
		this.date_req = date_req;
	}
	//universal
	public Notification(Integer nid, Integer types_tid, String recipientUname, String senderUname, Date dateAdded,
			String msgContent, Integer status, Integer offers_oid, Date date_req) {
		super();
		this.nid = nid;
		this.types_tid = types_tid;
		this.recipientUname = recipientUname;
		this.senderUname = senderUname;
		this.dateAdded = dateAdded;
		this.msgContent = msgContent;
		this.status = status;
		this.offers_oid = offers_oid;
		this.date_req = date_req;
	}
	//for requests listing 
	public Notification(Integer nid, Integer types_tid, String recipientUname, String senderUname, Date dateAdded,
			String msgContent, Integer status, Integer offers_oid, Date date_req, String requestedOffer) {
		super();
		this.nid = nid;
		this.types_tid = types_tid;
		this.recipientUname = recipientUname;
		this.senderUname = senderUname;
		this.dateAdded = dateAdded;
		this.msgContent = msgContent;
		this.status = status;
		this.offers_oid = offers_oid;
		this.date_req = date_req;
		this.requestedOffer = requestedOffer;
	}
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
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
	public Integer getOffers_oid() {
		return offers_oid;
	}
	public void setOffers_oid(Integer offers_oid) {
		this.offers_oid = offers_oid;
	}
	public Date getDate_req() {
		return date_req;
	}
	public void setDate_req(Date date_req) {
		this.date_req = date_req;
	}
	public String getRequestedOffer() {
		return requestedOffer;
	}
	public void setRequestedOffer(String requestedOffer) {
		this.requestedOffer = requestedOffer;
	}
	
	
}
