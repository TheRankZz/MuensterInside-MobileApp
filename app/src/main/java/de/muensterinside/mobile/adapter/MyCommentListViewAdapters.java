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
import de.muensterinside.mobile.PrefsActivity;
import de.muensterinside.mobile.R;
import de.muensterinside.mobile.entities.Comment;
import de.muensterinside.mobile.tasks.DeleteCommentTask;

/**
 * Created by Julia Bracht and Nicolas Burchert
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

            convertView=inflater.inflate(R.layout.my_comment_colmn_row, null);

            txtFirst=(TextView) convertView.findViewById(R.id.comment);
            txtSecond=(TextView) convertView.findViewById(R.id.created_at);
            deleteBtn = (Button)convertView.findViewById(R.id.delete_btn);

        }

        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get(FIRST_COLUMN));
        txtSecond.setText(map.get(SECOND_COLUMN));

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                id = comments.get(position).getId();
                DeleteCommentTask deleteCommentTask = new DeleteCommentTask(activity, myApp, id);
                deleteCommentTask.execute();

                Intent i = new Intent(activity, PrefsActivity.class);
                activity.startActivity(i);

            }
        });

        return convertView;
    }

}
