package com.example.dankookworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button signin, signup;
    FirebaseAuth firebaseAuth;
    EditText id, pwd;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

//        if(firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//        }

        id = findViewById(R.id.id);
        pwd = findViewById(R.id.pwd);

        Button signin = findViewById(R.id.signin);
        Button signup = findViewById(R.id.signup);

        progressDialog = new ProgressDialog(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent upintent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(upintent);
            }
        });

    }

    //파이어베이스 로그인 메소드임
    private void userLogin(){
        String userId = id.getText().toString().trim();
        String userPwd = pwd.getText().toString().trim();

        if(TextUtils.isEmpty(userId)){
            Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPwd)){
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("로그인 중입니다.");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userId, userPwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else{
                    Toast.makeText(getApplicationContext(), "로그인을 실패했습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
