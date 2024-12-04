package proyectotambo;

public class RegistroProducto {

    private Producto[] productos; // Matriz para almacenar productos
    private int contador; // Número actual de productos registrados

    public RegistroProducto() {
        productos = new Producto[1000]; // Matriz con capacidad para 1000 productos
        contador = 0; // Inicializamos el contador en 0
    }
    public Producto[] getProductos() {
        return productos;
    }

    public void registrarProducto(Producto producto) {
        // Verificar si el producto ya existe basado en el código
        for (int i = 0; i < contador; i++) {
            if (productos[i].getCodigo().equalsIgnoreCase(producto.getCodigo())
                    && !productos[i].getEstado().equals("ANU")) {
                productos[i].setCantidadStock(productos[i].getCantidadStock() + producto.getCantidadStock());
                System.out.println("Producto actualizado: " + productos[i].getNombre());
                return;
            }
        }

        // Agregar nuevo producto si hay espacio en la matriz
        if (contador < productos.length) {
            productos[contador] = producto;
            contador++;
            System.out.println("Producto registrado con éxito.");
        } else {
            System.out.println("Error: No hay espacio disponible para registrar más productos.");
        }
    }

    public String eliminarProducto(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getCodigo().equalsIgnoreCase(codigo)
                    && !productos[i].getEstado().equals("ANU")) {
                productos[i].setEstado("ANU");
                return "Producto eliminado exitosamente.";
            }
        }
        return "Producto no encontrado.";
    }

    public String editarProducto(String codigo, String nombre, double precio, int cantidadStock, String categoria, String fechaVencimiento) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getCodigo().equalsIgnoreCase(codigo)
                    && !productos[i].getEstado().equals("ANU")) {
                productos[i].setNombre(nombre);
                productos[i].setPrecio(precio);
                productos[i].setCantidadStock(cantidadStock);
                productos[i].setCategoria(categoria);
                productos[i].setFechaVencimiento(fechaVencimiento);
                return "Producto editado exitosamente.";
            }
        }
        return "Producto no encontrado.";
    }

    public Producto buscarProducto(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (productos[i].getCodigo().equalsIgnoreCase(codigo)
                    && !productos[i].getEstado().equals("ANU")) {
                return productos[i];
            }
        }
        return null;
    }

    public void listarProductos() {
        // Cabecera con los tamaños ajustados
        System.out.printf("%-6s | %-40s | %-5s | %-6s | %-12s | %-12s\n",
                "Código", "Nombre", "Stock", "Precio", "Categoría", "Vencimiento");
        System.out.println("-----------------------------------------------------------------------------------------------");

        boolean hayProductos = false;

        // Recorre todos los productos y los imprime
        for (int i = 0; i < contador; i++) {
            if (!productos[i].getEstado().equals("ANU")) {
                hayProductos = true;

                // Limitar el tamaño del nombre a 15 caracteres
                String nombreProducto = productos[i].getNombre();
                if (nombreProducto.length() > 35) {
                    nombreProducto = nombreProducto.substring(0, 35) + "..."; // Truncar el nombre si es muy largo
                }

                // Imprimir los detalles con alineación adecuada
                System.out.printf("%-6s | %-40s | %-5d | %-6.2f | %-12s | %-12s\n",
                        productos[i].getCodigo(),
                        nombreProducto, // Usar el nombre truncado
                        productos[i].getCantidadStock(),
                        productos[i].getPrecio(),
                        productos[i].getCategoria(),
                        productos[i].getFechaVencimiento()
                );
            }
        }

        // Si no hay productos, se muestra un mensaje
        if (!hayProductos) {
            System.out.println("No hay productos registrados.");
        }
    }
}
