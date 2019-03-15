package com.colin.maplesandbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button button_add, button_view;
    private EditText editText_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_name = (EditText) findViewById(R.id.editText_name);
        button_add = (Button) findViewById(R.id.button_add);
        button_view = (Button) findViewById(R.id.button_view);
        mDatabaseHelper = new DatabaseHelper(this);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText_name.getText().toString();
                if(editText_name.length() != 0){
                    AddData(newEntry);
                    editText_name.setText("");
                }else{
                    toastMessage("You must put something in the field!");
                }
            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String newEntry){
        boolean InsertData = mDatabaseHelper.addData(newEntry);

        if(InsertData){
            toastMessage("Data Successfully Inserted");
        }else{
            toastMessage("Something went wrong");
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
