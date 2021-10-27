package com.ceep.productos.datos;

import com.ceep.productos.dominio.Producto;
import com.ceep.productos.excepciones.*;
import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccesoDatosImp implements IAccesoDatos {

    @Override
    public boolean existe(String nombreFichero) {
        var archivo = new File(nombreFichero);
        return true;
    }

    //Operaciones CRUD
    @Override
    public void crear(String nombreFichero) throws AccesoDatosEx {
        var archivo = new File(nombreFichero);
        try {
            var escribir = new PrintWriter(new FileWriter(nombreFichero));
            escribir.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Error al crear");
        }
    }

    ///////////////////////////////
    @Override
    public List<Producto> listar(String nombreFichero) throws LecturaDatosEx {
        //Archivo
        File archivo = new File(nombreFichero);
        //Creo un arraylist con los productos
        List<Producto> productos = new ArrayList<>(); //almacena productos
        String[] productoArray = new String[4];
        try {
            //Declaro variable para entrar al archivo
            BufferedReader entrada = new BufferedReader(new FileReader(archivo)); //Para que no se sobreescriba
            String lectura = entrada.readLine(); // lectura = nombre;cantidad;precio;fecha
            //Hasta que se acaben las lineas con productos
            while (lectura != null) {
                productoArray = lectura.split(";"); // producto = {nombre, cantidad, precio, fecha}
                //parseamos los elementos
                var nombre= productoArray[0];
                var precio= Double.parseDouble(productoArray[1]);
                var cantidad= Integer.parseInt(productoArray[2]);
                var fecha= productoArray[3];
                productos.add(new Producto(nombre,
                        cantidad,
                        precio,
                        fecha)); //Se van añadiendo al array los productos
                lectura = entrada.readLine();//Pasa de linea
            }
            entrada.close();//Cierra
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Error de lectura listando los productos");
        }
        return productos;
    }

    @Override
    public void escribir(Producto producto, String nombreFichero, boolean anexar) throws AccesoDatosEx {
        var archivo = new File(nombreFichero);
        try {
            var escribir = new PrintWriter(new FileWriter(nombreFichero, true));
            escribir.println(producto.getNombre() + ";"
                    + producto.getPrecio() + ";"
                    + producto.getCantidad() + ";"
                    + producto.getFecha());

            escribir.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new EscrituraDatosEx("Error al escrinir el archivo");
        }
    }

    @Override
    public void borrar(String nombreFichero) throws EscrituraDatosEx {
        var archivo = new File(nombreFichero);
        if (archivo.exists()) {
            archivo.delete();
        }
        System.out.println("Se ha borrado el archivo");
    }

    @Override
    public String buscar(String nombreFichero, String busqueda) throws LecturaDatosEx {
        var archivo = new File(nombreFichero);
        int cont = 1;
        String mensaje = "";

        try {
            var leer = new BufferedReader(new FileReader(archivo));
            var lectura = leer.readLine();
            while (lectura != null) {
                if (!lectura.equalsIgnoreCase(busqueda)) {
                    mensaje = "El producto " + busqueda + " se encuentra en la línea " + cont + " del catálogo de productos";
                    break;
                }
                lectura = leer.readLine();
                cont++;
            }
            if (lectura == null) {
                mensaje = "El producto " + busqueda + " no está en el catálogo";
            }
            leer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Error en buscar (FNF)");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Error en buscar (IO)");
        }
        return mensaje;
    }
}
