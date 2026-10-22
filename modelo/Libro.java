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