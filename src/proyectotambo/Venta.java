/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectotambo;

/**
 *
 * @author PRV
 */
public class Venta {
    
   private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private String vendedor;
    private String categoria;
    // Constructor para inicializar una venta
    public Venta(String codigo, String nombre, double precio, int cantidad, String vendedor, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.vendedor = vendedor;
        this.categoria = categoria;
    }
     
    // Métodos getter y setter
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    // Método para calcular el subtotal
    public double calcularSubtotal() {
        return precio * cantidad;
    }
    
      public void setCategoria(String Categoria) {
        this.vendedor = Categoria;
    }
      
          public String getCategoria() {
        return categoria;
    }
          
              public double calcularIGV() {
        double subtotal = calcularSubtotal(); // Llamamos al método calcularSubtotal
        return subtotal * 0.18;  // 18% de IGV
    }

}
