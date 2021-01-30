package com.example.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOwnAdapter1 extends RecyclerView.Adapter<MyOwnAdapter1.MyOwnHolder> {

    data[]  data2;
    Context ctx;
    public  MyOwnAdapter1(Context ct,data []s1){
        ctx=ct;
        data2=s1;
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
    public MyOwnAdapter1.MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myinflator=LayoutInflater.from(ctx);
        View myOwnView=myinflator.inflate(R.layout.item_list,parent,false);
        return new MyOwnHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnAdapter1.MyOwnHolder holder, int position) {
        holder.t1.setText(data2[position].state);
        holder.t2.setText(data2[position].confirmed);
        holder.t3.setText(data2[position].active);
        holder.t4.setText(data2[position].recovered);
        holder.t5.setText(data2[position].deaths);
    }

    @Override
    public int getItemCount() {
        return data2.length;
    }
}
