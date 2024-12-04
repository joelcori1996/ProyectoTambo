package proyectotambo;

public class RegistroVenta {

    private Venta[] matriz = new Venta[1000];

    // Método para agregar una venta
    public void agregarVenta(Venta venta) {
        for (int i = 0; i < matriz.length; i++) {
            // Si encontramos una posición libre, agregamos la venta
            if (matriz[i] == null) {
                matriz[i] = venta;
                System.out.println("Venta registrada exitosamente.");
                return;
            }

            // Si el producto ya existe, actualizamos la cantidad
            if (matriz[i].getCodigo().equalsIgnoreCase(venta.getCodigo())) {
                matriz[i].setCantidad(matriz[i].getCantidad() + venta.getCantidad());
                System.out.println("Venta actualizada exitosamente.");
                return;
            }
        }

        System.out.println("Error: No se pueden registrar más ventas. Matriz llena.");
    }

    // Método para mostrar todas las ventas
    public void mostrarBoletaElectronica(String vent) {
        boolean hayVentas = false;
        double total = 0;  // Para almacenar el total de las ventas
        double igvTotal = 0;  // Para almacenar el total del IGV

        // Imprimir encabezado
        System.out.println("===================================");
        System.out.println("           BOLETA ELECTRÓNICA      ");
        System.out.println("===================================");

        // Imprimir vendedor (antes de la tabla)
        System.out.printf("%-30s: %s\n", "Vendedor", vent);  // Puedes reemplazar "Juan Pérez" por el vendedor actual

        // Encabezado de la tabla
        System.out.printf("%-6s %-40s %-10s %-8s %-10s\n", "CÓDIGO", "NOMBRE", "PRECIO", "CANT.", "SUBTOTAL");
        System.out.println("-----------------------------------------------------------------------------");

        // Mostrar las ventas
        for (Venta venta : matriz) {
            if (venta != null && venta.getVendedor().trim().toLowerCase().equals(vent.trim().toLowerCase())) {
                // Mostrar los detalles de cada venta
                System.out.printf("%-6s %-40s %-10.2f %-8d %-10.2f\n",
                        venta.getCodigo(),
                        venta.getNombre(),
                        venta.getPrecio(),
                        venta.getCantidad(),
                        venta.calcularSubtotal());

                // Sumar al total y al IGV
                total += venta.calcularSubtotal();
                igvTotal += venta.calcularIGV();
                hayVentas = true;
            }
        }

        if (!hayVentas) {
            System.out.println("No hay ventas registradas.");
        } else {
            // Mostrar el total y el IGV
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%-30s: %.2f\n", "Total", total);
            System.out.printf("%-30s: %.2f\n", "IGV (18%)", igvTotal);
            System.out.printf("%-30s: %.2f\n", "Total con IGV", total + igvTotal);
        }

        System.out.println("===================================");
        System.out.println("     Gracias por su compra         ");
        System.out.println("===================================");
    }

    // Método para calcular el total de las ventas
    public double calcularTotal() {
        double total = 0;

        for (Venta venta : matriz) {
            if (venta != null) {
                total += venta.calcularSubtotal();
            }
        }

        return total;
    }

    public void reporteMasMenosVendidos(RegistroProducto registroProducto) {
        int[] ventasPorProducto = new int[1000]; // Arreglo para contar las ventas de cada producto
        String[] codigosProductos = new String[1000]; // Arreglo para guardar los códigos de los productos

        // Contar las ventas por producto
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i] != null) {
                boolean encontrado = false;
                for (int j = 0; j < codigosProductos.length; j++) {
                    if (codigosProductos[j] != null && codigosProductos[j].equals(matriz[i].getCodigo())) {
                        ventasPorProducto[j] += matriz[i].getCantidad(); // Sumar cantidad vendida
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    for (int j = 0; j < codigosProductos.length; j++) {
                        if (codigosProductos[j] == null) {
                            codigosProductos[j] = matriz[i].getCodigo();
                            ventasPorProducto[j] = matriz[i].getCantidad();
                            break;
                        }
                    }
                }
            }
        }

        // Encontrar el producto más vendido y menos vendido
        int maxVentas = 0;
        int minVentas = Integer.MAX_VALUE;
        String codigoMasVendido = "";
        String codigoMenosVendido = "";

        for (int i = 0; i < codigosProductos.length; i++) {
            if (codigosProductos[i] != null) {
                if (ventasPorProducto[i] > maxVentas) {
                    maxVentas = ventasPorProducto[i];
                    codigoMasVendido = codigosProductos[i];
                }
                if (ventasPorProducto[i] < minVentas) {
                    minVentas = ventasPorProducto[i];
                    codigoMenosVendido = codigosProductos[i];
                }
            }
        }

        // Mostrar los productos más y menos vendidos
        Producto masVendido = registroProducto.buscarProducto(codigoMasVendido);
        Producto menosVendido = registroProducto.buscarProducto(codigoMenosVendido);

        System.out.println("Producto más vendido:");
        if (masVendido != null) {
            System.out.println("- " + masVendido.getNombre() + " (Vendidos: " + maxVentas + ")");
        }

        System.out.println("Producto menos vendido:");
        if (menosVendido != null) {
            System.out.println("- " + menosVendido.getNombre() + " (Vendidos: " + minVentas + ")");
        }
    }

    // Método para generar reporte por categoría
    public void reportePorCategoria(RegistroProducto registroProducto) {
        String[] categorias = new String[1000]; // Arreglo para almacenar las categorías
        int[] cantidadPorCategoria = new int[1000]; // Arreglo para contar productos por categoría

        // Contar productos por categoría
        for (int i = 0; i < registroProducto.getProductos().length; i++) {
            if (registroProducto.getProductos()[i] != null) {
                boolean encontrado = false;
                for (int j = 0; j < categorias.length; j++) {
                    if (categorias[j] != null && categorias[j].equals(registroProducto.getProductos()[i].getCategoria())) {
                        cantidadPorCategoria[j]++;
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    for (int j = 0; j < categorias.length; j++) {
                        if (categorias[j] == null) {
                            categorias[j] = registroProducto.getProductos()[i].getCategoria();
                            cantidadPorCategoria[j] +=  registroProducto.getProductos()[i].getCantidadStock();
                            break;
                        }
                    }
                }
            }
        }

        // Mostrar reporte por categoría en columnas
        System.out.printf("%-20s | %-10s\n", "Categoría", "Productos");
        System.out.println("-------------------------------");

        for (int i = 0; i < categorias.length; i++) {
            if (categorias[i] != null) {
                System.out.printf("%-20s | %-10d\n", categorias[i], cantidadPorCategoria[i]);
            }
        }
    }

  

}
