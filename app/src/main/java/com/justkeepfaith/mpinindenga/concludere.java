package com.justkeepfaith.mpinindenga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class concludere extends AppCompatActivity {

    EditText finoccup, finpow, finincome, finidno, finkeen, finrelation, finphone;
    Button finsubmit;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concludere);

        finoccup = findViewById(R.id.finoccup);
        finpow = findViewById(R.id.finpow);
        finincome = findViewById(R.id.finincome);
        finidno = findViewById(R.id.finidno);
        finkeen = findViewById(R.id.finkeen);
        finrelation = findViewById(R.id.finrelation);
        finphone = findViewById(R.id.finphone);
        finsubmit = findViewById(R.id.finsubmit);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String occupied = sharedPreferences.getString("occupation", "");
        String occupied2 = sharedPreferences.getString("last_name", "");

        if (!occupied2.isEmpty()){
            if (!occupied.isEmpty()){
                Intent intent = new Intent(concludere.this, casa.class);
                startActivity(intent);
                finish();
            }
        }else {
            /*Intent intent = new Intent(concludere.this, in_progress.class);
            startActivity(intent);
            finish();*/
        }

        finsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyer();
            }
        });
    }
    private void verifyer(){
        String finoccu = finoccup.getText().toString();
        String finpo = finpow.getText().toString();
        String finincom = finincome.getText().toString();
        String finidn = finidno.getText().toString();
        String finkee = finkeen.getText().toString();
        String finrelatio = finrelation.getText().toString();
        String phon = finphone.getText().toString();

        if (finoccu.isEmpty()){
            finoccup.setError("Enter occupation");
            return;
        }
        if (finpo.isEmpty()){
            finpow.setError("Enter place of work");
            return;
        }
        if (finincom.isEmpty()){
            finincome.setError("Enter income");
            return;
        }
        if (finidn.isEmpty()){
            finidno.setError("Enter ID no");
            return;
        }
        if (finidn.length() <7 ){
            finidno.setError("Enter a valid ID");
            return;
        }
        if (finidn.length() >8 ){
            finidno.setError("Enter a valid ID");
            return;
        }
        if (finkee.isEmpty()){
            finkeen.setError("Enter keen's name");
            return;
        }
        if (finrelatio.isEmpty()){
            finrelation.setError("Enter relationship");
            return;
        }
        if (phon.isEmpty()){
            finphone.setError("Enter phone number");
            return;
        }
        if (phon.length() < 10 ){
            finphone. setError("Enter a valid phone number");
            return;
        }
        if (phon.length() > 13 ){
            finphone.setError("Enter a valid phone number");
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("occupation", finoccu);
        editor.putString("workplace", finpo);
        editor.putString("income", finincom);
        editor.putString("IDno", finidn);
        editor.putString("keen_name", finkee);
        editor.putString("relationship", finrelatio);
        editor.putString("keen_phone", phon);
        editor.commit();


        progressDialog = new ProgressDialog(concludere.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Saving details...");
        progressDialog.setProgressStyle(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(4000);
                    Intent intent = new Intent(concludere.this, casa.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}