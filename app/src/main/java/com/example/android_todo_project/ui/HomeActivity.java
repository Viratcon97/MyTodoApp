package com.example.android_todo_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android_todo_project.R;
import com.example.android_todo_project.TodoAdapter;
import com.example.android_todo_project.TodoData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    final Context context = this;
    ImageButton addTodo;

    SQLiteDatabase myDatabase;

    TodoAdapter todoAdapter;
    List<TodoData> todoDataArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addTodo = findViewById(R.id.addTodo);

        myDatabase = openOrCreateDatabase("MyDatabase", MODE_PRIVATE, null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Todo (id INTEGER PRIMARY KEY AUTOINCREMENT, todoTitle TEXT, todoDescription TEXT);");

        //Fetch Data
        GridView gridview = findViewById(R.id.gridView);

        todoDataArrayList = new ArrayList<>();
        todoAdapter = new TodoAdapter(this, R.layout.layout_todo, todoDataArrayList);

        Cursor cursor = myDatabase.rawQuery("SELECT * FROM Todo", null);
        while (cursor.moveToNext()){
            todoDataArrayList.add(new TodoData(cursor.getString(1), cursor.getString(2)));
        }
        todoAdapter.notifyDataSetChanged();

        gridview.setAdapter(todoAdapter);
        todoAdapter.notifyDataSetChanged();

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_add_todo);
                ImageButton cancelBtn = dialog.findViewById(R.id.btnClose);
                Button addBtn = dialog.findViewById(R.id.btnAdd);
                EditText etTitle = dialog.findViewById(R.id.etTitle);
                EditText etDescription = dialog.findViewById(R.id.etDescription);

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String title = etTitle.getText().toString();
                        String description = etDescription.getText().toString();

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("todoTitle", title);
                        contentValues.put("todoDescription", description);
                        long newRowId = myDatabase.insert("Todo", null, contentValues);
                        if (newRowId != -1) {
                            Toast.makeText(HomeActivity.this, "Saved successfully", Toast.LENGTH_LONG).show();
                            todoDataArrayList.add(todoDataArrayList.size(),new TodoData(title, description));
                            todoAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(HomeActivity.this, "Error!", Toast.LENGTH_LONG).show();
                        }
                        todoAdapter.notifyDataSetChanged();

                    }
                });
                dialog.setCancelable(false);
                dialog.show();
                todoAdapter.notifyDataSetChanged();

            }
        });
        todoAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.aboutUs) {
            Navigation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Navigation() {
        Intent i = new Intent(getApplicationContext(), AboutUsActivity.class);
        startActivity(i);
    }
}