package com.justkeepfaith.mpinindenga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class periodo_di_mandato extends AppCompatActivity {

    static RadioButton months3, months4, months5, months6, months8, months10, months12;
    RadioGroup radioGroup1;
    TextView repayment, tit;
    Button applydonkey;
    String undeni;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodo_di_mandato);

        months3 = (RadioButton) findViewById(R.id.months3);
        months4 = (RadioButton) findViewById(R.id.months4);
        months5 = (RadioButton) findViewById(R.id.months5);
        months6 = (RadioButton) findViewById(R.id.months6);
        months8 = (RadioButton) findViewById(R.id.months8);
        months10 = (RadioButton) findViewById(R.id.months10);
        months12 = (RadioButton) findViewById(R.id.months12);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        repayment = (TextView) findViewById(R.id.repayment);
        applydonkey = findViewById(R.id.applydonkey);
        tit = (TextView) findViewById(R.id.tit);


        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String string = sharedPreferences.getString("amountapplied", "");

        tit.setText("KSH. " + string);

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("TillText")
                .document("TillStrings");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot dsnaps = task.getResult();
                String amount = dsnaps.getString("Amount");
                String tillname = dsnaps.getString("Till_name");
                String tillnumb = dsnaps.getString("Till_no");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("depos", amount);
                editor.putString("till_name", tillname);
                editor.putString("till_no", tillnumb);
                editor.commit();
            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (!string.isEmpty()){

                    int ienteramount = Integer.parseInt(string);
                    //tit.setText(string);

                    if (periodo_di_mandato.this.months3.isChecked()) {

                        double rate = 0.12;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (periodo_di_mandato.this.months4.isChecked()) {

                        double rate = 0.16;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (periodo_di_mandato.this.months5.isChecked()) {

                        double rate = 0.2;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (periodo_di_mandato.this.months6.isChecked()) {

                        double rate = 0.24;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (periodo_di_mandato.this.months8.isChecked()) {

                        double rate = 0.32;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (periodo_di_mandato.this.months10.isChecked()) {

                        double rate = 0.37;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }
                    if (periodo_di_mandato.this.months12.isChecked()) {

                        double rate = 0.45;

                        undeni = String.valueOf(ienteramount * rate + ienteramount);
                    }

                    double vale = Double.parseDouble((undeni));
                    int deni = (int) (5 * Math.round(vale/5));
                    repayment.setText("Amount due repay : Ksh. " + deni);
                }
            }
        });
        applydonkey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (periodo_di_mandato.this.months3.isChecked() || periodo_di_mandato.this.months4.isChecked() || periodo_di_mandato.this.months5.isChecked()
                        || periodo_di_mandato.this.months6.isChecked() || periodo_di_mandato.this.months8.isChecked()
                        || periodo_di_mandato.this.months10.isChecked() || periodo_di_mandato.this.months12.isChecked()) {

                    final ProgressDialog progressDialog = new ProgressDialog(periodo_di_mandato.this);
                    progressDialog.setTitle("Please wait, it takes less than a minute");
                    progressDialog.setMessage("Submitting your loan request.");
                    progressDialog.setProgressStyle(0);
                    progressDialog.setMax(100);
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(11000);
                                periodo_di_mandato.this.startActivity(new Intent(periodo_di_mandato.this, pagina_di_cattura.class));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();
                    return;
                }
                Toast.makeText(periodo_di_mandato.this, "Select period", Toast.LENGTH_SHORT).show();
            }
        });
    }
}