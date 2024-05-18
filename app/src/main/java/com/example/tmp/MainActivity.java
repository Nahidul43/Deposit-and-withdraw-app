package com.example.tmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

   TextView singnUp,login;
    EditText edMobile,edPassword;

    String n,name,vat,bonas,display,deposite,incriment;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singnUp=findViewById(R.id.singnUp);
        login=findViewById(R.id.login);
        edMobile=findViewById(R.id.edMobile);
        edPassword=findViewById(R.id.edPassword);


        singnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, signUp.class));

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = edMobile.getText().toString();
                String pass = edPassword.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://sea-level-railroad.000webhostapp.com/apps/signin.php?n="+num;

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        boolean validUser = false;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String password = jsonObject.getString("password");
                              deposite = jsonObject.getString("deposit");
                                vat = jsonObject.getString("text");
                                n = jsonObject.getString("id");

                                incriment= jsonObject.getString("incriment");
                                bonas= jsonObject.getString("bonas");

                                int in=Integer.parseInt(incriment);
                                int bo=Integer.parseInt(bonas);
                                int de=Integer.parseInt(deposite);
                                int result=(de+bo)-in;
                                String finalResult=String.valueOf(result);



                                if (num.equals(name) && pass.equals(password)) {
                                    validUser = true;
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    intent.putExtra("Id", n);
                                    intent.putExtra("Name", num);
                                    intent.putExtra("vat", vat);
                                  intent.putExtra("deposite", deposite);
                                  intent.putExtra("result",finalResult);


                                    startActivity(intent);
                                    break;
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        if (!validUser) {

                            Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                    }
                });
                queue.add(jsonArrayRequest);

            }
        });
    }
}