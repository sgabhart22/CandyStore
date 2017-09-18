package com.sgabhart.candystore;

/**
 * Created by Admin on 9/18/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v){
        EditText nameET = (EditText)(findViewById(R.id.input_name));
        EditText priceET = (EditText)(findViewById(R.id.input_price));

        String name = nameET.getText().toString();
        String price = priceET.getText().toString();

        // Insert candy data in db here

        nameET.setText("");
        priceET.setText("");
    }

    public void goBack(View v){
        this.finish();
    }

}
