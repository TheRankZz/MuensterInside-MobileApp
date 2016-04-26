package de.muensterinside.mobile.interfaces;

import java.util.List;
import de.muensterinside.mobile.modals.Category;

public interface CategoryService {

	public Category getCategory(int cat_id);
	
	public List<Category> getCategories();
	
	public boolean addCategory(Category cat);
	
	public boolean removeCategory(int cat_id);
	
}
