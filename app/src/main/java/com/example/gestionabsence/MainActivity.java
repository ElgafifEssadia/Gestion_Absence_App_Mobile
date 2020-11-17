package com.example.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText login,passowrd;
    Button conx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.username);
        passowrd = findViewById(R.id.password);
        conx = findViewById(R.id.connexion);

        conx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect(login.getText().toString(),passowrd.getText().toString());

            }
        });

    }


    private void connect(String login,String pass)
    {
        if(login.equals("admin")  && pass.equals("admin"))
        {
            Intent i = new Intent(MainActivity.this,gestionAbsence.class );
            startActivity(i);
        }
        else
        {

            Toast.makeText(MainActivity.this,"Username ou password incorrect",Toast.LENGTH_LONG).show();
        }
    }
}
