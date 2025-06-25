package creacional;

public class Cafe {
    private String tipo;
    private boolean tieneLeche;
    private boolean tieneAzucar;
    private String extra;
    private double precio;

    public Cafe(String tipo, boolean tieneLeche, boolean tieneAzucar, String extra) {
        this.tipo = tipo;
        this.tieneLeche = tieneLeche;
        this.tieneAzucar = tieneAzucar;
        this.extra = extra;
        this.precio = calcularPrecio();
    }

    private double calcularPrecio() {
        double precioBase = 1500;
        if (tieneLeche) precioBase += 300;
        if (tieneAzucar) precioBase += 100;
        if (extra != null) precioBase += 500;
        return precioBase;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "tipo='" + tipo + '\'' +
                ", leche=" + tieneLeche +
                ", azucar=" + tieneAzucar +
                ", extra='" + extra + '\'' +
                ", precio=" + precio +
                '}';
    }
}