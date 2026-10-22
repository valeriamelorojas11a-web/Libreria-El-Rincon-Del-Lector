import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private String idioma;
    private double precio;
    private int stock;

    public Libro(String isbn, String titulo, String autor, String categoria, int anio, String idioma, double precio, int stock) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
        this.idioma = idioma;
        this.precio = precio;
        this.stock = stock;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public int getAnio() { return anio; }
    public String getIdioma() { return idioma; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }

    @Override
    public String toString() {
        return String.format("| ISBN: %-6s | Título: %-26s | Categ: %-10s | Año: %d | Idioma: %-8s | Stock: %d | Precio: $%.2f |",
                isbn, titulo, categoria, anio, idioma, stock, precio);
    }
}


class InventarioLibreria {
    private List<Libro> catalogo = new ArrayList<>();

    public InventarioLibreria() {
       
        agregarLibro(new Libro("TER01", "El Creador de Suenos", "M. Reyes", "Terror", 2000, "Espanol", 45000, 10));
        agregarLibro(new Libro("TER02", "Sombras en la Noche", "A. Gomez", "Terror", 2001, "Espanol", 50000, 5));
        agregarLibro(new Libro("TER03", "Noche Macabra", "J. Silva", "Terror", 2002, "Espanol", 38000, 4));
        agregarLibro(new Libro("TER04", "El Retorno Oscuro", "W. Peter", "Terror", 2004, "Ingles", 55000, 7));
        agregarLibro(new Libro("TER05", "La Sombra del Mal", "Stephen King", "Terror", 2005, "Ingles", 72000, 6));

        // CATEGORÍA 2: REALISMO (3 Español: 2000-2002, 2 Inglés: 2004-2005)
        agregarLibro(new Libro("REA01", "Caminos de Tierra", "G. Garcia", "Realismo", 2000, "Espanol", 60000, 12));
        agregarLibro(new Libro("REA02", "Vidas Cruzadas", "A. Perez", "Realismo", 2001, "Espanol", 35000, 8));
        agregarLibro(new Libro("REA03", "Cronicas de un Pueblo", "M. Santos", "Realismo", 2002, "Espanol", 42000, 9));
        agregarLibro(new Libro("REA04", "Tierra Adentro", "Jose Eustasio", "Realismo", 2004, "Ingles", 30000, 5));
        agregarLibro(new Libro("REA05", "Voces del Viento", "Jorge Isaacs", "Realismo", 2005, "Ingles", 28000, 4));

       
        agregarLibro(new Libro("TEC01", "Introduccion a POO", "C. Ruiz", "Tecnologia", 2000, "Espanol", 90000, 6));
        agregarLibro(new Libro("TEC02", "Algoritmos Avanzados", "A. Perez", "Tecnologia", 2001, "Espanol", 85000, 7));
        agregarLibro(new Libro("TEC03", "Bases de Datos Relacionales", "M. Lopez", "Tecnologia", 2002, "Espanol", 98000, 5));
        agregarLibro(new Libro("TEC04", "Design Patterns", "Erich Gamma", "Tecnologia", 2004, "Ingles", 135000, 5));
        agregarLibro(new Libro("TEC05", "Clean Code", "Robert Martin", "Tecnologia", 2005, "Ingles", 110000, 10));

        
        agregarLibro(new Libro("FIC01", "Galaxia Roja", "Frank Herbert", "Ficcion", 2000, "Espanol", 75000, 9));
        agregarLibro(new Libro("FIC02", "Mundos Paralelos", "Isaac Asimov", "Ficcion", 2001, "Espanol", 68000, 7));
        agregarLibro(new Libro("FIC03", "El Ultimo Portal", "J.R.R. Tolkien", "Ficcion", 2002, "Espanol", 95000, 15));
        agregarLibro(new Libro("FIC04", "Cronicas Marcianas", "George Orwell", "Ficcion", 2004, "Ingles", 50000, 11));
        agregarLibro(new Libro("FIC05", "Dimension 451", "Ray Bradbury", "Ficcion", 2005, "Ingles", 48000, 8));

        
        agregarLibro(new Libro("HIS01", "El Tiempo Moderno", "S. Hawking", "Historia", 2000, "Espanol", 62000, 6));
        agregarLibro(new Libro("HIS02", "Guerras del Siglo", "W. Churchill", "Historia", 2001, "Espanol", 115000, 4));
        agregarLibro(new Libro("HIS03", "Siglo XXI", "Yuval Noah", "Historia", 2002, "Espanol", 78000, 10));
        agregarLibro(new Libro("HIS04", "Historia Contemporanea", "J. Ocampo", "Historia", 2004, "Ingles", 55000, 8));
        agregarLibro(new Libro("HIS05", "Imperios Modernos", "Mary Beard", "Historia", 2005, "Ingles", 70000, 5));
    }

    public boolean agregarLibro(Libro libro) {
        for (Libro l : catalogo) {
            if (l.getIsbn().equalsIgnoreCase(libro.getIsbn())) {
                return false;
            }
        }
        catalogo.add(libro);
        return true;
    }

    public List<Libro> getCatalogo() {
        return catalogo;
    }

    public List<Libro> buscarMulticriterio(String categoria, Integer anio, String idioma) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro l : catalogo) {
            boolean coincide = true;

            if (categoria != null && !categoria.trim().isEmpty() && !l.getCategoria().equalsIgnoreCase(categoria)) {
                coincide = false;
            }
            if (anio != null && l.getAnio() != anio) {
                coincide = false;
            }
            if (idioma != null && !idioma.trim().isEmpty() && !l.getIdioma().equalsIgnoreCase(idioma)) {
                coincide = false;
            }

            if (coincide) {
                resultados.add(l);
            }
        }
        return resultados;
    }

    public String generarReporteConsolidado() {
        int totalLibros = catalogo.size();
        int totalUnidades = 0;
        double valorTotalInventario = 0;
        Map<String, Integer> conteoPorCategoria = new HashMap<>();

        for (Libro l : catalogo) {
            totalUnidades += l.getStock();
            valorTotalInventario += l.getStock() * l.getPrecio();
            conteoPorCategoria.put(l.getCategoria(), conteoPorCategoria.getOrDefault(l.getCategoria(), 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n=================== REPORTE CONSOLIDADO DE INVENTARIO ===================\n");
        sb.append(" Cantidad de títulos distintos: ").append(totalLibros).append("\n");
        sb.append(" Total de unidades físicas en stock: ").append(totalUnidades).append("\n");
        sb.append(" Valor total estimado del inventario: $").append(String.format("%.2f", valorTotalInventario)).append("\n");
        sb.append(" Distribución por categoría:\n");
        for (Map.Entry<String, Integer> entry : conteoPorCategoria.entrySet()) {
            sb.append("   - ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" títulos\n");
        }
        sb.append("=========================================================================\n");
        return sb.toString();
    }
}


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