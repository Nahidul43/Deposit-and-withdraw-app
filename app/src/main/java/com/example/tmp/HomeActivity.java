package com.example.tmp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvBalance, tvProfit, tvTodayProfit;
    String balance,c="0";
    int result, v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView = findViewById(R.id.imageView);
        tvBalance = findViewById(R.id.tvBlance);
        tvProfit = findViewById(R.id.tvProfit);
        tvTodayProfit = findViewById(R.id.tvTodayProfit);

        Intent intent = getIntent();
        String n = intent.getStringExtra("Id");
        String name = intent.getStringExtra("Name");
        String vat = intent.getStringExtra("vat");
        String deposit = intent.getStringExtra("deposite");
        String f = intent.getStringExtra("result");

       tvBalance.setText("BDT: "+f);

            v = Integer.parseInt(vat);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ServiceActivity.class);
                intent.putExtra("p", n);
                intent.putExtra("n", name);
                intent.putExtra("x", balance);
                intent.putExtra("v", vat);
                intent.putExtra("d",deposit );
                intent.putExtra("final",f );
                startActivity(intent);
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://sea-level-railroad.000webhostapp.com/apps/Homepage.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    // Assuming there is only one object in the array
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    balance = jsonObject.getString("balance");
                    String yesterday = jsonObject.getString("yesterday");
                    String today = jsonObject.getString("today");
                    tvProfit.setText("BDT: " + yesterday);
                    tvTodayProfit.setText("BDT: " + today);
                    int b = Integer.parseInt(balance);

                  /*  result = b - v;
                    if (result > 0) {
                        tvBalance.setText(String.valueOf("BDT: "+result));
                    } else {
                        tvBalance.setText("0");
                    }*/
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
    }
}
