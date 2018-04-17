package net.unadeca.ana.myrest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.unadeca.ana.myrest.R;
import net.unadeca.ana.myrest.database.models.Users;
import net.unadeca.ana.myrest.database.models.Users_Table;
import net.unadeca.ana.myrest.util.Functions;
import net.unadeca.ana.myrest.util.Session;

/**
 * Created by ANA on 26/03/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Session session;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new Session(this);
        if (session.isLoggedIn()){
            goToMain();
        }

        setContentView(R.layout.loginview);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        image = findViewById(R.id.imageLogin);


        Button iniciar = findViewById(R.id.login);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userna = username.getText().toString();
                String passwo = password.getText().toString();
                if (userna.isEmpty() || passwo.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.loginerror), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Login(userna, passwo);
                }
            }
        });


        Button registrar = findViewById(R.id.register);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegistrar();
            }
        });


        Functions.loadImage("https://cdn.pixabay.com/photo/2018/02/24/10/10/pineapple-3177704_960_720.png", image, this);
    }

    private  void goToMain(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    private  void goToRegistrar(){
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
        finish();
    }

    //users_table= lo utilisamos para hacer consultas es como la instancia

    private boolean Login(String username, String password){
        boolean isLoggedIn=  SQLite.selectCountOf().from(Users.class).where(Users_Table.username.eq(username)).and(Users_Table.password.eq(Functions.md5(password))).hasData();
        Users user = SQLite.select().from(Users.class).where(Users_Table.username.eq(username)).and(Users_Table.password.eq(Functions.md5(password))).querySingle();
//si las preferencias existen esta logiado
        if (isLoggedIn){
            session.createLoginSession(user.id,user.nombre, user.roll);
            goToMain();//  sta haciendo un intento asta main activity
        }else{
            Toast.makeText(this, getResources().getString(R.string.tryregister), Toast.LENGTH_LONG).show();
        }
        return isLoggedIn;
    }

}
