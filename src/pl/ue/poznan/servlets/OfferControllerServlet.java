package pl.ue.poznan.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.ue.poznan.model.Extra;
import pl.ue.poznan.model.Offer;
import pl.ue.poznan.model.OfferPresentationObject;
import pl.ue.poznan.model.User;
import pl.ue.poznan.service.CategoryServiceImpl;
import pl.ue.poznan.service.OfferServiceImpl;
import pl.ue.poznan.service.UserServiceImpl;

@WebServlet("/OfferControllerServlet")
public class OfferControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OfferServiceImpl osi;

	public OfferControllerServlet() {
		osi = new OfferServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");

			if (!theCommand.isEmpty()) {
				switch (theCommand) {
				case "ADD_OFFER_FORM":
					addOfferForm(request, response);
					break;
				case "ADD_OFFER":
					addOffer(request, response);
					break;
				case "GET_USERS_OFFERS":
					getUsersOffers(request, response);
					break;
				case "OFFER_DETAILS":
					getOfferDetails(request, response);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void addOfferForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		UserServiceImpl usi = new UserServiceImpl();
		User user = usi.getUserByUsername(username);

		request.setAttribute("user", user);
		request.getRequestDispatcher("AddOffer.jsp").forward(request, response);
	}
	
	private void addOffer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Extra extra = new Extra();
		String error; 
		
		String username = request.getParameter("username");
		UserServiceImpl usi = new UserServiceImpl();
		User user = usi.getUserByUsername(username);
		//Integer uid = user.getId();
		
		String title = request.getParameter("title").trim();
		String desc = request.getParameter("desc").trim();
		Integer cid = Integer.parseInt(request.getParameter("category"));
		Date dateAdded = new Date();
		java.sql.Date dateAddedSQL = extra.convertDate(dateAdded);
		Float price = null;
		if (!request.getParameter("price").isEmpty()) {
			price = Float.parseFloat(request.getParameter("price"));
		}
		
		Offer offer = new Offer(-1, title, desc, dateAddedSQL, price, username, cid);
		
		error = osi.addOffer(offer);
		
		if (error.isEmpty()) {
			request.setAttribute("message", "Offer added");
			request.setAttribute("user", user);
			getUsersOffers(request, response);
		}else {
			request.setAttribute("error", error);
			request.setAttribute("user", user);
			request.getRequestDispatcher("AddOffer.jsp").forward(request, response);
		}
		
		
	}
	
	private void getUsersOffers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		UserServiceImpl usi = new UserServiceImpl();
		//User user = usi.getUserByUsername(username);
		//Integer uid = user.getId();
		Offer tempOffer;
		OfferPresentationObject tempOPO;
		CategoryServiceImpl csi = new CategoryServiceImpl();
		
		List<Offer> users_offers = new ArrayList<Offer>();
		List<OfferPresentationObject> offers_list = new ArrayList<OfferPresentationObject>();
		users_offers = osi.getOffersByUsername(username);
		
		//transforming offers into OfferPresentationObjects
		for (int i = 0; i < users_offers.size(); i++) {
			tempOffer = users_offers.get(i);
			
			Integer oid = tempOffer.getOid();
			String title = tempOffer.getTitle();
			String description = tempOffer.getDescription();
			Date dateAdded = tempOffer.getDateAdded();
			Float price = tempOffer.getPrice();
			String u_username = tempOffer.getUsers_username();
			String cName = csi.getCategoryById(tempOffer.getCategories_cid());
			
			tempOPO = new OfferPresentationObject(oid, title, description, dateAdded,
					price, u_username, cName);
			offers_list.add(tempOPO);
		}
		
		request.setAttribute("offers", offers_list);
		request.getRequestDispatcher("OffersList.jsp").forward(request, response);
	}
	
	private void getOfferDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("offer_id");
		Integer oid = Integer.parseInt(id);
		Offer offer = osi.getOfferById(oid);
		CategoryServiceImpl csi = new CategoryServiceImpl();
		
		OfferPresentationObject opo = new OfferPresentationObject(oid,
				offer.getTitle(), offer.getDescription(), offer.getDateAdded(),
				offer.getPrice(), offer.getUsers_username(), 
				csi.getCategoryById(offer.getCategories_cid()));
		
		request.setAttribute("offer", opo);
		request.getRequestDispatcher("OfferDetails.jsp").forward(request, response);
		
	}
}
