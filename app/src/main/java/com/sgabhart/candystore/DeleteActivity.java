package com.sgabhart.candystore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Admin on 10/9/2017.
 */

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView(){
        ArrayList<Candy> candies = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView sv = new ScrollView(this);
        RadioGroup rg = new RadioGroup(this);

        for (Candy c:
             candies) {
            RadioButton rb = new RadioButton(this);
            rb.setId(c.getId());
            rb.setText(c.toString());
            rg.addView(rb);
        }

        RadioButtonHandler rbh = new RadioButtonHandler();
        rg.setOnCheckedChangeListener(rbh);

        Button back = new Button(this);
        back.setText(R.string.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteActivity.this.finish();
            }
        });

        sv.addView(rg);
        layout.addView(sv);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(back, params);

        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup rg, int id){
            dbManager.deleteById(id);
            Toast.makeText(DeleteActivity.this, "Candy deleted", Toast.LENGTH_SHORT).show();

            updateView();
        }
    }
}
