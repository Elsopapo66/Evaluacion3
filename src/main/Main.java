package main;

import java.util.Scanner;
import creacional.CafeBuilder;
import estructural.PagoAdapter;
import estructural.PayPal;
import estructural.Stripe;
import comportamiento.DescuentoContexto;
import comportamiento.DescuentoEstudiante;
import comportamiento.DescuentoMembresia;
import creacional.Cafe;
import observer.Cliente;
import observer.Observador;
import observer.SistemaNotificacion;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaNotificacion sistema = new SistemaNotificacion();
        Cliente cliente = new Cliente("Juan");
        sistema.registrarObservador(cliente);
        
        System.out.println("¡Bienvenido a la Cafetería Patrones de Diseño!");
        
        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ordenar café");
            System.out.println("2. Salir");
            System.out.print("Seleccione: ");
            
            String opcionStr = scanner.nextLine().trim();
            if(opcionStr.isEmpty()) {
                System.out.println("❌ Error: Debe ingresar una opción");
                continue;
            }
            
            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch(NumberFormatException e) {
                System.out.println("❌ Error: Ingrese un número válido (1 o 2)");
                continue;
            }
            
            if(opcion == 2) {
                System.out.println("\n¡Gracias por visitarnos! Vuelva pronto.");
                break;
            } else if(opcion != 1) {
                System.out.println("❌ Error: Opción inválida. Solo 1 o 2");
                continue;
            }
            
            // Builder: Construir café
            System.out.println("\n=== CONSTRUYE TU CAFÉ ===");
            CafeBuilder builder = new CafeBuilder("Expresso");
            
            // Validación para leche
            String respuesta;
            boolean entradaValida = false;
            do {
                System.out.print("¿Agregar leche? (s/n): ");
                respuesta = scanner.nextLine().trim().toLowerCase();
                if(respuesta.equals("s") || respuesta.equals("n")) {
                    entradaValida = true;
                } else {
                    System.out.println("❌ Error: Ingrese 's' o 'n'");
                }
            } while(!entradaValida);
            if(respuesta.equals("s")) builder.conLeche();
            
            // Validación para azúcar
            entradaValida = false;
            do {
                System.out.print("¿Agregar azúcar? (s/n): ");
                respuesta = scanner.nextLine().trim().toLowerCase();
                if(respuesta.equals("s") || respuesta.equals("n")) {
                    entradaValida = true;
                } else {
                    System.out.println("❌ Error: Ingrese 's' o 'n'");
                }
            } while(!entradaValida);
            if(respuesta.equals("s")) builder.conAzucar();
            
            // Validación para extra
            entradaValida = false;
            String extra = null;
            do {
                System.out.print("¿Agregar extra? (s/n): ");
                respuesta = scanner.nextLine().trim().toLowerCase();
                if(respuesta.equals("s") || respuesta.equals("n")) {
                    entradaValida = true;
                } else {
                    System.out.println("❌ Error: Ingrese 's' o 'n'");
                }
            } while(!entradaValida);
            
            if(respuesta.equals("s")) {
                boolean extraValido = false;
                do {
                    System.out.print("  ¿Qué extra? (Caramelo/Vainilla/Chocolate): ");
                    extra = scanner.nextLine().trim();
                    if(!extra.isEmpty()) {
                        extraValido = true;
                    } else {
                        System.out.println("❌ Error: Debe ingresar un extra");
                    }
                } while(!extraValido);
                builder.conExtra(extra);
            }
            
            Cafe cafe = builder.build();
            double precio = cafe.getPrecio();
            System.out.printf("\nSubtotal: $%.0f%n", precio);
            
            // Strategy: Aplicar descuento
            DescuentoContexto descuento = new DescuentoContexto();
            System.out.println("\n=== APLICAR DESCUENTO ===");
            System.out.println("1. Estudiante (20% de descuento)");
            System.out.println("2. Membresía (15% de descuento)");
            System.out.println("3. Ninguno");
            System.out.print("Seleccione: ");
            
            int descOpcion = 0;
            boolean opcionValida = false;
            while(!opcionValida) {
                String input = scanner.nextLine().trim();
                try {
                    descOpcion = Integer.parseInt(input);
                    if(descOpcion >= 1 && descOpcion <= 3) {
                        opcionValida = true;
                    } else {
                        System.out.println("❌ Error: Ingrese 1, 2 o 3");
                    }
                } catch(NumberFormatException e) {
                    System.out.println("❌ Error: Ingrese un número válido");
                }
            }
            
            switch(descOpcion) {
                case 1:
                    descuento.setEstrategia(new DescuentoEstudiante());
                    break;
                case 2:
                    descuento.setEstrategia(new DescuentoMembresia());
                    break;
                default:
                    System.out.println("Sin descuento aplicado");
            }
            
            precio = descuento.aplicarDescuento(precio);
            System.out.printf("Precio final: $%.0f%n", precio);
            
            // Adapter: Procesar pago
            System.out.println("\n=== MÉTODO DE PAGO ===");
            System.out.println("1. PayPal");
            System.out.println("2. Stripe");
            System.out.print("Seleccione: ");
            
            int pagoOpcion = 0;
            opcionValida = false;
            while(!opcionValida) {
                String input = scanner.nextLine().trim();
                try {
                    pagoOpcion = Integer.parseInt(input);
                    if(pagoOpcion == 1 || pagoOpcion == 2) {
                        opcionValida = true;
                    } else {
                        System.out.println("❌ Error: Ingrese 1 o 2");
                    }
                } catch(NumberFormatException e) {
                    System.out.println("❌ Error: Ingrese un número válido");
                }
            }
            
            PagoAdapter pago;
            if(pagoOpcion == 1) {
                pago = new PayPal(new PayPal.APIPayPal());
            } else {
                pago = new Stripe(new Stripe.APIStripe());
            }
            
            System.out.println("\nProcesando pago...");
            boolean pagoExitoso = pago.procesarPago(precio);
            
            if(pagoExitoso) {
                // Observer: Notificar preparación
                System.out.println("\nPreparando tu café... ⏳");
                try {
                    // Simular tiempo de preparación con animación
                    for(int i = 0; i < 5; i++) {
                        Thread.sleep(600);
                        System.out.print("☕ ");
                    }
                    System.out.println();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                
                sistema.notificarObservadores("¡Tu café está listo! Disfrútalo 🎉");
                System.out.println("\n¡Gracias por tu compra!");
            } else {
                System.out.println("\n❌ Pago fallido. Por favor intenta nuevamente.");
            }
        }
        scanner.close();
    }
}