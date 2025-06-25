package creacional;

public class CafeBuilder {
    private String tipo;
    private boolean tieneLeche;
    private boolean tieneAzucar;
    private String extra;

    public CafeBuilder(String tipo) {
        this.tipo = tipo;
    }

    public CafeBuilder conLeche() {
        this.tieneLeche = true;
        return this;
    }

    public CafeBuilder conAzucar() {
        this.tieneAzucar = true;
        return this;
    }

    public CafeBuilder conExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public Cafe build() {
        return new Cafe(tipo, tieneLeche, tieneAzucar, extra);
    }
}