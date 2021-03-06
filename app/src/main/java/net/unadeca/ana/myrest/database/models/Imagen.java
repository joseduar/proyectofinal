package net.unadeca.ana.myrest.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.unadeca.ana.myrest.database.RestauranteDB;

/**
 * Created by ANA on 13/04/2018.
 */

@Table(database = RestauranteDB.class)
public class Imagen extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String uri;

    @Column
    public String nombre;
    //@Column
   // public String precio;


    @Column
    public String autor;
}