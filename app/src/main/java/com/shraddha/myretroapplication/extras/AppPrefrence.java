package com.shraddha.myretroapplication.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

//import com.shraddha.myretrofitbasicapplication.R;
import com.shraddha.myretroapplication.R;



public class AppPrefrence {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public AppPrefrence(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(String.valueOf(R.string.s_pref_file),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    //Login Status
    public void setLoginStatus(boolean status)
    {
        editor.putBoolean(String.valueOf(R.string.s_pref_login_status),status);
        editor.commit();
    }
    public boolean getLoginStatus()
    {
        return sharedPreferences.getBoolean(String.valueOf(R.string.s_pref_login_status),false);
    }
    //get Name
    public void setDisplayName(String name){
        editor.putString(String.valueOf(R.string.s_pref_login_name),name);
        editor.commit();
    }
    public String getDisplayName(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_name),"Name");
    }
    //get Email
    public void setDisplayEmail(String email){
        editor.putString(String.valueOf(R.string.s_pref_login_email),email);
        editor.commit();
    }
    public String getDisplayEmail(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_email),"Name");
    }
    //get Date
    public void setDisplayDate(String date){
        editor.putString(String.valueOf(R.string.s_pref_login_date),date);
        editor.commit();
    }
    public String getDisplayDaet(){
        return sharedPreferences.getString(String.valueOf(R.string.s_pref_login_date),"Name");
    }
    public void showToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG);
    }

}