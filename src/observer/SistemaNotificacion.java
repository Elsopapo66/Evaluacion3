package observer;

import java.util.ArrayList;
import java.util.List;

public class SistemaNotificacion {
    private List<Observador> observadores = new ArrayList<>();

    public void registrarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(mensaje);
        }
    }
}