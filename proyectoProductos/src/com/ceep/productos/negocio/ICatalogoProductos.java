package com.cepp.productos.negocio;

//Capa lógica, me devuelve los objetos
public interface ICatalogoProductos {

    void iniciarCatalogo(String nombreFichero);

    void agregarProducto(String nombreProducto, String nombreFichero);

    void listarProducto(String nombreFichero);

    void buscarProducto(String nombreFichero, String busqueda);

}
