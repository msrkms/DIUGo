package com.sajidur.diugo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LogIn extends AppCompatActivity {

    TextView tvDontHaveAnAccount;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        tvDontHaveAnAccount=(TextView)findViewById(R.id.textViewDontHaveAnAccount);
        btnLogin=(Button) findViewById(R.id.buttonLogin);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LogIn.this,Dashboard.class));
                LogIn.this.finish();
            }
        });


        tvDontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this,SignUp.class));
                LogIn.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {


        new MaterialAlertDialogBuilder(LogIn.this)
                .setTitle("Confirm")
                .setMessage("Do You Want To Exit")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LogIn.this.finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}
