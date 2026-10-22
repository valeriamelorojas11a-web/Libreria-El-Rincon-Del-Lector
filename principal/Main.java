package principal;

import modelo.Libro;
import servicio.InventarioLibreria;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventarioLibreria inventario = new InventarioLibreria();
        boolean salir = false;

        System.out.println("=========================================================");
        System.out.println("   SISTEMA DE GESTIÓN DE LIBRERÍA - EL RINCÓN DEL LECTOR ");
        System.out.println("=========================================================");

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar nuevo libro");
            System.out.println("2. Consultar todo el catálogo");
            System.out.println("3. Búsqueda y filtrado multicriterio (Categoría, Año, Idioma)");
            System.out.println("4. Actualizar stock de un libro");
            System.out.println("5. Generar reporte consolidado");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción (1-6): ");

            String opcionStr = scanner.nextLine().trim();

            switch (opcionStr) {
                case "1":
                    registrarLibroInteractivo(scanner, inventario);
                    break;
                case "2":
                    mostrarListaLibros(inventario.getCatalogo());
                    break;
                case "3":
                    buscarLibrosInteractivo(scanner, inventario);
                    break;
                case "4":
                    actualizarStockInteractivo(scanner, inventario);
                    break;
                case "5":
                    System.out.println(inventario.generarReporteConsolidado());
                    break;
                case "6":
                    salir = true;
                    System.out.println("\n¡Gracias por utilizar el sistema de librería! Hasta pronto.");
                    break;
                default:
                    System.out.println("❌ Opción no reconocida. Ingrese un número entre 1 y 6.");
            }
        }
        scanner.close();
    }

    private static void registrarLibroInteractivo(Scanner scanner, InventarioLibreria inventario) {
        System.out.println("\n--- REGISTRO DE NUEVO LIBRO ---");
        String isbn = leerTexto(scanner, "ISBN: ");
        String titulo = leerTexto(scanner, "Título: ");
        String autor = leerTexto(scanner, "Autor: ");
        String categoria = leerTexto(scanner, "Categoría (ej. Terror): ");
        int anio = leerEntero(scanner, "Año de publicación (ej. 2000): ");
        String idioma = leerTexto(scanner, "Idioma (ej. Espanol): ");
        double precio = leerDouble(scanner, "Precio ($): ");
        int stock = leerEntero(scanner, "Stock inicial: ");

        Libro nuevoLibro = new Libro(isbn, titulo, autor, categoria, anio, idioma, precio, stock);
        if (inventario.agregarLibro(nuevoLibro)) {
            System.out.println("✅ ¡Libro registrado con éxito!");
        } else {
            System.out.println("❌ Error: Ya existe un libro con el ISBN " + isbn);
        }
    }

    private static void buscarLibrosInteractivo(Scanner scanner, InventarioLibreria inventario) {
        System.out.println("\n--- BÚSQUEDA Y FILTRADO MULTICRITERIO ---");
        System.out.println("(Presione Enter sin escribir nada para omitir un criterio)");

        String categoria = leerTextoOpcional(scanner, "Categoría (ej. Terror): ");

        Integer anio = null;
        System.out.print("Año de publicación (ej. 2000, o Enter para omitir): ");
        String anioStr = scanner.nextLine().trim();
        if (!anioStr.isEmpty()) {
            try {
                anio = Integer.parseInt(anioStr);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Año inválido, se omitirá este filtro.");
            }
        }

        String idioma = leerTextoOpcional(scanner, "Idioma (ej. Espanol): ");

        List<Libro> resultados = inventario.buscarMulticriterio(categoria, anio, idioma);
        mostrarListaLibros(resultados);
    }

    private static void actualizarStockInteractivo(Scanner scanner, InventarioLibreria inventario) {
        System.out.println("\n--- ACTUALIZAR STOCK DE UN LIBRO ---");
        String isbn = leerTexto(scanner, "Ingrese el ISBN del libro a actualizar: ");

        Libro libroEncontrado = null;
        for (Libro l : inventario.getCatalogo()) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) {
                libroEncontrado = l;
                break;
            }
        }

        if (libroEncontrado != null) {
            System.out.println("Libro encontrado: " + libroEncontrado.getTitulo() + " (Stock actual: " + libroEncontrado.getStock() + ")");
            int nuevoStock = leerEntero(scanner, "Ingrese el nuevo stock: ");
            libroEncontrado.setStock(nuevoStock);
            System.out.println("✅ Stock actualizado correctamente.");
        } else {
            System.out.println("❌ No se encontró ningún libro con ese ISBN.");
        }
    }

    private static void mostrarListaLibros(List<Libro> lista) {
        if (lista.isEmpty()) {
            System.out.println("\n❌ No se encontraron libros con los criterios indicados.");
        } else {
            System.out.println("\n--- RESULTADOS (" + lista.size() + " libros encontrados) ---");
            for (Libro l : lista) {
                System.out.println(l);
            }
        }
    }

    private static String leerTexto(Scanner scanner, String mensaje) {
        String entrada;
        while (true) {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("⚠️ Este campo no puede estar vacío.");
        }
    }

    private static String leerTextoOpcional(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un número entero válido.");
            }
        }
    }

    private static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Debe ingresar un valor numérico válido.");
            }
        }
    }
}