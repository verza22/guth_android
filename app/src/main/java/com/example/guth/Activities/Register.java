package com.example.guth.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guth.Others.SqlHelper;
import com.example.guth.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registro");
        setContentView(R.layout.register);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerTipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btnCancel = (Button)findViewById(R.id.cancel);
        btnCancel.setOnClickListener(cancel);

        Button btnRegister = (Button)findViewById(R.id.register);
        btnRegister.setOnClickListener(register);
    }

    private View.OnClickListener cancel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Obtenemos los objetos
            TextView userTextView = (TextView)findViewById(R.id.inputUser);
            TextView passwordTextView = (TextView)findViewById(R.id.inputPassword);
            Spinner tipoSpinner = (Spinner) findViewById(R.id.spinnerTipo);

            String user = userTextView.getText().toString();
            String password = passwordTextView.getText().toString();
            boolean tipo = tipoSpinner.getSelectedItem().toString().equals("Administrador");
            String error = "";

            if(user.equals("") || password.equals("")){
                error = "Usuario y password requeridos";
            }else {
                SqlHelper dbHelper = new SqlHelper(view.getContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(db == null){
                    error = "Error en la conexion a la bd";
                }else{
                    boolean exist = checkUser(view,user,password);
                    if(exist){
                        error = "Ya existe un usuario con estas credenciales";
                    }else{
                        ContentValues cv = new ContentValues();
                        cv.put("usuario",user);
                        cv.put("password",password);
                        cv.put("tipo",tipo);
                        db.insert("usuarios",null,cv);

                        Intent intent = new Intent(view.getContext(), Home.class);
                        intent.putExtra("usuario", user);
                        intent.putExtra("tipo", tipo);
                        startActivity(intent);
                    }
                }
            }
            if(error!=""){
                Toast toast = Toast.makeText(view.getContext(), error, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    };

    private boolean checkUser(View view, String user, String password){
        SqlHelper dbHelper = new SqlHelper(view.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean response = false;
        Cursor c = db.rawQuery("SELECT id FROM usuarios WHERE usuario = ? AND password = ?", new String[] {user,password});
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                if(id>0){
                    response = true;
                }
            } while (c.moveToNext());
        }
        c.close();
        return response;
    }
}
