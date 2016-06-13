
package de.muensterinside.mobile;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
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
import de.muensterinside.mobile.MuensterInsideAndroidApplication;

import java.util.ArrayList;

public class CommentAdapters extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private MuensterInsideAndroidApplication myApp;
    private Activity activity;



    public CommentAdapters(ArrayList<String> list, Context context,MuensterInsideAndroidApplication myApp) {
        this.list = list;
        this.context = context;
        this.myApp = myApp;
        this.activity = (Activity) context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = activity.getLayoutInflater();
        if (view == null) {

            view=inflater.inflate(R.layout.colum_comment, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);


        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();


            }
        });


        return view;
    }
}