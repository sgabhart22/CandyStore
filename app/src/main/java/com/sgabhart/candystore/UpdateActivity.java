package com.sgabhart.candystore;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Admin on 10/9/2017.
 */

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    public void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    } // onCreate

    public void updateView(){
        ArrayList<Candy> candies = dbManager.selectAll();
        if(candies.size() > 0){
            ScrollView sv = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(candies.size());
            grid.setColumnCount(4);

            TextView[] ids = new TextView[candies.size()];
            EditText[][] candyData = new EditText[candies.size()][2];
            Button[] buttons = new Button[candies.size()];
            ButtonHandler bh = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (Candy c:
                 candies) {
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + c.getId());

                candyData[i][0] = new EditText(this);
                candyData[i][1] = new EditText(this);
                candyData[i][0].setText(c.getName());
                candyData[i][1].setText("" + c.getPrice());
                candyData[i][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                candyData[i][0].setId(10 * c.getId());
                candyData[i][1].setId(10 * c.getId() + 1);

                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(c.getId());

                buttons[i].setOnClickListener(bh);

                grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(candyData[i][0], (int)(width * .4), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(candyData[i][1], (int)(width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int)(width * .35), ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }

            sv.addView(grid);
            setContentView(sv);
        } // if
    } // updateView

    private class ButtonHandler implements View.OnClickListener{
        public void onClick(View v){
            int candyId = v.getId();
            EditText nameET = (EditText)(findViewById(10 * candyId));
            EditText priceET = (EditText)(findViewById(10 * candyId + 1));

            String name = nameET.getText().toString();
            String priceString = priceET.getText().toString();

            try {
                double price = Double.parseDouble(priceString);
                dbManager.updateById(candyId, name, price);
                Toast.makeText(UpdateActivity.this, "Candy updated", Toast.LENGTH_SHORT).show();

                updateView();
            } catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this, "Price error", Toast.LENGTH_SHORT).show();
            }
        } // onClick
    } // ButtonHandler
}
