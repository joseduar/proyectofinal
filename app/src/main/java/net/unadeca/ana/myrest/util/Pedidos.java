package net.unadeca.ana.myrest.util;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;

import net.unadeca.ana.myrest.R;
import net.unadeca.ana.myrest.database.models.PedidosTable;
import net.unadeca.ana.myrest.mostrarpedidos;

public class Pedidos extends AppCompatActivity {
    private EditText txtNombreComprador;
    private EditText txtPedidoDetallado;
    private EditText txtDireccion;
    private EditText txtNumTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        FlowManager.init(this);
        txtNombreComprador = findViewById(R.id.NombreComprador);
        txtPedidoDetallado = findViewById(R.id.PedidoDetallado);
        txtDireccion = findViewById(R.id.Direccion);
        txtNumTel = findViewById(R.id.NumTel);


    }



    private boolean validación (){
        boolean send =true;
        if (txtNombreComprador.getText().toString().isEmpty()){
            return send;
        }
        if (txtPedidoDetallado.getText().toString().isEmpty()){
            send = false;
        }
        if (txtDireccion.getText().toString().isEmpty()){
            send = false;
        }

        if (txtNumTel.getText().toString().isEmpty()){
            send = false;
        }

        return  send;
    }

    private void pedidos(){
        if (validación()){
            PedidosTable pedir = new PedidosTable();
            pedir.nombrecomprador = txtNombreComprador.getText().toString();
            pedir.pedidodetallado = txtPedidoDetallado.getText().toString();
            pedir.direccion = txtDireccion.getText().toString();
            pedir.numtel = txtNumTel.getText().toString();

            pedir.save();
            Toast.makeText(this,"Pedido guardado",Toast.LENGTH_LONG).show();

        }

        else{
            Toast.makeText(this,"Se produjo un error al hacer el pedido.",Toast.LENGTH_LONG).show();

        }

    }

    public void EnvPedido(View view) {
        pedidos();

    }

    public void MostrarPedidos(View view) {
        Intent i = new Intent(Pedidos.this, mostrarpedidos.class);
        startActivity(i);
    }

}

