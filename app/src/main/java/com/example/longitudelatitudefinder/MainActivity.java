package com.example.longitudelatitudefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
EditText etplace;
Button btSubmit;
TextView tvaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etplace = findViewById(R.id.et_place);
        btSubmit = findViewById(R.id.btn_submit);
        tvaddress = findViewById(R.id.txadress);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etplace.getText().toString();
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAdress(address,getApplicationContext(),new GeoHandler());

            }
        });

    }


    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String address;
            switch (msg.what){
                case 1 :
                    Bundle bundle = msg.getData();
                    address = bundle.getString("address");
                    break;
                default:
                    address = null;
            }
            tvaddress.setText(address);
        }
    }
}