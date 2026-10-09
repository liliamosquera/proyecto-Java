package com.techlab.model;

public class Bebida extends Producto {
    private double volumenLitros;

    public Bebida(String nombre, double precio, int stock, double volumenLitros) {
        super(nombre, precio, stock, "Bebida");
        this.volumenLitros = volumenLitros;
    }

    public double getVolumenLitros() { 
        return volumenLitros; 
    }

}