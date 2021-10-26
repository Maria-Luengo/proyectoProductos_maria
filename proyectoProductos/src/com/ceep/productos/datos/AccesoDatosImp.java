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

    @Override
    public List<Producto> listar(String nombreFichero) throws LecturaDatosEx {
        var archivo = new File(nombreFichero);
        List<Producto> productosAL = new ArrayList<>();

        try {
            var leer = new BufferedReader(new FileReader(archivo));
            var lectura = leer.readLine();
            while (lectura != null) {
                productosAL.add(new Producto(lectura));
                lectura = leer.readLine();
            }
            leer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Error al listar películas FNF");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Error al listar películas (IO)");
        }
        return productosAL;
    }

    @Override
    public void escribir(Producto producto, String nombreFichero) throws AccesoDatosEx {
        var archivo = new File(nombreFichero);
        try {
            var escribir = new PrintWriter(new FileWriter(nombreFichero));
            escribir.println(producto.getNombre());
            escribir.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new EscrituraDatosEx("Error al escrinir el archivo");
        }
    }

    @Override
    public void borrar(String producto, String nombreFichero) throws EscrituraDatosEx {
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
                    mensaje = "El producto " + busqueda + " se encuentra en la lñinea " + cont + " del catálogo de productos";
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
