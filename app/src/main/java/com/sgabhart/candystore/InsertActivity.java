package com.sgabhart.candystore;

/**
 * Created by Admin on 9/18/2017.
 */

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v){
        EditText nameET = (EditText)(findViewById(R.id.input_name));
        EditText priceET = (EditText)(findViewById(R.id.input_price));

        String name = nameET.getText().toString();
        String priceString = priceET.getText().toString();

        // Insert new candy data in db
        try{
            double price = Double.parseDouble(priceString);
            Candy candy = new Candy(0, name, price);
            dbManager.insert(candy);
            Toast.makeText(this, "Candy added", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException nfe) {
            Toast.makeText(this, "Price error", Toast.LENGTH_SHORT).show();
        }

        nameET.setText("");
        priceET.setText("");
    }

    public void goBack(View v){
        this.finish();
    }

}
