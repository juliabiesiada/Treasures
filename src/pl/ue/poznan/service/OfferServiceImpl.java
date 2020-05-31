package pl.ue.poznan.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.ue.poznan.dao.OfferDAOImpl;
import pl.ue.poznan.model.Offer;

public class OfferServiceImpl implements OfferService {
	
	@Override
	public Offer getOfferById(Integer id) throws IOException {
		OfferDAOImpl odi = new OfferDAOImpl();
		Offer offer = odi.getOfferById(id);
		return offer;
	}

	@Override
	public String addOffer(Offer offer) {
		String error = validateOffer(offer);
		if (error.isEmpty()) {
			OfferDAOImpl odi = new OfferDAOImpl();
			try {
				error = odi.addOffer(offer);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return error;
	}


	@Override
	public String updateOfer(Offer offer) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String validateOffer (Offer offer) {
		String result = "";
		String title = offer.getTitle();
		Integer cid = offer.getCategories_cid();
		Float price = offer.getPrice();
		
		if (title.isEmpty()) {
			result = "Please enter the title";
			return result;
		}
		
		if (price==null) {
			result = "Please enter the price";
			return result;
		}
		
		if (cid!=1 & cid!=2 & cid!=3 & cid!=4 & cid!=5) {
			result = "Invalid category";
			return result;
		}return result;
	}

	@Override
	public List<Offer> getOffers() throws IOException {
		OfferDAOImpl odi = new OfferDAOImpl();
		List<Offer> offers = new ArrayList<Offer>();
		offers = odi.getOffers();
		return offers;
	}

	@Override
	public List<Offer> getOffersByUsername(String username) throws IOException {
		OfferDAOImpl odi = new OfferDAOImpl();
		List<Offer> offers = new ArrayList<Offer>();
		offers = odi.getOffers();
		List<Offer> users_offers = new ArrayList<Offer>();
		Offer tempOffer;
		String tempUsername;
		
		//loop to get all offers with given username
		for (int i = 0; i < offers.size(); i++) {
			tempOffer = offers.get(i);
			tempUsername = tempOffer.getUsers_username();
			if (tempUsername.equals(username)) {
				users_offers.add(tempOffer);
			}
		}
		return users_offers;
	}

	@Override
	public List<Offer> getNewestOffers(String city, String username) throws IOException {
		OfferDAOImpl odi = new OfferDAOImpl();
		List<Offer> offers = new ArrayList<Offer>();
		offers = odi.getNewestOffers(city, username);
		return offers;
	}

}
