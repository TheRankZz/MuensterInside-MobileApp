package de.muensterinside.mobile.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.muensterinside.mobile.CategoryActivity;
import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.entities.Category;

/**
 * Created by Julia Bracht and Nicolas Burchert
 */
public class CategoryTask extends AsyncTask<Void, Void, List<Category>> {
    private MuensterInsideAndroidApplication myApp;
    private List<Category> categories;
    private Context context;
    private ListView listView;
    public static final String TAG = "CategoryTask";


    public CategoryTask(Context context, MuensterInsideAndroidApplication myApp, ListView listView){
        this.context = context;
        this.myApp = myApp;
        this.listView = listView;
    }

    @Override
    protected List<Category> doInBackground(Void... params){
        Log.d(TAG, "doInBackground() gestartet");
        try {
            this.categories = myApp.getMuensterInsideMobile().getCategories();
            Log.i(TAG, "doInBackground() erfolgreich");
            return this.categories;
        }
        catch (Exception e){
            Log.e(TAG, "doInBackground() fehlgeschlagen");
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(List<Category> categories){
        Log.d(TAG, "onPostExecute() gestartet");
        List myList = new ArrayList<String>();
        for(int i=0; i < categories.size(); i++){
            myList.add(categories.get(i).getName());
        }
        final List<Category> test = categories;



        // Es wird ein Adapter erstellt der die listView mit einträgen befüllt
        ArrayAdapter<String> adapter;
        adapter=new ArrayAdapter<String>(context, R.layout.content_item_list_category, myList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /**
         * Wenn ein Eintrag aus der listView ausgewählt wird,
         * soll die CategoryActivity gestartet werden.
         * Zusätzlich soll die Id, von der Category die ausgewählt wurde,
         * an die CategoryActivity mit übergeben werden.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
                Log.d(TAG, "listView.onItemClick() gestartet");
                Intent myIntent = new Intent(context, CategoryActivity.class);
                String name = listView.getAdapter().getItem(arg2).toString();
                int id = 1;
                for(int i=0; i < test.size(); i++){
                    if(test.get(i).getName().equals(name)){
                        id = test.get(i).getId();
                    }
                }
                myIntent.putExtra("selected", id);
                context.startActivity(myIntent);
            }
        });
    }
}
