package com.example.guth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Home  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String user = b.getString("usuario");
        int tipo = b.getInt("tipo");
        if(tipo==0){
            setTitle("User's Home");
        }else{
            setTitle("Admin's Home");
        }
        setContentView(R.layout.home);
    }
}
