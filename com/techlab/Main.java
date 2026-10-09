package com.techlab;

import java.util.Scanner;

import com.techlab.excepciones.ProductoNoEncontradoException;
import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.model.Producto ;
import com.techlab.service.ProductoService;
import com.techlab.ui.MenuProducto;
import com.techlab.util.Validador;

public class Main {
    public static void main(String[] args) {
        
        ProductoService service = new ProductoService();
        Scanner sc = new Scanner(System.in);
        MenuProducto menu = new MenuProducto(sc, service);
        cargarDatosDePrueba(service);

        int opcion;
        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Elija una opción: ");
            try {
              switch (opcion) {
                    case 1 -> menu.agregarProducto();
                    case 2 -> menu.listarProductos();
                    case 3 -> menu.buscarProductoPorId();
                    case 4 -> menu.mostrarProductosPorCategoria();
                    case 5 -> menu.actualizarProducto();
                    case 6 -> menu.eliminarProducto();
                    case 7 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida. Elija un número del 1 al 7.");
                }
            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 7);
        sc.close();
    }

    private static void cargarDatosDePrueba(ProductoService service) {
        service.guardar(new Producto(
                "Yerba mate 1kg",
                3200,
                50,
                "Bebidas"
            )
        );
        service.guardar(new Producto(
                "Galletitas dulces",
                1850,
                100,
                "Almacen"
            )
        );
        service.guardar(new Producto(
                "Aceite de oliva 500ml",
                6700,
                20,
                "Almacen"
            )
        );
        service.guardar(
            new Producto(
                "Chocolate amargo 70%",
                2900,
                15,
                "Golosinas"
            )
        );
        System.out.println(
            "Se cargaron 5 productos de prueba.\n"
        );
    }
}  