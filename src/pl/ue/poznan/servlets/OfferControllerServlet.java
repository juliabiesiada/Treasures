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
		String myloc = request.getParameter("myimg");
		String myloc2 = request.getParameter("myimg2");
		String myloc3 = request.getParameter("myimg3");
		String myloc4 = request.getParameter("myimg4");
		String myloc5 = request.getParameter("myimg5");
		Float price = null;
		if (!request.getParameter("price").isEmpty()) {
			price = Float.parseFloat(request.getParameter("price"));
		}
		
		Offer offer = new Offer(-1, title, desc, dateAddedSQL, price, username, cid, myloc, myloc2, myloc3, myloc4, myloc5);
		
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
		Extra extra = new Extra();
		
		List<Offer> users_offers = new ArrayList<Offer>();
		List<OfferPresentationObject> offers_list = new ArrayList<OfferPresentationObject>();
		users_offers = osi.getOffersByUsername(username);
		
		offers_list = extra.changeToOPO(users_offers);
		
		request.setAttribute("offers", offers_list);
		request.getRequestDispatcher("OffersList.jsp").forward(request, response);
	}
	
	private void getOfferDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("offer_id");
		String loggedUser = request.getParameter("logged_user");
		Integer oid = Integer.parseInt(id);
		Offer offer = osi.getOfferById(oid);
		CategoryServiceImpl csi = new CategoryServiceImpl();
		
		OfferPresentationObject opo = new OfferPresentationObject(oid,
				offer.getTitle(), offer.getDescription(), offer.getDateAdded(),
				offer.getPrice(), offer.getUsers_username(), 
				csi.getCategoryById(offer.getCategories_cid()), offer.getBase64Image());
		
		request.setAttribute("offer", opo);
		request.setAttribute("logged_user", loggedUser);
		request.getRequestDispatcher("OfferDetails.jsp").forward(request, response);
		
	}
}
