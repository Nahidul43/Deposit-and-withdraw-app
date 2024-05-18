package com.example.tmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DepositActivity extends AppCompatActivity {

    TextInputEditText edNumber, txnId,edAmount;
    TextView nogod, submit;
    String balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        nogod = findViewById(R.id.nogod);
        edNumber = findViewById(R.id.edNumber);
        txnId = findViewById(R.id.txnId);
        submit = findViewById(R.id.submit);
        edAmount = findViewById(R.id.edAmount);

        Intent intent = getIntent();
        String c = intent.getStringExtra("p");


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://sea-level-railroad.000webhostapp.com/apps/Homepage.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    // Assuming there is only one object in the array
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    balance = jsonObject.getString("balance");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonArrayRequest);









        nogod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edNumber.setVisibility(View.VISIBLE);
                txnId.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                edNumber.setText(balance);
                edAmount.setVisibility(View.VISIBLE);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String deposit = edAmount.getText().toString();
                        String txnid = txnId.getText().toString();









                        if (!deposit.isEmpty() && !txnid.isEmpty()) {
                            RequestQueue queue = Volley.newRequestQueue(DepositActivity.this);

                            String url = "https://sea-level-railroad.000webhostapp.com/apps/update.php?n=" + c + "&&e=" + deposit + "&&t=" + txnid;


                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                    response -> {
                                        Toast.makeText(DepositActivity.this, "Deposit Add Successfully !!", Toast.LENGTH_SHORT).show();
                                       startActivity(new Intent(DepositActivity.this, MainActivity.class));
                                    },
                                    error -> {
                                        Log.e("VolleyError", "Error during Volley request", error);
                                    });

                            queue.add(stringRequest);
                        } else {
                            Toast.makeText(DepositActivity.this, "Input All Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
