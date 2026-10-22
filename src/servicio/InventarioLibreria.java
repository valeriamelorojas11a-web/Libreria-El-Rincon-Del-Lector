package servicio;

import modelo.Libro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InventarioLibreria {
    private List<Libro> catalogo = new ArrayList<>();

    public InventarioLibreria() {
        // CATEGORÍA 1: TERROR
        agregarLibro(new Libro("TER01", "El Creador de Suenos", "M. Reyes", "Terror", 2000, "Espanol", 45000, 10));
        agregarLibro(new Libro("TER02", "Sombras en la Noche", "A. Gomez", "Terror", 2001, "Espanol", 50000, 5));
        agregarLibro(new Libro("TER03", "Noche Macabra", "J. Silva", "Terror", 2002, "Espanol", 38000, 4));
        agregarLibro(new Libro("TER04", "El Retorno Oscuro", "W. Peter", "Terror", 2004, "Ingles", 55000, 7));
        agregarLibro(new Libro("TER05", "La Sombra del Mal", "Stephen King", "Terror", 2005, "Ingles", 72000, 6));

        // CATEGORÍA 2: REALISMO
        agregarLibro(new Libro("REA01", "Caminos de Tierra", "G. Garcia", "Realismo", 2000, "Espanol", 60000, 12));
        agregarLibro(new Libro("REA02", "Vidas Cruzadas", "A. Perez", "Realismo", 2001, "Espanol", 35000, 8));
        agregarLibro(new Libro("REA03", "Cronicas de un Pueblo", "M. Santos", "Realismo", 2002, "Espanol", 42000, 9));
        agregarLibro(new Libro("REA04", "Tierra Adentro", "Jose Eustasio", "Realismo", 2004, "Ingles", 30000, 5));
        agregarLibro(new Libro("REA05", "Voces del Viento", "Jorge Isaacs", "Realismo", 2005, "Ingles", 28000, 4));

        // CATEGORÍA 3: TECNOLOGÍA
        agregarLibro(new Libro("TEC01", "Introduccion a POO", "C. Ruiz", "Tecnologia", 2000, "Espanol", 90000, 6));
        agregarLibro(new Libro("TEC02", "Algoritmos Avanzados", "A. Perez", "Tecnologia", 2001, "Espanol", 85000, 7));
        agregarLibro(new Libro("TEC03", "Bases de Datos Relacionales", "M. Lopez", "Tecnologia", 2002, "Espanol", 98000, 5));
        agregarLibro(new Libro("TEC04", "Design Patterns", "Erich Gamma", "Tecnologia", 2004, "Ingles", 135000, 5));
        agregarLibro(new Libro("TEC05", "Clean Code", "Robert Martin", "Tecnologia", 2005, "Ingles", 110000, 10));

        // CATEGORÍA 4: FICCIÓN
        agregarLibro(new Libro("FIC01", "Galaxia Roja", "Frank Herbert", "Ficcion", 2000, "Espanol", 75000, 9));
        agregarLibro(new Libro("FIC02", "Mundos Paralelos", "Isaac Asimov", "Ficcion", 2001, "Espanol", 68000, 7));
        agregarLibro(new Libro("FIC03", "El Ultimo Portal", "J.R.R. Tolkien", "Ficcion", 2002, "Espanol", 95000, 15));
        agregarLibro(new Libro("FIC04", "Cronicas Marcianas", "George Orwell", "Ficcion", 2004, "Ingles", 50000, 11));
        agregarLibro(new Libro("FIC05", "Dimension 451", "Ray Bradbury", "Ficcion", 2005, "Ingles", 48000, 8));

        // CATEGORÍA 5: HISTORIA
        agregarLibro(new Libro("HIS01", "El Tiempo Moderno", "S. Hawking", "Historia", 2000, "Espanol", 62000, 6));
        agregarLibro(new Libro("HIS02", "Guerras del Siglo", "W. Churchill", "Historia", 2001, "Espanol", 115000, 4));
        agregarLibro(new Libro("HIS03", "Siglo XXI", "Yuval Noah", "Historia", 2002, "Espanol", 78000, 10));
        agregarLibro(new Libro("HIS04", "Historia Contemporanea", "J. Ocampo", "Historia", 2004, "Ingles", 55000, 8));
        agregarLibro(new Libro("HIS05", "Imperios Modernos", "Mary Beard", "Historia", 2005, "Ingles", 70000, 5));
    }

    public boolean agregarLibro(Libro libro) {
        if (buscarPorIsbn(libro.getIsbn()).isPresent()) {
            return false;
        }
        catalogo.add(libro);
        return true;
    }

    public Optional<Libro> buscarPorIsbn(String isbn) {
        for (Libro l : catalogo) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) {
                return Optional.of(l);
            }
        }
        return Optional.empty();
    }

    public List<Libro> getCatalogo() {
        return Collections.unmodifiableList(catalogo);
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
        Map<String, Integer> conteoPorCategoria = new LinkedHashMap<>();

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
