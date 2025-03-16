package com.example.personaldiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<ModelClass> {

    private Context context;
    private int resource;
    private List<ModelClass> todos;
    //If something wrong make this public / default

    public Adapter(Context context, int resource, List<ModelClass> todos){

        // resource = single_todo.xml
        super(context, resource, todos);
        this.context = context;
        this.resource = resource;
        this.todos = todos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView title = row.findViewById(R.id.textView);
        TextView description = row.findViewById(R.id.textView5);
        TextView pageCreatedDate = row.findViewById(R.id.textView3);
        TextView pageCreatedTime=row.findViewById(R.id.textView4);

        // todos [obj1, obj2, obj3]

        ModelClass toDo = todos.get(position); //When first time run the position is zero
        title.setText(toDo.getTitle());
        description.setText(toDo.getDescription());
        pageCreatedDate.setText(toDo.getDate());
        pageCreatedTime.setText(toDo.getTime());
//        imageView.setVisibility(row.INVISIBLE);
//
//        if(toDo.getDate() > 0){
//            imageView.setVisibility(View.VISIBLE);
//        }
        return row;


    }
}
