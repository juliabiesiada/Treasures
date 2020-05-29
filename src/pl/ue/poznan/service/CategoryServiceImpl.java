package pl.ue.poznan.service;

import pl.ue.poznan.dao.CategoryDAOImpl;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public String getCategoryById(Integer cid) {
		CategoryDAOImpl cdi = new CategoryDAOImpl();
		String name = cdi.getCategoryById(cid);
		if (!name.equals("ERROR")) {
			return name;
		}else {
			return "Error while getting the category from the database";
		}
		
	}

}
