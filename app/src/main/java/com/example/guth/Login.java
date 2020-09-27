package com.example.guth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.login);

        Button btnRegistrar = (Button)findViewById(R.id.submit);
        btnRegistrar.setOnClickListener(btnSubmit);
    }

    private View.OnClickListener btnSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Obtenemos los objetos
            TextView user = (TextView)findViewById(R.id.inputUser);
            TextView password = (TextView)findViewById(R.id.inputPassword);

            if(user.getText().toString().equals("") || password.getText().toString().equals("")){
                Toast toast = Toast.makeText(view.getContext(), "Usuario y password requeridos", Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Toast toast = Toast.makeText(view.getContext(), "OK", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    };
}