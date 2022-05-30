package com.example.jakaceh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout namaLengkap, username, email, noTelp, password;
    Button callSignIn, daftar;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        namaLengkap = findViewById(R.id.namalengkap);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        noTelp = findViewById(R.id.notelp);
        password = findViewById(R.id.password);

        daftar = findViewById(R.id.daftar);
        callSignIn = findViewById(R.id.signup);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                if (!validateName() | !validatePassword() | !validateUsername() | !validateEmail() | !validateNotelp()){
                    return;
                }

                String name = namaLengkap.getEditText().getText().toString();
                String userName = username.getEditText().getText().toString();
                String gmail = email.getEditText().getText().toString();
                String phoneNo = noTelp.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();

                UserHelperClass helperClass =   new UserHelperClass(name, userName, gmail, phoneNo, pass);

                reference.child(userName).setValue(helperClass);

                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
            private Boolean validateName(){
                String val = namaLengkap.getEditText().getText().toString();
                String namaVal = "[a-zA-Z ]+";
                if (val.isEmpty()){
                    namaLengkap.setError("Kolom harus diisi");
                    return false;
                }
                else if (!val.matches(namaVal)) {
                    namaLengkap.setError("Hanya boleh menggunakan alfabet");
                    return false;
                }
                else {
                    namaLengkap.setError(null);
                    namaLengkap.setErrorEnabled(false);
                    return true;
                }
            }

            private Boolean validateUsername(){
                String val = username.getEditText().getText().toString();
                String usernameVal = "[a-zA-Z0-9._-]+";

                if (val.isEmpty()){
                    username.setError("Kolom harus diisi");
                    return false;
                }
                else if (val.length() >= 15){
                    username.setError("Batas username harus kurang dari 15 karakter");
                    return false;
                }
                else if (val.contains(" ")){
                    username.setError("Tidak boleh menggunakan spasi");
                    return false;
                }
                else if (!val.matches(usernameVal)) {
                    username.setError("Tidak boleh menggunakan simbol");
                    return false;
                }
                else {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    return true;
                }
            }

            private Boolean validateEmail(){
                String val = email.getEditText().getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (val.isEmpty()){
                    email.setError("Kolom harus diisi");
                    return false;
                }
                else if (!val.matches(emailPattern)){
                    email.setError("Alamat email tidak valid");
                    return false;
                }
                else {
                    email.setError(null);
                    return true;
                }
            }

            private Boolean validateNotelp(){
                String val = noTelp.getEditText().getText().toString();
                if (val.isEmpty()){
                    noTelp.setError("Kolom harus diisi");
                    return false;
                }
                else {
                    noTelp.setError(null);
                    return true;
                }
            }

            private Boolean validatePassword(){
                String val = password.getEditText().getText().toString();
                String passwordVal = "^" +
                        " (?=.*[0-9])" + " (?=.*[a-z])" + " (?=.*[A-Z])"+ "(?=.*[a-zA-Z])" + "(?=.*[@#$&^&+=])" + "(?=\\S+$)" + ".{4,}" + "$";
                if (val.isEmpty()){
                    password.setError("Kolom harus diisi");
                    return false;
                }
//                else if (!val.matches(passwordVal)) {
//                    password.setError("Password terlalu lemah");
//                    return false;
//                }
                else {
                    password.setError(null);
                    return true;
                }
            }
        });
    }
}