package com.example.dankookworld;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private ArrayList<ClipData.Item> list;
    EditText name, phone, birth, address, insertId, insertPwd;
    Button register;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog progressDialog;
    String Name,Phone,Birth,Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
//        if(firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//        }

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        birth = findViewById(R.id.birth);
        address = findViewById(R.id.address);
        insertId = findViewById(R.id.insertId);
        insertPwd = findViewById(R.id.insertPwd);

        Button register = findViewById(R.id.register);




        progressDialog = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });


    }

    private void registerUser(){
        String makeId = insertId.getText().toString().trim();
        String makePwd = insertPwd.getText().toString().trim();

        progressDialog.setMessage("회원가입 중입니다.");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(makeId, makePwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
//                    String u1 = firebaseAuth.getUid();
//                    String email = insertId.getText().toString();
                    String email = insertId.getText().toString();
                    Map<String, Object> dataToSave = new HashMap<>();
                    dataToSave.put("Address", address.getText().toString());
                    dataToSave.put("Birth",birth.getText().toString());
                    dataToSave.put("Name", name.getText().toString());
                    dataToSave.put("Phone", phone.getText().toString());
                    firebaseFirestore.collection("user").document(email).set(dataToSave);
                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "가입하실 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });




    }

}

