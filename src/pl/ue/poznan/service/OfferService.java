package pl.ue.poznan.service;

import java.util.List;

import pl.ue.poznan.model.Offer;

public interface OfferService {
	Offer getOfferById(Integer id);
	String addOffer(Offer offer);
	String updateOfer(Offer offer);
	List<Offer> getOffers();
	List<Offer> getOffersByUsername(String username);
}
