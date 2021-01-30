package com.example.covid19tracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.TimeoutException;

public class MyOwnAdapter  extends RecyclerView.Adapter<MyOwnAdapter.MyOwnHolder> {

    data[]  data1;
    Context ctx;
    public  MyOwnAdapter(Context ct,data []s1){
        ctx=ct;
        data1=s1;
    }


    public  class MyOwnHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5;
        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);
            t1=(TextView)itemView.findViewById(R.id.statetv);
            t2=(TextView)itemView.findViewById(R.id.confirmedtv);
            t3=(TextView)itemView.findViewById(R.id.activetv);
            t4=(TextView)itemView.findViewById(R.id.recoveredtv);
            t5=(TextView)itemView.findViewById(R.id.deceasedtv);

        }
    }
    @NonNull
    @Override
    public MyOwnAdapter.MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myinflator=LayoutInflater.from(ctx);
        View myOwnView=myinflator.inflate(R.layout.item_list,parent,false);
        return new MyOwnHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnAdapter.MyOwnHolder holder, final int position) {

        holder.t1.setText(data1[position].state);
        holder.t2.setText(data1[position].confirmed);
        holder.t3.setText(data1[position].active);
        holder.t4.setText(data1[position].recovered);
        holder.t5.setText(data1[position].deaths);
        if (data1[position].state.compareTo("Total")!=0 ) {
            holder.t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] arr = new String[6];
                    arr[0] = data1[position].getState();
                    arr[1] = data1[position].getConfirmed();
                    arr[2] = data1[position].getActive();
                    arr[3] = data1[position].getRecovered();
                    arr[4] = data1[position].getDeaths();
                    arr[5]= data1[position].getLastupdatedtime();
                    Intent intent = new Intent(ctx, StateWise.class);
                    intent.putExtra("txt", arr);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data1.length ;
    }
}

