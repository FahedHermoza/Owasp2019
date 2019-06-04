package com.fahedhermoza.developer.owaspapp;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fahedhermoza.developer.owaspapp.models.ResponseLogin;
import com.fahedhermoza.developer.owaspapp.models.User;
import com.fahedhermoza.developer.owaspapp.services.ServiceGenerator;
import com.fahedhermoza.developer.owaspapp.services.ServicesEndPoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {
    private View viewRoot;
    private EditText editTextUser;
    private TextInputLayout textInputUser;
    private EditText editTextPassword;
    private TextInputLayout textInputPassword;
    private TextView textViewLogin;


    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_login, container, false);

        editTextUser = (EditText) viewRoot.findViewById(R.id.editTextUser);
        textInputUser = (TextInputLayout) viewRoot.findViewById(R.id.textInputUser);

        editTextPassword = (EditText) viewRoot.findViewById(R.id.editTextPassword);
        textInputPassword = (TextInputLayout) viewRoot.findViewById(R.id.textInputPassword);

        textViewLogin = (TextView) viewRoot.findViewById(R.id.textViewLogin);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                if(validate()){
                    webServicePostFindUser(editTextUser.getText().toString(),
                            editTextPassword.getText().toString());
                } */
                if(validateTextInput()){
                    webServicePostFindUser(editTextUser.getText().toString(),
                            editTextPassword.getText().toString());
                }

            }
        });

        return viewRoot;
    }

    public Boolean validate() {
        if (TextUtils.isEmpty(editTextUser.getText())) {
            editTextUser.setError("Ingrese usuario");
            return false;
        }

        if (TextUtils.isEmpty(editTextPassword.getText())) {
            editTextPassword.setError("Ingrese password");
            return false;
        }

        viewRoot.clearFocus();
        return true;
    }

    public Boolean validateTextInput() {
        Boolean respuesta = true;
        String userError = null;
        if (TextUtils.isEmpty(editTextUser.getText())) {
            userError = ("Ingrese usuario");
            respuesta = false;
        }
        toggleTextInputLayoutError(textInputUser, userError);

        String passError = null;
        if (TextUtils.isEmpty(editTextPassword.getText())) {
            passError = "Ingrese password";
            respuesta = false;
        }
        toggleTextInputLayoutError(textInputPassword, passError);

        viewRoot.clearFocus();
        return respuesta;
    }

    private static void toggleTextInputLayoutError(TextInputLayout textInputLayout,
                                                   String msg) {
        textInputLayout.setError(msg);
        if (msg == null) {
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
        }
    }

    public void webServicePostFindUser(final String user,
                                       final String contrasena) {

        ServicesEndPoint service = ServiceGenerator.createService(ServicesEndPoint.class);
        Call<ResponseLogin> call = service.findUser(new User(user, contrasena));
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Toast toast = Toast.makeText(getContext(), "Mensaje: " + response.body().getAnswer(), Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e("ERROR", "ERROR t: " + t.toString());
            }
        });
    }
}
