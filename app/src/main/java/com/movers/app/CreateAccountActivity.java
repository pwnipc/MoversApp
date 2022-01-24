package com.movers.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = CreateAccountActivity.class.getSimpleName();

    @BindView(R.id.createAccountName)
    EditText createAccountName;
    @BindView(R.id.createAccountEmailEditText)
    EditText createAccountEmailEditText;
    @BindView(R.id.createAccountPasswordEditText) EditText createAccountPasswordEditText;
    @BindView(R.id.confirmPasswordEditText) EditText confirmPasswordEditText;
    @BindView(R.id.createBtn) Button createBtn;
    @BindView(R.id.loginTextView)
    TextView mLoginTextView;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mLoginTextView.setOnClickListener(this);
        createBtn.setOnClickListener(this);

        createAuthStateListener();
    }
    @Override
    public void onClick(View view) {

        if (view == mLoginTextView) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == createBtn) {
            createNewUser();
        }

    }

    private void createNewUser() {
        final String name = createAccountName.getText().toString().trim();
        final String email = createAccountEmailEditText.getText().toString().trim();
        String password = createAccountPasswordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Authentication successful");
                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}