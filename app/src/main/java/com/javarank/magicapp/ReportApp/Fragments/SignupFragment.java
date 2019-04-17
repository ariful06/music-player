package com.javarank.magicapp.ReportApp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.javarank.magicapp.R;
import com.javarank.magicapp.ReportApp.Model.Users;
import com.javarank.magicapp.ReportApp.ReportActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment {


    EditText userName, userEmail, userPassword, userConfirmPassword;
    Toolbar toolbar;
    Button signup;

    FragmentManager fragmentManager;

    public SignupFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        userName = view.findViewById(R.id.username);
        userEmail = view.findViewById(R.id.email);
        userPassword = view.findViewById(R.id.edit_text_password);
        userConfirmPassword = view.findViewById(R.id.edit_text_confirm_password);
        signup = view.findViewById(R.id.button_register);

        getActivity().setTitle("Registration");
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        toolbar.setTitle("Sign Up");


        return view;
    }

    public void performRegistration() {
        final String name = userName.getText().toString();
        final String email = userEmail.getText().toString();
        final String password = userPassword.getText().toString();
        final String confirmPassword = userConfirmPassword.getText().toString();
        if (!name.equals("") && !email.equals("") && !password.equals("") && !confirmPassword.equals("")) {
            if (password.equals(confirmPassword)) {
                Call<Users> call = ReportActivity.apiInterface.performRegistration(name, email, password);
                call.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse( Call<Users> call, Response<Users> response) {
                                if (response.body().getResponse().equals("ok")) {
                                    ReportActivity.prefConfig.displayToast("Registration Succsess");
                                    FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
                                    Fragment mFrag = new LoginFragment();
                                    t.replace(R.id.fragment_cotainer, mFrag);
                                    t.commit();
                                } else if (response.body().getResponse().equals("exist")) {
                                    ReportActivity.prefConfig.displayToast("Already exist");
                                } else if (response.body().getResponse().equals("error")) {
                                    ReportActivity.prefConfig.displayToast("Something went wrong");
                                }
                    }
                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {

                        t.getMessage();
                        t.getStackTrace();
                        Toast.makeText(getContext(), "Error in registering", Toast.LENGTH_SHORT).show();
                    }
                });


            } else {
                userPassword.setError("Password does not match");
                userConfirmPassword.setError("Password does not match");
                userPassword.setText("");
                userConfirmPassword.setText("");
            }
        } else {
            if (name.equals("")) {
                userName.setError("Please Enter Your Name");
            }
            if (email.equals("")) {
                userEmail.setError("please Enter Your Email");
            }
            if (password.equals("")) {
                userPassword.setError("Please Enter Your password");
            }
            if (confirmPassword.equals("")) {
                userConfirmPassword.setError("Enter Confirm Password");
            }

        }

    }


}
