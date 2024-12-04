package proyectotambo;

public class Producto {

    private String Codigo;
    private String Nombre;
    private int CantidadStock;
    private double Precio;
    private String Categoria;
    private String FechaVencimiento;
    private String Estado;

    public Producto(String codigo, String nombre, int cantidadStock, double precio, String categoria, String fechaVencimiento) {
        this.Codigo = codigo;
        this.Nombre = nombre;
        this.CantidadStock = cantidadStock;
        this.Precio = precio;
        this.Categoria = categoria;
        this.FechaVencimiento = fechaVencimiento;
        this.Estado = "REG";
    }

    // Getters y Setters
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidadStock() {
        return CantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        CantidadStock = cantidadStock;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        FechaVencimiento = fechaVencimiento;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
