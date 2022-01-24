package com.movers.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.createAccountName)
    EditText mCreateUserButton;
    @BindView(R.id.createAccountEmailEditText)
    EditText createAccountEmailEditText;
    @BindView(R.id.createAccountPasswordEditText) EditText createAccountPasswordEditText;
    @BindView(R.id.confirmPasswordEditText) EditText confirmPasswordEditText;
    @BindView(R.id.createBtn) Button createBtn;
    @BindView(R.id.loginTextView)
    TextView mLoginTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mLoginTextView.setOnClickListener(this);
        createBtn.setOnClickListener(this);
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


}