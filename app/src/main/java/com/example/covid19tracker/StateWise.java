package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class StateWise extends AppCompatActivity {
    private  String url1="https://api.covid19india.org/state_district_wise.json";
    TextView titletv1,conformtv,activetv,recoveredtv,deceasedtv,lastupdate1;
    data []district1;
    RecyclerView recycle1;
    MyOwnAdapter1 OD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_wise);
        Intent intent =getIntent();
        final String[] state=intent.getStringArrayExtra("txt");
        titletv1=findViewById(R.id.titletv1);
        conformtv=findViewById(R.id.confirmtv1);
        activetv=findViewById(R.id.activetv1);
        recoveredtv=findViewById(R.id.recoveredtv1);
        deceasedtv=findViewById(R.id.deceasedtv1);
        lastupdate1=findViewById(R.id.lastupdate1);
        recycle1=findViewById(R.id.recycler1);
        titletv1.setText(state[0]);
        conformtv.setText(state[1]);
        activetv.setText(state[2]);
        recoveredtv.setText(state[3]);
        deceasedtv.setText(state[4]);
        lastupdate1.setText(lastupdate1.getText()+":\n"+state[5]);
        StringRequest request1 =new StringRequest(url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj=new JSONObject(response);
                    JSONObject obj1=obj.getJSONObject(""+state[0]);
                    JSONObject obj2=obj1.getJSONObject("districtData");
                    JSONArray arr=obj2.names();
                    for(int i=0;i<arr.length();) {
                        if (arr.getString(i).equals("Others") || arr.getString(i).equals("Other State")) {
                            arr.remove(i);
                        }else i++;
                    }
                    district1=new data[arr.length()];
                    for(int i=0;i<arr.length();i++) {
                        district1[i] = new data();
                        district1[i].setState(arr.getString(i));
                        JSONObject obj3 = obj2.getJSONObject(arr.getString(i));
                        district1[i].setActive(obj3.getString("active"));
                        district1[i].setRecovered(obj3.getString("recovered"));
                        district1[i].setDeaths(obj3.getString("deceased"));
                        district1[i].setConfirmed(obj3.getString("confirmed"));
                    }
                    OD=new MyOwnAdapter1(getApplicationContext(),district1);
                    recycle1.setAdapter(OD);
                    recycle1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                } catch (Exception e) {
                    Toast.makeText(StateWise.this, "hello", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue queue1= Volley.newRequestQueue(this);
        queue1.add(request1);
    }
}