package estructural;

public class Stripe implements PagoAdapter {
    private APIStripe api;
    
    public Stripe(APIStripe api) {
        this.api = api;
    }

    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago Stripe...");
        return api.efectuarPago(monto);
    }

    public static class APIStripe {
        public boolean efectuarPago(double monto) {
            System.out.println("Cobrando $" + monto + " via Stripe");
            return true;
        }
    }
}