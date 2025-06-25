package comportamiento;

public class DescuentoEstudiante implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double precio) {
        System.out.println("Aplicando descuento estudiante (20%)");
        return precio * 0.8;
    }
}