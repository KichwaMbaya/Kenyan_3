package com.justkeepfaith.mpinindenga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class casa extends AppCompatActivity {

    Button homeapply;
    ImageView homeprofile;
    ProgressDialog progressDialog;
    TextView homestatus, gettips, hometips, getanwers, homefaqs, homehistory, homebalance;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa);

        homeapply = findViewById(R.id.homeapply);
        homeprofile = findViewById(R.id.homeprofile);
        homestatus = findViewById(R.id.homestatus);
        gettips = findViewById(R.id.gettips);
        hometips = findViewById(R.id.hometips);
        getanwers = findViewById(R.id.getanwers);
        homefaqs = findViewById(R.id.homefaqs);
        homehistory = findViewById(R.id.homehistory);
        homebalance = findViewById(R.id.homebalance);

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String prog = sharedPreferences.getString("loanprog", "");

        if (!prog.isEmpty()){
            //homestatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_history));
            homestatus.setText("Pending Approval.");
        }

        homeapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(casa.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setProgressStyle(0);
                progressDialog.setMax(100);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(4000);
                            Intent intent = new Intent(casa.this, applicazione.class);//appli
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();
            }
        });

        homeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(casa.this, profilo.class);
                startActivity(intent);
            }
        });

        homehistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(casa.this, history.class);
                //startActivity(intent);
            }
        });
        gettips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(casa.this);
                builder.setMessage(R.string.limit).setCancelable(false).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
            }
        });
        hometips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(casa.this);
                builder.setMessage(R.string.limit).setCancelable(false).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
            }
        });
        getanwers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(casa.this);
                builder.setMessage(R.string.faqs).setCancelable(false).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
            }
        });
        homefaqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(casa.this);
                builder.setMessage(R.string.faqs).setCancelable(false).setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
            }
        });
    }
}