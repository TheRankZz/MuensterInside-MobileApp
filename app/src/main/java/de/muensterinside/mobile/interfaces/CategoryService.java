package de.muensterinside.mobile.interfaces;

import java.util.List;

import de.muensterinside.mobile.entities.Category;

public interface CategoryService {

	Category getCategory(int cat_id);
	
	List<Category> getCategories();
	
	boolean addCategory(Category cat);
	
	boolean removeCategory(int cat_id);
	
}
