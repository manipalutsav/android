package com.creativecodes.maheutsav2019;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {

    NavigationView navigationView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("About Developers");

        recyclerView=(RecyclerView) findViewById(R.id.contact_us);
        RecyclerViewAdapterContact recyclerViewAdapter=new RecyclerViewAdapterContact(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        //final TextView contact_no;
        //contact_no=(TextView)findViewById(R.id.contact_no);
    }




}

class RecyclerViewAdapterContact extends RecyclerView.Adapter<RecyclerViewAdapterContact.MyViewHolder> {

    Context context;
    //Dialog myDialog;
    //List<Contact> mData;.
    Integer [] contactPhoto={R.drawable.ic_facebook,R.drawable.ppk,R.drawable.creative};
    String [] contactName={"Sambit Dash","Dr.Poornima K Kundapur","Mohammed Alfaz"};

    String[] role={"CCC in charge","Development Head","App Developer"};
    String[] contactEmail={"sambit.dash@manipal.edu","poornima.girish@manipal.edu","sheikmohammedalfaz@gmail.com"};
    String[] contactNo={"Department of Biochemistry,MMMC,Manipal","Associate professor, MIT ,Manipal","MCA , Manipal"};

    public RecyclerViewAdapterContact(Context context) {
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.contact_layout,parent,false);
        final MyViewHolder myViewHolder=new MyViewHolder(v);

        /*myDialog=new Dialog(context);
        myDialog.setContentView(R.layout.event_dialog_layout);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));*/


        myViewHolder.contact_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+contactNo[myViewHolder.getAdapterPosition()]));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

       /* myViewHolder.contact_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
                //emailintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailintent.setType("plain/text");
                emailintent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { contactEmail[myViewHolder.getAdapterPosition()] });
                emailintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                emailintent.putExtra(android.content.Intent.EXTRA_TEXT,"Mail Body");
                context.startActivity(android.content.Intent.createChooser(emailintent, "Send mail..."));
            }
        });*/

        //myViewHolder.contact_email.setMovementMethod(LinkMovementMethod.getInstance());


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.contact_img.setImageResource(contactPhoto[position]);
        holder.contact_name.setText(contactName[position]);
        holder.contact_post.setText(role[position]);

        holder.contact_no.setText(contactNo[position]);
        holder.contact_email.setText(contactEmail[position]);
    }

    @Override
    public int getItemCount() {
        return contactName.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView contact_name;
        private TextView contact_post;
        private TextView contact_no;
        private TextView contact_email;
        private ImageView contact_img;
        public MyViewHolder(final View itemView) {
            super(itemView);
            //item_event=(LinearLayout)itemView.findViewById(R.id.eventLayout);
            contact_img=(ImageView)itemView.findViewById(R.id.contact_img);
            contact_name=(TextView)itemView.findViewById(R.id.contact_name);
            contact_post=(TextView)itemView.findViewById(R.id.contact_post);

            contact_no=(TextView)itemView.findViewById(R.id.contact_no);
            contact_email=(TextView)itemView.findViewById(R.id.contact_email);
            //contact_email.setAutoLinkMask(Linkify.EMAIL_ADDRESSES);
        }
    }
}