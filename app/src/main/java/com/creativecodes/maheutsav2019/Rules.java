package com.creativecodes.maheutsav2019;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class Rules extends AppCompatActivity {

    TextView txtR1,txtR2,txtR3,txtR4,txtR5,txtR6,txtR7,txtR8,txtR9,txtR10;
    TextView txtR11,txtR12,txtR13,txtR14,txtR15,txtR16,txtR17,txtR18,txtR19,txtR20;
    TextView txtR21,txtR22,txtR23,txtR24,txtDisclaimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        //Textview
        txtDisclaimer=(TextView)findViewById(R.id.txtDisclaimer);
        txtR1=(TextView)findViewById(R.id.txtRule1);
        txtR2=(TextView)findViewById(R.id.txtRule2);
        txtR3=(TextView)findViewById(R.id.txtRule3);
        txtR4=(TextView)findViewById(R.id.txtRule4);
        txtR5=(TextView)findViewById(R.id.txtRule5);
        txtR6=(TextView)findViewById(R.id.txtRule6);
        txtR7=(TextView)findViewById(R.id.txtRule7);
        txtR8=(TextView)findViewById(R.id.txtRule8);
        txtR9=(TextView)findViewById(R.id.txtRule9);
        txtR10=(TextView)findViewById(R.id.txtRule10);
        txtR11=(TextView)findViewById(R.id.txtRule11);
        txtR12=(TextView)findViewById(R.id.txtRule12);
        txtR13=(TextView)findViewById(R.id.txtRule13);
        txtR14=(TextView)findViewById(R.id.txtRule14);
        txtR15=(TextView)findViewById(R.id.txtRule15);
        txtR16=(TextView)findViewById(R.id.txtRule16);
        txtR17=(TextView)findViewById(R.id.txtRule17);
        txtR18=(TextView)findViewById(R.id.txtRule18);
        txtR19=(TextView)findViewById(R.id.txtRule19);
        txtR20=(TextView)findViewById(R.id.txtRule20);
        txtR21=(TextView)findViewById(R.id.txtRule21);
        txtR22=(TextView)findViewById(R.id.txtRule22);
        txtR23=(TextView)findViewById(R.id.txtRule23);
        txtR24=(TextView)findViewById(R.id.txtRule24);

        //textview Justification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txtDisclaimer.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR1.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR2.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR3.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR4.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR5.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR6.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR7.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR8.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR9.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR10.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR11.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR12.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR13.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR14.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR15.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR16.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR17.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR18.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR19.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR20.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR21.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR22.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR23.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            txtR24.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    @Override
    public void onBackPressed() {
//        Intent i = new Intent(this, Home_screen.class);
//        startActivity(i);
        finish();
        //super.onBackPressed();
    }


}
