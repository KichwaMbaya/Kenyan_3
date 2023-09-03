package com.justkeepfaith.mpinindenga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class profilo extends AppCompatActivity {

    TextView user_details;
    Button okay_prof, delete_prof;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        user_details = findViewById(R.id.user_details);
        okay_prof = findViewById(R.id.okay_prof);
        delete_prof = findViewById(R.id.delete_prof);

        sharedPreferences = getApplicationContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);

        String string = sharedPreferences.getString("first_name", "");
        String string2 = sharedPreferences.getString("middle_name", "");
        String string3 = sharedPreferences.getString("last_name", "");
        String string4 = sharedPreferences.getString("Email", "");
        String string5 = sharedPreferences.getString("phone_number", "");
        String string6 = sharedPreferences.getString("occupation", "");
        String string7 = sharedPreferences.getString("workplace", "");
        String string8 = sharedPreferences.getString("income", "");
        String string9 = sharedPreferences.getString("IDno", "");
        String string10 = sharedPreferences.getString("keen_name", "");
        String string11 = sharedPreferences.getString("relationship", "");
        String string12 = sharedPreferences.getString("keen_phone", "");

        user_details.setText("\n1. Name : "+ string + " " + string2 + " " + string3 +
                "\n\n2. Email : " + string4 +
                "\n\n3. Phone Number : " + string5 +
                "\n\n4. Occupation : " + string6 +
                "\n\n5. WorkPlace : " + string7 +
                "\n\n6. ID No : " + string9 +
                "\n\n7. Next of Keen : " + string10 +
                "\n\n8. Relationship : " + string11 +
                "\n\n9. Phone Number : " +string12 + "\n");

        okay_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        delete_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(profilo.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will result in complete removal of your data from the system." +
                        "This action cannot be undone and you will no longer access the app. \n\n" +
                        "Please note that if you had saved some money to your account it will be refunded within 5 business days.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();

                        Toast.makeText(profilo.this, "Account Deleted", Toast.LENGTH_SHORT).show();

                        android.os.Process.killProcess(android.os.Process.myPid());

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }
}