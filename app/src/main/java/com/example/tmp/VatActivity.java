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

public class VatActivity extends AppCompatActivity {

    TextView tvVat, submit;
    TextInputEditText edAmount;
    String aM, deposite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vat);

        tvVat = findViewById(R.id.tvVat);
        submit = findViewById(R.id.submit);
        edAmount = findViewById(R.id.edAmount);

        Intent intent = getIntent();
        String id = intent.getStringExtra("p");
        String deposit = intent.getStringExtra("de");










        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 aM=edAmount.getText().toString();
                if(!aM.isEmpty()){
                    RequestQueue queue = Volley.newRequestQueue(VatActivity.this);


                    String url = "https://sea-level-railroad.000webhostapp.com/apps/vat.php?n="+id+"&&e="+aM;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            response -> {
                                Toast.makeText(VatActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(VatActivity.this, MainActivity.class));
                            },
                            error -> {

                                Log.e("VolleyError", "Error during Volley request", error);

                            });


                    queue.add(stringRequest);
                }else{
                    Toast.makeText(VatActivity.this, "Input All Data", Toast.LENGTH_SHORT).show();
                }

             int x=Integer.parseInt(deposit);
                int y=Integer.parseInt(aM);
                int result=x-y;

                if(result>0){
                    RequestQueue queue = Volley.newRequestQueue(VatActivity.this);

                    String dep=String.valueOf(result);

                    String url = "https://sea-level-railroad.000webhostapp.com/apps/depositUpdate.php?n="+id+"&&e="+dep ;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            response -> {
                                Toast.makeText(VatActivity.this, "Register successfully !!", Toast.LENGTH_SHORT).show();

                            },
                            error -> {
                                Log.e("VolleyError", "Error during Volley request", error);
                            });

                    queue.add(stringRequest);


                }














            }

        });









    }
}