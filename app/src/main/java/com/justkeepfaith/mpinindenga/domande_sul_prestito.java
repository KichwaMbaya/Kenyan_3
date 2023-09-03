package com.justkeepfaith.mpinindenga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class domande_sul_prestito extends AppCompatActivity {

    RadioButton crbyes, crbno, maried, single, notsay, primary, secondary, college, undergraduate, postgraduate, optional, notoptional;
    Spinner spinnerquestions;
    Button next_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domande_sul_prestito);

        next_questions = findViewById(R.id.next_questions);
        spinnerquestions = findViewById(R.id.spinnerquestions);
        crbyes = findViewById(R.id.crbyes);
        crbno = findViewById(R.id.crbno);
        maried = findViewById(R.id.maried);
        single = findViewById(R.id.single);
        notsay = findViewById(R.id.notsay);
        primary = findViewById(R.id.primary);
        secondary = findViewById(R.id.secondary);
        college = findViewById(R.id.college);
        undergraduate = findViewById(R.id.undergraduate);
        postgraduate = findViewById(R.id.postgraduate);
        optional = findViewById(R.id.optional);
        notoptional = findViewById(R.id.notoptional);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.counties, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerquestions.setAdapter(arrayAdapter);

        next_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!domande_sul_prestito.this.crbyes.isChecked() && !domande_sul_prestito.this.crbno.isChecked()) {
                    Toast.makeText(domande_sul_prestito.this, "Check all the Questions", Toast.LENGTH_SHORT).show();
                    return;
                } if (!domande_sul_prestito.this.maried.isChecked() && !domande_sul_prestito.this.single.isChecked() && !domande_sul_prestito.this.notsay.isChecked()) {
                    Toast.makeText(domande_sul_prestito.this, "Check all the Questions", Toast.LENGTH_SHORT).show();
                    return;
                } if (!domande_sul_prestito.this.primary.isChecked() && !domande_sul_prestito.this.secondary.isChecked() && !domande_sul_prestito.this.college.isChecked()
                        && !domande_sul_prestito.this.undergraduate.isChecked() && !domande_sul_prestito.this.postgraduate.isChecked()){
                    Toast.makeText(domande_sul_prestito.this, "Check all the Questions", Toast.LENGTH_SHORT).show();
                    return;
                } if (!domande_sul_prestito.this.optional.isChecked() && !domande_sul_prestito.this.notoptional.isChecked()){
                    Toast.makeText(domande_sul_prestito.this, "Check all the Questions", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    final ProgressDialog progressDialog = new ProgressDialog(domande_sul_prestito.this);
                    progressDialog.setTitle("Wait a minute");
                    progressDialog.setMessage("Saving...");
                    progressDialog.setProgressStyle(0);
                    progressDialog.setMax(100);
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(7000);//9000
                                domande_sul_prestito.this.startActivity(new Intent(domande_sul_prestito.this, limitare_il_gioco_mentale.class));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();
                }
            }
        });
    }
}