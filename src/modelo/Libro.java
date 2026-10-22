package modelo;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private String idioma;
    private double precio;
    private int stock;

    public Libro(String isbn, String titulo, String autor, String categoria, int anio, String idioma, double precio, int stock) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
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
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("| ISBN: %-6s | Título: %-26s | Categ: %-10s | Año: %d | Idioma: %-8s | Stock: %d | Precio: $%.2f |",
                isbn, titulo, categoria, anio, idioma, stock, precio);
    }
}
