package de.muensterinside.interfaces;

import java.util.List;
import de.muensterinside.modals.Category;

public interface CategoryService {

	public Category getCategory(int cat_id);
	
	public List<Category> getCategories();
	
	public boolean addCategory(Category cat);
	
	public boolean removeCategory(int cat_id);
	
}
