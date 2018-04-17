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
 * Created by ANA on 13/04/2018.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText roll;
    private Session session;
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerview);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        roll = findViewById(R.id.roll);
        image = findViewById(R.id.imageLogin);

        session = new Session(this);
        Button registrar = findViewById(R.id.register);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //mandamos a llamar validar y si no encuentra ningun error pasa al siguiente codigo
                    validar(username.getText().toString(), password.getText().toString(), roll.getText().toString());
                    // despues de validar pasa este codigo registrar
                    goToRegistrar(username.getText().toString(), password.getText().toString(), roll.getText().toString());

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        Functions.loadImage("https://thumb9.shutterstock.com/display_pic_with_logo/3630134/604345325/stock-vector-review-rating-bubble-speeches-on-mobile-phone-vector-illustration-flat-style-smartphone-reviews-604345325.jpg", image, this);
    }
    private  void goToMain(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
    //validar si el usuario esta vacio
    private void validar(String username, String password, String roll)throws Exception{
        if(username.isEmpty())
            throw new Exception("el nombre del usuario esta vacio");
        if(password.isEmpty())
            throw new Exception("la contraseña esta vacia esta vacio");
        if(roll.isEmpty())
            throw new Exception("el roll del usuario  esta vacia esta vacio");

    }

//creamos un usuario con gotoRegistrar
    private boolean goToRegistrar(String username, String password, String roll)throws Exception{
        boolean isLoggedIn=  SQLite.selectCountOf().from(Users.class).where(Users_Table.username.eq(username)).and(Users_Table.password.eq(Functions.md5(password))).hasData();
        if (isLoggedIn){
            throw new Exception("el usuario ya existe");

        }else{
            Users user = new Users();
            user.nombre = username;
            user.username = username;
            user.password= Functions.md5(password);
            user.roll=roll;
            user.save();
            Session session = new Session(getApplicationContext());
            session.createLoginSession(user.id,user.nombre,user.roll);
            goToMain();


        }
        return isLoggedIn;
    }

}
