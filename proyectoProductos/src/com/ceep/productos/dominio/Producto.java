
package com.ceep.productos.dominio;

public class Producto {
    private String nombre;
    private int cantidad;
    private double precio;
    private String fecha;

    public Producto() {
    }

    public Producto(String nombre) {
        this();
        this.nombre = nombre;
    }

    public Producto(String nombre, int cantidad, double precio, String fecha) {
        this();
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", fecha=" + fecha + '}';
    }
    
    
}
