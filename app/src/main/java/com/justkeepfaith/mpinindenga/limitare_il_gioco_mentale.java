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
import android.widget.TextView;
import android.widget.Toast;

public class limitare_il_gioco_mentale extends AppCompatActivity {

    TextView limit_limit;
    Button limitcheck, limitapply;
    EditText loan_amount;
    String limit = "0";
    int chec = 0;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limitare_il_gioco_mentale);

        limit_limit = findViewById(R.id.limit_limit);
        limitcheck = findViewById(R.id.limitcheck);
        limitapply = findViewById(R.id.limitapply);
        loan_amount = findViewById(R.id.loan_amount);

        /*limitcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = loan_amount.getText().toString();

                if (chec == 0){
                    checking();
                } else if (!amount.isEmpty()) {
                    Toast.makeText(limitare_il_gioco_mentale.this, "Click APPLY NOW", Toast.LENGTH_LONG).show();
                } else {
                    loan_amount.setError("Enter amount");
                    Toast.makeText(limitare_il_gioco_mentale.this, "Enter amount", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        checking();

        /*limit_limit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = loan_amount.getText().toString();

                if (chec == 0){
                    checking();
                } else if (!amount.isEmpty()) {
                    Toast.makeText(limitare_il_gioco_mentale.this, "Click APPLY NOW", Toast.LENGTH_LONG).show();
                } else {
                    loan_amount.setError("Enter amount");
                    Toast.makeText(limitare_il_gioco_mentale.this, "Enter amount", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        limitapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String limit = limit_limit.getText().toString();
                String amount = loan_amount.getText().toString();

                if (limit.isEmpty()){
                    limit_limit.setError("Check your limit");
                    Toast.makeText(limitare_il_gioco_mentale.this, "Click CHECK LIMIT", Toast.LENGTH_LONG).show();
                    return;
                }
                if (amount.isEmpty()) {
                    loan_amount.setError("Enter an amount");
                    Toast.makeText(limitare_il_gioco_mentale.this, "Please enter the amount you want to apply", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!amount.isEmpty()){

                    int ienteramount = Integer.parseInt(amount.toString());

                    if (ienteramount > 6500) {
                        loan_amount.setError("Your limit is KSH. 6500");
                        Toast.makeText(limitare_il_gioco_mentale.this, "Your limit is KSH 6500", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (ienteramount < 500) {
                        loan_amount.setError("Least amount is KSH. 500");
                        Toast.makeText(limitare_il_gioco_mentale.this, "You cannot apply less than KSH 500", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                final String amountapplies = String.valueOf(limitare_il_gioco_mentale.this.loan_amount.getText());
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("amountapplied", amountapplies);
                edit.commit();

                final ProgressDialog progressDialog = new ProgressDialog(limitare_il_gioco_mentale.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setProgressStyle(0);
                progressDialog.setMax(100);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(5000);//7000
                            limitare_il_gioco_mentale.this.startActivity(new Intent(limitare_il_gioco_mentale.this, periodo_di_mandato.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();
            }
        });
    }
    private void checking(){
        final ProgressDialog progressDialog = new ProgressDialog(limitare_il_gioco_mentale.this);
        progressDialog.setTitle("Checking servers");
        progressDialog.setMessage("Calculating your limit...");
        progressDialog.setProgressStyle(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(7000);//7000
                    limitare_il_gioco_mentale.this.limit = "KSH: 6500";
                    chec = 1;
                    limit_limit.setText(limitare_il_gioco_mentale.this.limit);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}