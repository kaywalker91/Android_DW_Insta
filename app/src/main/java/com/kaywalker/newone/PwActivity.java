package com.kaywalker.newone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PwActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_send).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.btn_send:
                    send();
                    break;

            }
        }
    };

    private void send(){

        String email = ((EditText)findViewById(R.id.et_email)).getText().toString();

        if(email.length() > 0){

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(PwActivity.this, "이메일을 보냈습니다.", Toast.LENGTH_LONG).show();

                            }
                        }

                    });

        }else {

            Toast.makeText(PwActivity.this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();

        }

    }
}