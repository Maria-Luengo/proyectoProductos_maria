package com.ceep.productos.datos;

import com.ceep.productos.dominio.Producto;
import com.ceep.productos.excepciones.AccesoDatosEx;
import com.ceep.productos.excepciones.EscrituraDatosEx;
import com.ceep.productos.excepciones.LecturaDatosEx;
import java.util.List;

public interface IAccesoDatos {

    boolean existe(String nombreFichero);

    //CRUD
    void crear(String nombreFichero) throws AccesoDatosEx;

    List<Producto> listar(String nombreFichero) throws LecturaDatosEx;

    void escribir(Producto producto, String nombreFichero, boolean anexar) throws AccesoDatosEx;

    void borrar(String nombreFichero) throws EscrituraDatosEx;

    String buscar(String nombreFichero, String busqueda) throws LecturaDatosEx;

}
