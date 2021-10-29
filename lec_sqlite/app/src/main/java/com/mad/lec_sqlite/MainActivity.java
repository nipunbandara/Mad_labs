package com.mad.lec_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtName, nMark;
    Button btnAdd, btnView, btnUpdate, btnDelete;

    DBHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DBHandler(this);
        txtName = findViewById(R.id.name);
        nMark = findViewById(R.id.marks);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_del);
        btnUpdate = findViewById(R.id.btn_update);
        btnView = findViewById(R.id.btn_disp);
    }

    protected void onResume(){
        super.onResume();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myDb.insertData(txtName.getText().toString(), nMark.getText().toString())) {
                    Toast.makeText(MainActivity.this, "A new student record inserted", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Insetion Failed", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener((view) = {
                Cursor result = myDb.selectAll();
                if(result != null){
                    while(result.moveToNext()){
                        Log.d("Student_record", "Student" + result.getString(i:0) + " " + result.getString(i:1) + " " + result.getSring(i:2));

                    }
                }
                else{
                    showMessage();
                }

        });

        btnUpdate.setOnClickListener((view) -> {
            int rowId = myDb.updateInfo(txtName.getText().toString(), nMark.getText().toString());
            if(rowId >= 1)
                Toast.makeText(MainActivity.this, "Successfully updated a record ", Toast.LENGTH_LONG).show();
        });

        btnDelete.setOnClickListener((view -> {
            int rowId = myDb.deleteInfo(txtName.getText().toString());
            if(rowId >= 1)
                Toast.makeText(MainActivity.this, "Successfully deleted a record ", Toast.LENGTH_LONG).show();
        }));
    }

    private void showMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Error");
        builder.setMessage("No Students available");
        builder.show();

    }
}














