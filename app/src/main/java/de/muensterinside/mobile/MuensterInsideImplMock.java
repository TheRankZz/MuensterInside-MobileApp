package de.muensterinside.mobile;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.interfaces.CategoryService;
import de.muensterinside.mobile.entities.Category;

/**
 * Created By Julia Bracht and Nicolas Burchert
 * Diese Klasse ersetzt die Verbindung zum Server als Mock-Objekt.
 * Die Methoden sind aufrufbar, liefern aber nur starre Testdaten.
 */
public class MuensterInsideImplMock implements CategoryService {

    private List<Category> categoryList;

    /**
     * Konstruktor
     * erstellt 6 "Test" Kategorien
     */
    public MuensterInsideImplMock() {
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

    public List<Category> getCategories(){
        return categoryList;
    }

    public boolean addCategory(Category cat){
        return true;
    }

    public boolean removeCategory(int cat_id){
        return true;
    }

}
