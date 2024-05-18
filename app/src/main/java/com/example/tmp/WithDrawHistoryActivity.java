package com.example.tmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;

public class WithDrawHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    int i,count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_history);

        recyclerView = findViewById(R.id.recyclerView);


        // Move the adapter and layout manager initialization here
        recyclerView.setLayoutManager(new LinearLayoutManager(WithDrawHistoryActivity.this));
        recyclerView.setAdapter(new XAdapter());

        Intent intent = getIntent();
        String id = intent.getStringExtra("p");

        // Change the URL based on the user's ID
        String url = "https://sea-level-railroad.000webhostapp.com/apps/withdrawHistory.php?m="+id;

        // Create a RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a JsonArrayRequest
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for ( i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String number = jsonObject.getString("number");
                                String amount = jsonObject.getString("amount");
                                String time = jsonObject.getString("time");
                                hashMap = new HashMap<>();
                                hashMap.put("number", number);
                                hashMap.put("amount", amount);
                                hashMap.put("time", time);
                                arrayList.add(hashMap);


                            }


                            // Notify the adapter that the data has changed
                            recyclerView.getAdapter().notifyDataSetChanged();

                        } catch (JSONException e) {
                            // Log the error for debugging
                            Log.e("WithDrawHistoryActivity", "Error parsing JSON", e);
                            Toast.makeText(WithDrawHistoryActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log the error for debugging
                        Log.e("WithDrawHistoryActivity", "Volley Error", error);
                        // Handle errors here
                        Toast.makeText(WithDrawHistoryActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        requestQueue.add(jsonArrayRequest);






    }

    public class XAdapter extends RecyclerView.Adapter<XAdapter.Holder> {

        public class Holder extends RecyclerView.ViewHolder {
            TextView TvName;

            public Holder(@NonNull View itemView) {
                super(itemView);
                TvName = itemView.findViewById(R.id.TvName);
            }
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            hashMap = arrayList.get(position);
            String number = hashMap.get("number");
            String amount = hashMap.get("amount");
            String time = hashMap.get("time");
            holder.TvName.setText("Time: " + time + "\nNumber: " + number + "\nAmount: " + amount);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}
