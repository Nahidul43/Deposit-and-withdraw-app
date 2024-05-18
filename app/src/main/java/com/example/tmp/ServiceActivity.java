package com.example.tmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    TextView dePosit, withDraw, withdrawHistory, notice, vat, customerService, settings, myRefferral, tvBlance, tvName;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        // Initialize your TextViews
        dePosit = findViewById(R.id.dePosit);
        withDraw = findViewById(R.id.withDraw);
        withdrawHistory = findViewById(R.id.withdrawHistory);
        notice = findViewById(R.id.notice);
        vat = findViewById(R.id.vat);
        customerService = findViewById(R.id.customerService);
        settings = findViewById(R.id.settings);
        myRefferral = findViewById(R.id.myRefferral);
        tvBlance = findViewById(R.id.tvBlance);
        tvName = findViewById(R.id.tvName);
        back = findViewById(R.id.back);






        // Get data from Intent
        Intent intent1 = getIntent();
        String num = intent1.getStringExtra("p");
        String name = intent1.getStringExtra("n");
    String x=intent1.getStringExtra("x");
        String a=intent1.getStringExtra("v");
        String de=intent1.getStringExtra("d");
        String f=intent1.getStringExtra("final");

      //  tvBlance.setText(a);

  // int result=Integer.parseInt(x)-Integer.parseInt(a);

        tvName.setText("Hello, " + name);

   /* if(result>0){
        String c=String.valueOf(result);
        tvBlance.setText("BDT: "+c);
    }else{
        tvBlance.setText("BDT: 0");
    }*/
        tvBlance.setText("BDT: "+f);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceActivity.this, HomeActivity.class));
            }
        });

        dePosit.setOnClickListener(view -> {
            Intent intent = new Intent(ServiceActivity.this, DepositActivity.class);
            intent.putExtra("p", num);
            intent.putExtra("vat", a);
            startActivity(intent);
        });

        withDraw.setOnClickListener(view -> {
            Intent intent = new Intent(ServiceActivity.this, withDrawActivity.class);
            intent.putExtra("p", num);
            intent.putExtra("de", de);
            intent.putExtra("d", f);
            startActivity(intent);
        });

        withdrawHistory.setOnClickListener(view -> {
            Intent intent = new Intent(ServiceActivity.this, WithDrawHistoryActivity.class);
            intent.putExtra("p", num);

            startActivity(intent);
        });

        notice.setOnClickListener(view -> {
            Intent intent = new Intent(ServiceActivity.this, NoticeActivity.class);
            intent.putExtra("ID", "notice.json");
            startActivity(intent);
        });

        vat.setOnClickListener(view -> {
          // startActivity(new Intent(ServiceActivity.this, VatActivity.class));
            Intent intent = new Intent(ServiceActivity.this, VatActivity.class);
            intent.putExtra("p", num);
            intent.putExtra("de", de);
            startActivity(intent);
        });

        customerService.setOnClickListener(view -> {
            Intent intent = new Intent(ServiceActivity.this, complainActivity.class);
            intent.putExtra("p", num);
            startActivity(intent);
        });

        settings.setOnClickListener(view -> {
            startActivity(new Intent(ServiceActivity.this, SettingActivity.class));
        });

        myRefferral.setOnClickListener(view -> {
            Intent intent = new Intent(ServiceActivity.this, RefferralActivity.class);
            intent.putExtra("p", num);
            startActivity(intent);
        });
    }
}
