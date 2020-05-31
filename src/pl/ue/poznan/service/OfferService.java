package pl.ue.poznan.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import pl.ue.poznan.model.Offer;

public interface OfferService {
	Offer getOfferById(Integer id) throws IOException;
	String addOffer(Offer offer) throws FileNotFoundException;
	String updateOfer(Offer offer);
	List<Offer> getOffers() throws IOException;
	List<Offer> getOffersByUsername(String username) throws IOException;
	List<Offer> getNewestOffers(String city, String username) throws IOException;
}
