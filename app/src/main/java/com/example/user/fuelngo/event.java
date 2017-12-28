package com.example.user.fuelngo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.user.fuelngo.R.id.endd;
import static com.example.user.fuelngo.R.id.endt;
import static com.example.user.fuelngo.R.id.startd;
import static com.example.user.fuelngo.R.id.startt;

public class event extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private DatabaseReference myref;
    ImageButton btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true); linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        myref= FirebaseDatabase.getInstance().getReference().child("Events");
        btnadd = (ImageButton) findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(event.this, addevent.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog,BlogViewHolder> recyclerAdapter=new FirebaseRecyclerAdapter<Blog,BlogViewHolder>(
                Blog.class,
                R.layout.individual_row,
                BlogViewHolder.class,
                myref
        )

        {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setAddress(model.getAddress());
                viewHolder.setStartdate(model.getStartdate());
                viewHolder.setEnddate(model.getEnddate());
                viewHolder.setStarttime(model.getStarttime());
                viewHolder.setEndtime(model.getEndtime());
            }
        }; recyclerView.setAdapter(recyclerAdapter);
    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        //ImageView imageView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            View mView=itemView;

        }
        public void setTitle(String title)
        {
            TextView tveventname = (TextView) itemView.findViewById(R.id.title);
            tveventname.setText(title);
        }
        public void setDescription(String description)
        {
            TextView tvdesc = (TextView) itemView.findViewById(R.id.description);
            tvdesc.setText(description);
        }
        public void setLocation(String location)
        {
            TextView tvlocation = (TextView) itemView.findViewById(R.id.location);
            tvlocation.setText(location);
        }
        public void setAddress(String address)
        {
            TextView tvaddress = (TextView) itemView.findViewById(R.id.address);
            tvaddress.setText(address);
        }
        public void setStartdate(String startdate)
        {
            TextView tvstartdate = (TextView) itemView.findViewById(startd);
            tvstartdate.setText(startdate + "");
        }
        public void setEnddate(String enddate)
        {
            TextView tvenddate = (TextView) itemView.findViewById(endd);
            tvenddate.setText("To " + enddate);
        }
        public void setStarttime(String starttime)
        {
            TextView tvstarttime = (TextView) itemView.findViewById(startt);
            tvstarttime.setText(starttime + "");
        }

        public void setEndtime(String endtime)
        {
            TextView tvendtime = (TextView) itemView.findViewById(endt);
            tvendtime.setText(" To " + endtime);
        }
          /* public void setImage(String image)
        {
            Picasso.with(mView.getContext())
                    .load(image)
                    .into(imageView);
        } */

    }
}

