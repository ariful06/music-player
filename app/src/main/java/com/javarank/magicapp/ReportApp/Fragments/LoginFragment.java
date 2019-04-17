package com.javarank.magicapp.ReportApp.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.javarank.magicapp.MainActivity;
import com.javarank.magicapp.R;
import com.javarank.magicapp.ReportApp.Model.Users;
import com.javarank.magicapp.ReportApp.ReportActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    Toolbar toolbar;

    private EditText userEmail, userPassword;
    Button signupButton, loginButton;

    public LoginFragment() {
        // Required empty public constructor
    }

    OnLoginFromActivityListener loginFromActivityListener;

    public interface OnLoginFromActivityListener {
        public void performSignup();
        public void performLogin(String email);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        userEmail = view.findViewById(R.id.edit_text_email);
        userPassword = view.findViewById(R.id.edit_text_password);
        loginButton = view.findViewById(R.id.login_button);
        signupButton = view.findViewById(R.id.signup_button);

        getActivity().setTitle("Login");
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFromActivityListener.performSignup();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
        toolbar.setTitle("Login");


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        loginFromActivityListener = (OnLoginFromActivityListener) activity;
    }

    private void performLogin(){
        final String email = userEmail.getText().toString().trim();
        final String password = userPassword.getText().toString();

        if (!email.equals("") && !password.equals("")){
            Call<Users> call = ReportActivity.apiInterface.performLogin(email,password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body().getResponse().equals("ok")){
                        ReportActivity.prefConfig.writeLoginStatus(true);
                        loginFromActivityListener.performLogin(email);
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                    }else if (response.body().getResponse().equals("fail")){
                        ReportActivity.prefConfig.displayToast("login fail.please try again");
                    }
                }
                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(getActivity(), "some thing went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            if(email.equals("")){
                userEmail.setError("Please Enter Your Email");
            }
            if (password.equals("")){
                userPassword.setError("Please Enter Your Password");
            }
        }
        userEmail.setText("");
        userPassword.setText("");

    }




}
