package de.muensterinside.mobile.adapter;

import static de.muensterinside.mobile.Constants.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.muensterinside.mobile.R;


/**
 * Created by Julia Bracht and Nicolas Burchert
 * Adapter zum Anzeigen der Locations
 * @author Julia Bracht, Nicolas Burchert
 */
public class LocationListViewAdapters extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    public Activity activity;
    public TextView txtFirst;
    public TextView txtSecond;
    public LocationListViewAdapters(Context context, ArrayList<HashMap<String, String>> list){
        super();
        this.activity= (Activity) context;
        this.list=list;
    }


    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    public void add(HashMap<String, String> item){
        this.list.add(item);
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            //Hier wird dem Adapter das Aussehen zugeordnet
            convertView=inflater.inflate(R.layout.category_colmn_row, null);

            // TextView für die Darstellung des Namens der Location
            txtFirst=(TextView) convertView.findViewById(R.id.name);

            //TextView für die Darstellung des Vote-Werts der Location
            txtSecond=(TextView) convertView.findViewById(R.id.vote);

        }

        //Hashmap für den Namen und den Vote-Wert
        HashMap<String, String> map=list.get(position);


        //Erster TevtView wird dem Namen zugeordnet
        txtFirst.setText(map.get(FIRST_COLUMN));

        //Zweiter TextView wird der Vote-Wert zugeordnet
        txtSecond.setText(map.get(SECOND_COLUMN));

        return convertView;
    }

}
