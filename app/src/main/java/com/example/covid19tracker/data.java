package com.example.covid19tracker;

import com.android.volley.toolbox.StringRequest;

public class data {
public String active;
    public String confirmed;
    public String deaths;
    public String recovered;
    public String state;
    public String lastupdatedtime;
    public data() {
    }
public String getLastupdatedtime(){
        return lastupdatedtime;
}
public void setLastupdatedtime(String lastupdatedtime){
        this.lastupdatedtime=lastupdatedtime;
}
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
