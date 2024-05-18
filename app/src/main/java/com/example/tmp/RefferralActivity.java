package com.example.tmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RefferralActivity extends AppCompatActivity {

    TextView tvCode,tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refferral);

        tvCode=findViewById(R.id.tvCode);
        tvTotal=findViewById(R.id.tvTotal);


        Intent intent=getIntent();
        String num=intent.getStringExtra("p");

        tvCode.setText("Your Refferral Code Is: "+num);

        RequestQueue queue = Volley.newRequestQueue(RefferralActivity.this);


        String url = "https://sea-level-railroad.000webhostapp.com/apps/Reffer.php?id="+num;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                   tvTotal.setText(response.toString());
                },
                error -> {

                    Log.e("VolleyError", "Error during Volley request", error);

                });


        queue.add(stringRequest);



    }
}