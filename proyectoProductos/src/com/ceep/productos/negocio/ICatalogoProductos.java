package com.ceep.productos.negocio;

//Capa lógica, me devuelve los objetos

public interface ICatalogoProductos {

    void iniciarCatalogo(String nombreFichero);

    void agregarProducto(String nombreProducto,int canitdad, double precio, String fecha, String nombreFichero);

    void listarProducto(String nombreFichero);

    void buscarProducto(String nombreFichero, String busqueda);

}
