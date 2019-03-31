package com.creativecodes.maheutsav2019;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


public class Feedback extends AppCompatActivity {
    private static Button btnsub;

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
        //super.onBackPressed();
    }

    private static RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


    }
    public void listenerForRatingbar() {

        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4);


        ratingBar1.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        //Toast.makeText(getApplicationContext(), String.valueOf(ratingBar1.getRating()), Toast.LENGTH_SHORT).show();
                        LayerDrawable stars = (LayerDrawable) ratingBar1.getProgressDrawable();
                        stars.getDrawable(2).setColorFilter(Color.parseColor("#FFE81B"), PorterDuff.Mode.SRC_ATOP);
                        validate();
                    }
                }
        );


        ratingBar2.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        //Toast.makeText(getApplicationContext(), String.valueOf(ratingBar2.getRating()), Toast.LENGTH_SHORT).show();
                        LayerDrawable stars = (LayerDrawable) ratingBar2.getProgressDrawable();
                        stars.getDrawable(2).setColorFilter(Color.parseColor("#FFE81B"), PorterDuff.Mode.SRC_ATOP);
                        validate();

                    }
                }
        );

        ratingBar3.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        //Toast.makeText(getApplicationContext(), String.valueOf(ratingBar3.getRating()), Toast.LENGTH_SHORT).show();
                        LayerDrawable stars = (LayerDrawable) ratingBar3.getProgressDrawable();
                        stars.getDrawable(2).setColorFilter(Color.parseColor("#FFE81B"), PorterDuff.Mode.SRC_ATOP);
                        validate();
                    }
                }
        );

        ratingBar4.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        //Toast.makeText(getApplicationContext(), String.valueOf(ratingBar4.getRating()), Toast.LENGTH_SHORT).show();
                        LayerDrawable stars = (LayerDrawable) ratingBar4.getProgressDrawable();
                        stars.getDrawable(2).setColorFilter(Color.parseColor("#FFE81B"), PorterDuff.Mode.SRC_ATOP);
                        validate();
                    }
                }
        );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void validate()
    {
        btnsub = (Button) findViewById(R.id.button);
        if((ratingBar1.getRating()==0.0)||(ratingBar2.getRating()==0.0)||(ratingBar3.getRating()==0.0)||(ratingBar4.getRating()==0.0)){
            btnsub.setEnabled(false);
        }
        else
        {
            btnsub.setEnabled(true);
        }
    }
    /*public void onButtonClickListener() {
        btnsub = (Button) findViewById(R.id.button);
        btnsub.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String q1,q2,q3,q4,comment;
                        q1=String.valueOf(ratingBar1.getRating());
                        q2=String.valueOf(ratingBar2.getRating());
                        q3=String.valueOf(ratingBar3.getRating());
                        q4=String.valueOf(ratingBar4.getRating());
                        comment=((EditText) findViewById(R.id.comment)).getText().toString().trim();
                        Toast.makeText(getApplicationContext(),"Submitted",Toast.LENGTH_LONG).show();
                        //BackgroundFeedback backgroundWorker = new BackgroundFeedback(dialog.getContext());
                        //backgroundWorker.execute("submit",user_id,q1,q2,q3,q4,comment);
                    }
                }
        );
    }*/

}
