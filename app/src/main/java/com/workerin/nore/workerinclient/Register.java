package com.workerin.nore.workerinclient;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText email, pass;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in


                } else {
                    // User is signed out

                }
                // ...
            }
        };

        //mLayout = (CoordinatorLayout)findViewById(R.id.informasi);

        email=(EditText)findViewById(R.id.signUpEmail);
        pass = (EditText)findViewById(R.id.signUpPass);
        signUp = (Button)findViewById(R.id.daftarEmail);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("")&&pass.getText().toString().equals("")){
                   /* Snackbar.make(mLayout, "Email dan Password harus diisi",
                            Snackbar.LENGTH_SHORT)
                            .show();*/
                }else{
                    if(pass.length() < 8){
                        /*Snackbar.make(mLayout, "Pass harus lebih dari 8",
                                Snackbar.LENGTH_SHORT)
                                .show();*/
                    }else{
                        signUp(email.getText().toString(), pass.getText().toString());
                    }
                }
            }
        });

    }

    private void signUp(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("info", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            /*Toast.makeText(signupEmail.this, "sign Up gagal",
                                    Toast.LENGTH_SHORT).show();*/
                            /*Snackbar.make(mLayout, "Sign Up gagal",
                                    Snackbar.LENGTH_SHORT)
                                    .show();*/
                        }else {
                            startActivity(new Intent(Register.this, Home.class));
                        }
                        // ...
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}



