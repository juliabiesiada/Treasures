package pl.ue.poznan.dao;

import java.util.List;

import pl.ue.poznan.model.Offer;

public interface OfferDAO {

	Offer getOfferById(Integer id);
	String addOffer(Offer offer);
	String updateOfer(Offer offer);
	List<Offer> getOffers();

	
}
