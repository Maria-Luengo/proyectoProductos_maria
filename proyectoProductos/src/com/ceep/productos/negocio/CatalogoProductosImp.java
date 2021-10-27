package com.ceep.productos.negocio;

import com.ceep.productos.datos.*;
import com.ceep.productos.dominio.Producto;
import com.ceep.productos.excepciones.*;
import java.io.File;
import java.util.*;

public class CatalogoProductosImp implements ICatalogoProductos {

    private final IAccesoDatos DATOS; //objeto del tipo de la interface

    //constructro vacío con ibjeto como la clase
    public CatalogoProductosImp() {
        this.DATOS = new AccesoDatosImp();
    }

    @Override
    public void iniciarCatalogo(String nombreFichero) {
        try {
            if (this.DATOS.existe(nombreFichero)) {
                this.DATOS.borrar(nombreFichero);
                this.DATOS.crear(nombreFichero);
            } else {
                this.DATOS.crear(nombreFichero);
            }
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error al inicializar el catálogo");
        }
    }

    @Override
    public void agregarProducto(String nombreProducto, int cantidad, double precio, String fecha, String nombreFichero) {

        try {
            if (this.DATOS.existe(nombreFichero)) {
                this.DATOS.escribir(new Producto(nombreProducto, cantidad, precio, fecha), nombreFichero, true);
            } else {
                System.out.println("Catálogo no inicializado");
            }
        } catch (AccesoDatosEx ex) { //ME DA ERROR SI PONGO EXCEPCION DE ESCRITURA
            System.out.println("Error lectura desde Catálogo agreagarPelicula");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarProducto(String nombreFichero) {
        List<Producto> productos = new ArrayList<>();
        try {
            productos = this.DATOS.listar(nombreFichero);
            productos.forEach(producto -> {
                System.out.println("Producto: " + producto.getNombre()
                        + "\nnombre: "+producto.getCantidad()
                        + "\nprecio: "+producto.getPrecio()
                        + "\nfecha: "+producto.getFecha());
            });
        } catch (LecturaDatosEx e) {
            System.out.println("Error de lectura al listar productos clase Catalogo productos");
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarProducto(String nombreFichero, String busqueda) {
        var archivo = new File(nombreFichero);
        try {
            System.out.println(this.DATOS.buscar(nombreFichero, busqueda));
        } catch (LecturaDatosEx ex) {
            ex.printStackTrace(System.out);
        }
    }

}
