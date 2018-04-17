package net.unadeca.ana.myrest.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.unadeca.ana.myrest.database.RestauranteDB;

/**
 * Created by ANA on 15/04/2018.
 */
@Table (database = RestauranteDB.class)
public class PedidosTable extends BaseModel {

        @PrimaryKey(autoincrement = true)
        public long id;

        @Column
        public String nombrecomprador;

        @Column
        public String pedidodetallado;

        @Column
        public String direccion;

        @Column
        public String numtel;

        @Column
        public int estado;


    }




