package com.iremipek.AcPisir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


//Uygulamaya kayıt olma ekranı

public class RegisterActivity extends AppCompatActivity {

    private EditText registerUserName;
    private EditText registerPassword;
    private EditText registerRePassword;
    private Button buttonRegister;
    private TextView redirectLogin;
    private String userName;
    private String userPassword;
    private String userRePassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);                                                 //xml dosyası

        mAuth = FirebaseAuth.getInstance();

        registerUserName = (EditText) findViewById(R.id.registerUserName);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerRePassword = (EditText) findViewById(R.id.registerRePassword);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        redirectLogin = (TextView) findViewById(R.id.redirectLogin);

        redirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                    //Zaten uygulamaya kayıt olunmuşsa login ekranına yönlendirme yapar.
                Intent i = new Intent(RegisterActivity.this,Login.class);
                startActivity(i);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                   //Kayıt olma kısmı

                userName = registerUserName.getText().toString();
                userPassword = registerPassword.getText().toString();
                userRePassword = registerRePassword.getText().toString();

                if (validate(userName, userPassword, userRePassword)) {
                    registerFunc();
                }

            }
        });

    }

    private boolean validate(String email, String password, String repeatedPassword) {
        boolean valid = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {                          //geçerli bir mail adresi girilmediğinde
            valid = false;
            this.registerUserName.setError("Geçerli bir mail adresi girin!");
        } else if (password.isEmpty() || password.length() < 5 || password.length() > 32) {                 //pparaola karakter uzunluğu belirtilen aralığı kapsamıyorsa
            valid = false;
            this.registerPassword.
                    setError("Parola boş bırakılamaz! Parolanız 6-32 " +
                            "karakter uzunluğunda olmalıdır!");
        } else if (repeatedPassword.isEmpty()) {                                                            //parola tekrarı boş bırakılırsa
            valid = false;
            this.registerRePassword.setError("Parola tekrarı boş bırakılamaz.");
        } else if (!password.equals(repeatedPassword)) {                                                    //parola tekrarı ve parola uyuşmuyorsa.
            valid = false;
            this.registerRePassword.setError("Parolalar uyuşmuyor!");
        }

        return valid;
    }

    private void registerFunc() {

        mAuth.createUserWithEmailAndPassword(userName,userPassword)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){                                                    //oturum başarılı açıldığında anasayfa açılır.
                            Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
