package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private  final String url="https://api.covid19india.org/data.json";
data[] covid;
TextView confirmtv,activetv,recoveredtv,deceasedtv,lastupdate;
RecyclerView recycler;
MyOwnAdapter Od;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirmtv=findViewById(R.id.confirmedtv);
        activetv=findViewById(R.id.activetv);
        recoveredtv=findViewById(R.id.recoveredtv);
        deceasedtv=findViewById(R.id.deceasedtv);
        recycler =findViewById(R.id.recycler);
        lastupdate=findViewById(R.id.lastupdate);
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj=new JSONObject(response);
                    String data=obj.getString("statewise");
                    JSONArray arr=new JSONArray(data);
                    arr.remove(arr.length()-2);
                    covid=new data[arr.length()];
                    for(int i=0;i<arr.length();i++){
                        covid[i]=new data();
                        JSONObject object=arr.getJSONObject(i);
                        if(!object.getString("state").toString().equals("State Unassigned")) {
                            covid[i].active = object.getString("active").toString();
                            covid[i].confirmed = object.getString("confirmed").toString();
                            covid[i].deaths = object.getString("deaths").toString();
                            covid[i].recovered = object.getString("recovered").toString();
                            covid[i].state = object.getString("state").toString();
                            String s=object.getString("lastupdatedtime").toString();
                            int time=Integer.parseInt(s.substring(s.length()-8,s.length()-8+2));
                            if(time>=12)
                            covid[i].lastupdatedtime=s+" PM";
                            else
                                covid[i].lastupdatedtime=s+" AM";
                        }
                    }
                    confirmtv.setText(covid[0].confirmed);
                    activetv.setText(covid[0].active);
                    recoveredtv.setText(covid[0].recovered);
                    deceasedtv.setText(covid[0].deaths);
                    lastupdate.setText(lastupdate.getText()+":\n"+covid[0].lastupdatedtime);
                    Od=new MyOwnAdapter(getApplicationContext(),covid);
                    recycler.setAdapter(Od);
                    recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}