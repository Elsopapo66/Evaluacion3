package estructural;

public class PayPal implements PagoAdapter {
    private APIPayPal api;
    
    public PayPal(APIPayPal api) {
        this.api = api;
    }

    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago PayPal...");
        return api.realizarCobro(monto);
    }

    public static class APIPayPal {
        public boolean realizarCobro(double monto) {
            System.out.println("Cobrando $" + monto + " via PayPal");
            return true;
        }
    }
}