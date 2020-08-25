package com.shraddha.myretroapplication.activites;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.shraddha.myretroapplication.R;
import com.shraddha.myretroapplication.constant.Constant;
import com.shraddha.myretroapplication.extras.AppPrefrence;
import com.shraddha.myretroapplication.fragment.LoginFragment;
import com.shraddha.myretroapplication.fragment.ProfileFragment;
import com.shraddha.myretroapplication.fragment.RegestrestionFragment;
//import com.shraddha.myretroapplication.fragment.RegisterFragment;
import com.shraddha.myretroapplication.services.MyInterface;
import com.shraddha.myretroapplication.services.RetrofitClient;
import com.shraddha.myretroapplication.services.ServiceApi;


public class MainActivity extends AppCompatActivity implements MyInterface {

    FrameLayout container_fragment;
    public static AppPrefrence appPrefrence;
    public static ServiceApi serviceApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container_fragment = findViewById(R.id.fragment_container);
        appPrefrence = new AppPrefrence(this);
        serviceApi= RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        if(container_fragment!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            if(appPrefrence.getLoginStatus())
            {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new ProfileFragment())
                        .commit();


            }else{
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment())
                        .commit();


            }
        }

    }

    @Override
    public void register() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new RegestrestionFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void login(String name, String email, String created_at) {
        appPrefrence.setLoginStatus(true);
        appPrefrence.setDisplayName(name);
        appPrefrence.setDisplayEmail(email);
        appPrefrence.setDisplayDate(created_at);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ProfileFragment())

                .commit();


    }

    @Override
    public void logout() {
        appPrefrence.setLoginStatus(false);
        appPrefrence.setDisplayName("Name");
        appPrefrence.setDisplayEmail("Email");
        appPrefrence.setDisplayDate("Date");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())

                .commit();

    }


}