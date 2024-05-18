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
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class withDrawActivity extends AppCompatActivity {


    TextInputEditText amount,userNogod;

    TextView submit;
    String tk;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw);


        amount=findViewById(R.id.amount);
        userNogod=findViewById(R.id.userNogod);
        submit=findViewById(R.id.submit);

        Intent intent=getIntent();
        String id=intent.getStringExtra("p");
        String deposit=intent.getStringExtra("de");
        String finalResult=intent.getStringExtra("d");

          x=Integer.parseInt(finalResult);

   //  submit.setText(finalResult);


       submit.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            String date = DateFormat.getDateInstance().format(calendar.getTime());
            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            final String finalTime = date + " " + currentTime;

             tk=amount.getText().toString();
            String number=userNogod.getText().toString();



            if (Integer.parseInt(tk) >= 200&& Integer.parseInt(tk) <=5000  && !number.isEmpty() &&x>=200&&Integer.parseInt(tk)<=x){
                RequestQueue queue = Volley.newRequestQueue(withDrawActivity.this);


                String url = " https://sea-level-railroad.000webhostapp.com/apps/withdraw.php?i="+id+"&&m="+number+"&&a="+tk+"&&t="+finalTime;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        response -> {
                            Toast.makeText(withDrawActivity.this, "Withdrow Successfully !!", Toast.LENGTH_SHORT).show();
                          //  startActivity(new Intent(withDrawActivity.this, MainActivity.class));
                        },
                        error -> {

                            Log.e("VolleyError", "Error during Volley request", error);

                        });


                queue.add(stringRequest);


            }else{
                Toast.makeText(withDrawActivity.this, "Please try again and Check All Information", Toast.LENGTH_SHORT).show();
            }

            if(x>=200&&Integer.parseInt(tk)<=x){
                int x=Integer.parseInt(deposit);
                int y=Integer.parseInt(tk);
                int result=x-y;

                if(result>0){
                    RequestQueue queue = Volley.newRequestQueue(withDrawActivity.this);

                    String dep=String.valueOf(result);

                    String url = "https://sea-level-railroad.000webhostapp.com/apps/depositUpdate.php?n="+id+"&&e="+dep ;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            response -> {
                                Toast.makeText(withDrawActivity.this, "Withdrow Successfully !!", Toast.LENGTH_SHORT).show();
                                //  startActivity(new Intent(DepositActivity.this, MainActivity.class));
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