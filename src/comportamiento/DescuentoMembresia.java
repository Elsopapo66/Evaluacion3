package comportamiento;

public class DescuentoMembresia implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double precio) {
        System.out.println("Aplicando descuento membresía (15%)");
        return precio * 0.85;
    }
}