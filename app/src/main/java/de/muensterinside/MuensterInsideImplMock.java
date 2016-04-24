package de.muensterinside;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import de.muensterinside.interfaces.CategoryService;
import de.muensterinside.modals.Category;

/**
 * Diese Klasse ersetzt die Verbindung zum Server als Mock-Objekt.
 * Die Methoden sind aufrufbar, liefern aber nur starre Testdaten.
 */
public class MuensterInsideImplMock implements CategoryService {

    /**
     * Mocking class holds a list with predefined categories
     */
    private List<Category> categoryList;

    public MuensterInsideImplMock() {
        //create 6 test categories
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Essen"));
        categoryList.add(new Category("Party"));
        categoryList.add(new Category("Hotel"));
        categoryList.add(new Category("Shopping"));
        categoryList.add(new Category("Sehenswürdigkeiten"));
        categoryList.add(new Category("Veranstaltungen"));

    }
    public Category getCategory(int cat_id) {
        return categoryList.get(cat_id);
    }

    //returns a copy of the private category list.
    public List<Category> getCategories(){
       return categoryList;
    }

    public boolean addCategory(Category cat){
        //mocking: do nothing!
        return true;
    }

    public boolean removeCategory(int cat_id){
        //mocking: do nothing!
        return true;
    }

}
