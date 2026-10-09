package com.techlab.ui;

import java.util.Scanner;

import com.techlab.model.*;
import com.techlab.service.ProductoService;
import com.techlab.util.Validador;

public class MenuProducto {
    private final Scanner scanner;
    private final ProductoService service;

    public MenuProducto(Scanner scanner, ProductoService service) {
        this.scanner = scanner;
        this.service = service;
    }

    public void mostrarMenu() {
        System.out.println("======= TechLab - Gestión de Productos =======");
        System.out.println("1) Agregar producto");
        System.out.println("2) Listar productos");
        System.out.println("3) Buscar producto por ID");
        System.out.println("4) Mostrar productos por categoria");
        System.out.println("5) Actualizar producto");
        System.out.println("6) Eliminar producto");
        System.out.println("7) Salir");
        System.out.println("==============================================");
    }

    public void agregarProducto() {
        System.out.println("--- Nuevo producto ---");
        String nombre = Validador.leerTexto(scanner, "Nombre: ");
        double precio = Validador.leerDouble(scanner, "Precio: ");
        int stock = Validador.leerEntero(scanner, "Stock: ");
        String categoria = Validador.leerTexto(scanner, "Categoría: ");

        Producto p = new Producto(nombre,precio,stock,categoria);
        Producto guardado = service.guardar(p);
        
        
        System.out.println("Producto agregado correctamente.");
        System.out.println("ID asignado: " + guardado.getId());
    }

   public void listarProductos() {
        System.out.println("\n--- Lista de productos ---");

        if (service.listarTodos().isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto p : service.listarTodos()) {
            System.out.println(p);
        }
    }

    public void buscarProductoPorId() {
        System.out.println("\n--- Buscar producto ---");

        int id = Validador.leerEntero(scanner, "Ingrese el ID del producto: ");

        Producto producto = service.obtenerPorId(id);

        System.out.println("Producto encontrado:");
        System.out.println(producto);
    }

    public void mostrarProductosPorCategoria() {
        System.out.println("\n--- Productos por categoría ---");

        String categoria = Validador.leerTexto(
            scanner,
            "Ingrese la categoría: "
        );

        boolean encontrado = false;

        for (Producto p : service.listarTodos()) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(p);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println(
                "No se encontraron productos en la categoría: " + categoria
            );
        }
    }

    public void actualizarProducto() {
        System.out.println("\n--- Actualizar producto ---");

        int id = Validador.leerEntero(
            scanner,
            "Ingrese el ID del producto a actualizar: "
        );

        String nombre = Validador.leerTexto(scanner, "Nuevo nombre: ");
        double precio = Validador.leerDouble(scanner, "Nuevo precio: ");
        int stock = Validador.leerEntero(scanner, "Nuevo stock: ");
        String categoria = Validador.leerTexto(
            scanner,
            "Nueva categoría: "
        );

        Producto datos = new Producto(
            nombre,
            precio,
            stock,
            categoria
        );

        Producto actualizado = service.actualizar(id, datos);

        System.out.println("Producto actualizado correctamente.");
        System.out.println(actualizado);
    }

    public void eliminarProducto() {
        System.out.println("\n--- Eliminar producto ---");
        int id = Validador.leerEntero(
            scanner,
            "Ingrese el ID del producto a eliminar: "
        );
        service.eliminar(id); 
        System.out.println("Producto eliminado correctamente."); 
    }
}
