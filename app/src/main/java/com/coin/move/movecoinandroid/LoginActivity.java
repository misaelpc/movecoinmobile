package com.coin.move.movecoinandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;

import networking.MoveCoinRequest;
import networking.RequestData;


public class LoginActivity extends Activity {

    private Button mLoginButton;
    private EditText mUserEditText;
    private EditText mPasswordEditText;
    private RequestData mLoginData;
    private HashMap<String, String> body_hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginButton = (Button)findViewById(R.id.login_button);
        mUserEditText = (EditText)findViewById(R.id.user_name);
        mPasswordEditText = (EditText)findViewById(R.id.password);


        mLoginData = new RequestData();
        mLoginData.setmActionPath("/sessions/login");
        mLoginData.setmRequestMethod(Request.Method.POST);
        body_hash = new HashMap<String, String>();


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                body_hash.put("user", mUserEditText.getText().toString());
                body_hash.put("password", mPasswordEditText.getText().toString());
                mLoginData.setmBody(new JSONObject(body_hash));

                MoveCoinRequest request = MoveCoinRequest.getInstance(getApplicationContext());
                request.startAsynchronousObjectRequest(mLoginData, new MoveCoinRequest.RequestObjectListener() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        Log.d("LoginActivity", "About to transition to rewards");
                        Intent i = new Intent(LoginActivity.this, RewardsActivity.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onFail(VolleyError volleyError) {
                        Log.d("LoginActivity", volleyError.toString());
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña invalidos",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });





    }

}
