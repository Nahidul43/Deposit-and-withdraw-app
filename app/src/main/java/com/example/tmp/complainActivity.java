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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

public class complainActivity extends AppCompatActivity {


    TextInputEditText edAccount,edCompline;
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

       // edAccount=findViewById(R.id.edAccount);
        edCompline=findViewById(R.id.edCompline);
        submit=findViewById(R.id.submit);

        Intent intent=getIntent();
        String num=intent.getStringExtra("p");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // String number=edAccount.getText().toString();
                String complain = edCompline.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(complainActivity.this);


                String url = " https://sea-level-railroad.000webhostapp.com/apps/complain.php?n=" + num + "&&c=" + complain;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        response -> {
                            Toast.makeText(complainActivity.this, "Complain Submited", Toast.LENGTH_SHORT).show();
                            //  startActivity(new Intent(withDrawActivity.this, MainActivity.class));
                        },
                        error -> {

                            Log.e("VolleyError", "Error during Volley request", error);

                        });


                queue.add(stringRequest);

            }



        });




    }
}