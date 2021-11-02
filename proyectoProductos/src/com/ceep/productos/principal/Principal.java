package com.ceep.productos.principal;

import com.ceep.productos.dominio.Producto;
import com.ceep.productos.negocio.*;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        var nombreFichero = "Productos.txt";
        //inicializamos las variables
        var nombre = "";
        var cantidad = 0;
        var precio = 0.0;
        var fecha = "";

        ICatalogoProductos catalogo = new CatalogoProductosImp();
        Scanner sn = new Scanner(System.in);
        Scanner dato = new Scanner(System.in);
        int opcion = 0;
        while (true) {
            System.out.println("   -----MENU-----");
            System.out.println("1.- Iniciar catalogo");
            System.out.println("2.- Agregar producto");
            System.out.println("3.- Listar productos");
            System.out.println("4.- Buscar productos");
            System.out.println("5.- Borrar producto");
            //PENDIENTES
            System.out.println("6.- Calcular total precio");
            System.out.println("7.- Mayor número de productos");
            System.out.println("8.- Contador de productos");
            //////////////////
            System.out.println("0.- Salir");
            System.out.println("Introduce la opción a elegir: \t");
            opcion = sn.nextInt();
            switch (opcion) {
                case 1:
                    catalogo.iniciarCatalogo(nombreFichero);
                    System.out.println("Catálogo iniciciado....");
                    break;
                case 2:
                    System.out.println("Introduce los datos del producto:");
                    System.out.print("Nombre:\t");
                    nombre = dato.nextLine();
                    System.out.print("Cantidad:\t");
                    cantidad = dato.nextInt();
                    System.out.print("Precio:\t");
                    precio = dato.nextDouble();
                    System.out.print("Fecha:\t");
                    fecha = dato.next();
                    //CREAMOS EL OBJETO
                    Producto p = new Producto(nombre, cantidad, precio, fecha);
                    catalogo.agregarProducto(nombre, cantidad, precio, fecha, nombreFichero);
                    break;
                case 3:
                    catalogo.listarProducto(nombreFichero);
                    break;
                case 0:
                    System.out.println("Gracias!!");
                    return;
                case 4:
                    System.out.println("Introduce el producto a buscar: ");
                    var buscar = dato.next();
                    catalogo.buscarProducto(nombreFichero, buscar);
                    break;
                case 6:
                    System.out.println("El precion máximo es: ");
                    catalogo.PrecioTotal(nombreFichero);
                    break;

                default:
                    System.out.println("Opción desconocida");
                    break;
            }
        }
    }
}
