package com.example.smart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonmasuk;
    private EditText editTextpassword;
    private EditText editTextemail;
    private TextView textViewregis;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonmasuk = (Button) findViewById(R.id.button_masuk);
        editTextemail = (EditText) findViewById(R.id.email);
        editTextpassword = (EditText) findViewById(R.id.password);
        textViewregis= (TextView) findViewById(R.id.button_regis);

        buttonmasuk.setOnClickListener(this);
        textViewregis.setOnClickListener(this);
    }

    private void masukUser() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Tolong isi dahulu email anda", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Tolong isi dahulu password anda", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("User Masuk...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(Login.this, "SIGN IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                            Intent masukIntent = new Intent(Login.this, HalamanUtama.class);
                            Login.this.startActivity(masukIntent);
                        }else {
                            Toast.makeText(Login.this, "COULD NOT REGISTER.. PLEASE TRY AGAIN", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if (view == buttonmasuk) {
            masukUser();
        }
        if (view == textViewregis){
            Intent masIntent = new Intent(Login.this, Register.class);
            Login.this.startActivity(masIntent);
        }
    }

}
