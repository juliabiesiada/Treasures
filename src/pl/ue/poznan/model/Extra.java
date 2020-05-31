package pl.ue.poznan.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import pl.ue.poznan.service.CategoryServiceImpl;

//this class will contain all the useful, but random methods used in the project

public class Extra {

	public Extra() {
		super();
	}

	public java.sql.Date convertDate(java.util.Date uDate) {

		java.sql.Date sqlDate = new java.sql.Date(uDate.getTime());
		return sqlDate;

	}

	public List<OfferPresentationObject> changeToOPO(List<Offer> offers) {
		
		Offer tempOffer;
		OfferPresentationObject tempOPO;
		CategoryServiceImpl csi = new CategoryServiceImpl();
		List<OfferPresentationObject> offers_list = new ArrayList<OfferPresentationObject>();
		
		for (int i = 0; i < offers.size(); i++) {
			tempOffer = offers.get(i);

			Integer oid = tempOffer.getOid();
			String title = tempOffer.getTitle();
			String description = tempOffer.getDescription();
			Date dateAdded = tempOffer.getDateAdded();
			Float price = tempOffer.getPrice();
			String u_username = tempOffer.getUsers_username();
			String cName = csi.getCategoryById(tempOffer.getCategories_cid());
			String base64Image = tempOffer.getBase64Image();

			tempOPO = new OfferPresentationObject(oid, title, description, dateAdded, price, u_username, cName,
					base64Image);
			offers_list.add(tempOPO);
		}
		return offers_list;
	}

}
