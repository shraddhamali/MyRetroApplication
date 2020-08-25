package com.shraddha.myretroapplication.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.shraddha.myretroapplication.R;
import com.shraddha.myretroapplication.activites.MainActivity;
import com.shraddha.myretroapplication.model.User;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegestrestionFragment extends Fragment {

    EditText name_input,email_input,phone_input,password_input;
    Button register_button;
    public RegestrestionFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_regestrestion,container,false);
        name_input = view.findViewById(R.id.nameInput);
        email_input = view.findViewById(R.id.emailInput);
        phone_input = view.findViewById(R.id.phoneInput);
        password_input = view.findViewById(R.id.passwordInput);
        register_button = view.findViewById(R.id.regBtn);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regiterUser();
            }
        });


        return view;
    }

    private void regiterUser() {
        String name=name_input.getText().toString().trim();
        String email=email_input.getText().toString().trim();
        String phone=phone_input.getText().toString().trim();
        String password=password_input.getText().toString().trim();
        if (TextUtils.isEmpty(name))
        {
            MainActivity.appPrefrence.showToast("Enter your name");
        }
        if (TextUtils.isEmpty(email))
        {
            MainActivity.appPrefrence.showToast("Enter your email id");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            MainActivity.appPrefrence.showToast("Email id is invalid");
        }else if (TextUtils.isEmpty(phone))
        {
            MainActivity.appPrefrence.showToast("Enter your phone number");
        }else if (TextUtils.isEmpty(password))
        {
            MainActivity.appPrefrence.showToast("Enter your passworrd");
        }else if (TextUtils.isEmpty(name))
        {
            MainActivity.appPrefrence.showToast("Enter your name");
        }
        else if (password.length()<6)
        {
            MainActivity.appPrefrence.showToast("Password too short!");
        }
        else{

            //data on server
            Call<User> userCall = MainActivity.serviceApi.doRegisteration(name,email,phone,password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call,@NonNull Response<User> response) {
                    if(response.body().getResponse().matches("inserted"))
                    {
                        show_Message("Successfully Registered!!","Welcome "+name);
                        // Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    }else if(response.body().getResponse().matches("exists"))
                    {
                        show_Message("Already registered user!!","Welcome "+name);
                        //  Toast.makeText(getActivity(), "User already exists!!!!", Toast.LENGTH_SHORT).show();
                    }

                    Log.i("My response",response.body().getResponse());
                }

                @Override
                public void onFailure(@NonNull Call<User> call,@NonNull Throwable t) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    System.out.println("MyError"+t.getMessage());
                }
            });
        }
    }
    private void show_Message(String title, String input)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(input);
        builder.setCancelable(true);
        builder.show();
    }
    private void clearText () {
        email_input.setText("");
        name_input.setText("");
        password_input.setText("");
        phone_input.requestFocus();
    }
}