package com.example.guth.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guth.R;
import com.example.guth.Others.SqlHelper;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.login);

        Button btnSubmit = (Button)findViewById(R.id.submit);
        btnSubmit.setOnClickListener(submit);

        Button btnRegister = (Button)findViewById(R.id.register);
        btnRegister.setOnClickListener(register);
    }

    private View.OnClickListener submit = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            //Obtenemos los objetos
            TextView userTextView = (TextView)findViewById(R.id.inputUser);
            TextView passwordTextView = (TextView)findViewById(R.id.inputPassword);

            String user = userTextView.getText().toString();
            String password = passwordTextView.getText().toString();
            String error = "";
            if(user.equals("") || password.equals("")){
                error = "Usuario y password requeridos";
            }else {
                SqlHelper dbHelper = new SqlHelper(view.getContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor c = db.rawQuery("SELECT tipo FROM usuarios WHERE usuario = ? AND password = ?", new String[] {user,password});
                if (c.moveToFirst()) {
                    do {
                        int tipo = c.getInt(0);
                        Intent intent = new Intent(view.getContext(), Home.class);
                        intent.putExtra("usuario", user);
                        intent.putExtra("tipo", tipo);
                        startActivity(intent);
                    } while (c.moveToNext());
                } else {
                    error = "Credenciales incorrectas";
                }
                c.close();
            }
            if(error!=""){
                Toast toast = Toast.makeText(view.getContext(), error, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    };

    private View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), Register.class);
            startActivity(intent);
        }
    };
}