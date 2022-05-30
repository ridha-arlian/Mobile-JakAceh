package com.example.jakaceh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    CircleImageView profileImg;
    TextInputLayout fullName,userName,email,phoneNo,password;
    String user_name,user_username,user_email,user_phoneNo,user_password;
    DatabaseReference reference;
    Button callHome;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        reference = FirebaseDatabase.getInstance().getReference("users");

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        callHome = findViewById(R.id.callHome);

        fullName = findViewById(R.id.namalengkap_profile);
        userName = findViewById(R.id.username_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.notelp_profile);
        password = findViewById(R.id.password_profile);

        profileImg = findViewById(R.id.profile_img);

        showAllUserData();

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivity(intent);
            }
        });

        callHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
                Intent intent = new Intent(Profile.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateUserProfile() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData() != null){
            Uri profileUri = data.getData();
            profileImg.setImageURI(profileUri);

            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getBaseContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        user_username = intent.getStringExtra("userName");
        user_name = intent.getStringExtra("name");
        user_email = intent.getStringExtra("gmail");
        user_phoneNo = intent.getStringExtra("phoneNo");
        user_password = intent.getStringExtra("pass");

        fullName.getEditText().setText(user_name);
        userName.getEditText().setText(user_username);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_password);
    }
    public void update(View view){
        if (isUsernameChanged() || isNameChanged() || isEmailChanged() || isPhoneNoChanged() || isPasswordChanged() ){
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Tidak ada data yang berubah", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isEmailChanged() {
        if (!user_email.equals(email.getEditText().getText().toString())){
            reference.child(user_username).child("gmail").setValue(email.getEditText().getText().toString());
            user_email = email.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isUsernameChanged() {
        if (!user_username.equals(userName.getEditText().getText().toString())){
            reference.child(user_username).child("userName").setValue(userName.getEditText().getText().toString());
            user_username = userName.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isPhoneNoChanged() {
        if (!user_phoneNo.equals(phoneNo.getEditText().getText().toString())){
            reference.child(user_username).child("phoneNo").setValue(phoneNo.getEditText().getText().toString());
            user_phoneNo = phoneNo.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isPasswordChanged() {
        if (!user_password.equals(password.getEditText().getText().toString())){
            reference.child(user_username).child("pass").setValue(password.getEditText().getText().toString());
            user_password = password.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if (!user_name.equals(fullName.getEditText().getText().toString())){
            reference.child(user_username).child("name").setValue(fullName.getEditText().getText().toString());
            user_name = fullName.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }
}