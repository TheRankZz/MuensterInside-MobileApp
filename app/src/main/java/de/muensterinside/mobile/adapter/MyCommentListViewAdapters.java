package de.muensterinside.mobile.adapter;

import static de.muensterinside.mobile.Constants.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import de.muensterinside.mobile.MuensterInsideAndroidApplication;
import de.muensterinside.mobile.MyCommentActivity;
import de.muensterinside.mobile.PrefsActivity;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.tasks.DeleteCommentTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
 * Adapter zum Anzeigen der eigenen Kommentare
 * @author Julia Bracht, Nicolas Burchert
 */
public class MyCommentListViewAdapters extends BaseAdapter{
    public ArrayList<HashMap<String, String>> list;
    public Activity activity;
    public TextView txtFirst;
    public TextView txtSecond;
    public Button deleteBtn;
    public int id;
    public List<Comment> comments;
    public MuensterInsideAndroidApplication myApp;
    public MyCommentListViewAdapters(Context context, ArrayList<HashMap<String, String>> list,
                                   List<Comment> comments, MuensterInsideAndroidApplication myApp){
        super();
        this.activity= (Activity) context;
        this.list=list;
        this.comments = comments;
        this.myApp = myApp;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            //Hier wird dem Adapter das Aussehen zugeordnet
           convertView=inflater.inflate(R.layout.my_comment_colmn_row, null);

            //TextView für die Darstellung des Kommentars
            txtFirst=(TextView) convertView.findViewById(R.id.comment);

            //TextView für die Darstellung des Datums
            txtSecond=(TextView) convertView.findViewById(R.id.created_at);

            //Button zum Löschen des eigenen Kommentars
            deleteBtn = (Button)convertView.findViewById(R.id.delete_btn);


        }

        //Hashmap für Kommentar und das Datum
        HashMap<String, String> map=list.get(position);

        //Erste TextView wird das Kommentar zugeordnet
        txtFirst.setText(map.get(FIRST_COLUMN));

        //Zweiter TextView wird das Datum zugeordnet
        txtSecond.setText(map.get(SECOND_COLUMN));


        //Wird ausgeführt, wenn auf den Delete Button geklickt wird
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //Die Id des Kommentars
                id = comments.get(position).getId();

                //DeleteCommentTask wird aufgerufen, um Kommentar zu löschen
                DeleteCommentTask deleteCommentTask = new DeleteCommentTask(activity, myApp, id);
                deleteCommentTask.execute();

                //Aktualisiert die Activity, damit gelöschte Kommentare verschwinden
                Intent i = new Intent(activity, MyCommentActivity.class);
                activity.startActivity(i);
                notifyDataSetChanged();

            }
        });

        return convertView;
    }

}
