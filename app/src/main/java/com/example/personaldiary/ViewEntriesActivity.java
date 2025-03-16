package com.example.personaldiary;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewEntriesActivity extends AppCompatActivity {
    ListView listView;
//    TextView count;

    private Context context;

    private DBHandler dbHandler;
    private int length;
    private List<ModelClass> toDos;

   // private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);

        listView = findViewById(R.id.entriesListView);
        context = this;

        dbHandler = new DBHandler(context);
        toDos = new ArrayList<>();
        toDos = dbHandler.getAllToDos();

        Adapter adapter = new Adapter(context, R.layout.entry_list, toDos);
        listView.setAdapter(adapter);
       // backButton = findViewById(R.id.backButton);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ModelClass todo = toDos.get(position);
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
                alertdialog.setTitle(todo.getTitle().toString());
                alertdialog.setMessage(todo.getDescription().toString());

//                alertdialog.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        todo.setImage(System.currentTimeMillis());
//                        dbHandler.updateSingleToDo(todo);
//                        startActivity(new Intent(context, MainActivity.class));
//
//                    }
//                });
                alertdialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteToDo(todo.getId());
                        startActivity(new Intent(context, MainActivity.class));
                    }
                });

//                alertdialog.setNeutralButton("Update", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(context, EditToDo.class);
//                        intent.putExtra("id", String.valueOf(todo.getId()));
//                        startActivity(intent);
//
//                    }
//                });

                alertdialog.show();
               // backButton.setOnClickListener(v -> finish());

            }
        });
    }
}
