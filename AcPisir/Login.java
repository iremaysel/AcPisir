package com.iremipek.AcPisir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iremipek.AcPisir.R;

public class Login extends AppCompatActivity {

        private Button buttonLogin;
        private ProgressBar progressBar;
        private EditText loginUserName;
        private EditText loginPassword;
        private TextView redirectRegister;
        private TextView forgetPassword;
        private Button buttonGoogleLogin;
        private Button buttonFacebookLogin;
        private FirebaseAuth mAuth;
        private FirebaseUser firebaseUser;
        private String userName;
        private String userPassword;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.login);

            buttonLogin = (Button) findViewById(R.id.buttonLogin);
            loginUserName = (EditText) findViewById(R.id.loginUserName);
            loginPassword = (EditText) findViewById(R.id.loginPassword);
            redirectRegister = (TextView) findViewById(R.id.redirectRegister);
            forgetPassword = (TextView) findViewById(R.id.forgetPassword);
            buttonGoogleLogin = (Button) findViewById(R.id.buttonGoogleLogin);
            buttonFacebookLogin = (Button) findViewById(R.id.buttonFacebookLogin);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);

            mAuth = FirebaseAuth.getInstance();
            firebaseUser = mAuth.getCurrentUser(); // authenticated user

            if(firebaseUser != null){ // check user session

                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
                finish();
            }

            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userName = loginUserName.getText().toString();
                    userPassword = loginPassword.getText().toString();
                    if(userName.isEmpty() || userPassword.isEmpty()){

                        Toast.makeText(getApplicationContext(),"Lütfen gerekli alanları doldurunuz!",Toast.LENGTH_SHORT).show();

                    }else{

                        loginFunc();
                    }
                }
            });

            redirectRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this,RegisterActivity.class);
                    startActivity(intent);
                }
            });

        }

        private void loginFunc() {
            mAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(Login.this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i = new Intent(Login.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                // hata
                                Toast.makeText(getApplicationContext(),"Yanlış Giriş Yaptınız!",Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }

}
