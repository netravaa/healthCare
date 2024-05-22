package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edPassword, edConfirm, edEmail;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edPassword = findViewById(R.id.editTextRegPassword);
        edEmail = findViewById(R.id.editTextEmail);
        edConfirm = findViewById(R.id.editTextPasswordConfirm);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database();

                if (email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Заполните все пункты", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        String passwordValidationMessage = getPasswordValidationMessage(password);
                        if (passwordValidationMessage.isEmpty()) {
                            db.register(email, password, new Database.RegisterCallback() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(getApplicationContext(), "Регистрация пройдена", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                }

                                @Override
                                public void onFailure(String errorMessage) {
                                    Toast.makeText(getApplicationContext(), "Ошибка: " + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), passwordValidationMessage, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    public static String getPasswordValidationMessage(String password) {
        if (password.length() < 8) {
            return "Пароль должен быть не менее 8 символов";
        }

        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (c >= 33 && c <= 46 || c == 64) {
                hasSpecialChar = true;
            }
        }

        if (!hasLetter) {
            return "Пароль должен содержать хотя бы одну букву";
        }
        if (!hasDigit) {
            return "Пароль должен содержать хотя бы одну цифру";
        }
        if (!hasSpecialChar) {
            return "Пароль должен содержать хотя бы один специальный символ";
        }

        return "";
    }
}
