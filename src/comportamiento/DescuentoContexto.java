package comportamiento;

public class DescuentoContexto {
    private EstrategiaDescuento estrategia;

    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }

    public double aplicarDescuento(double precio) {
        if (estrategia != null) {
            return estrategia.aplicarDescuento(precio);
        }
        return precio;
    }
}