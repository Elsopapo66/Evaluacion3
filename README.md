# ☕ Evaluación 3 - Sistema de Cafetería con Patrones de Diseño

## 🧑‍💻 Integrante
- **Nombre:** Jesús Vidal

## 🎯 Descripción del Sistema y Problema a Resolver
**Sistema:** Aplicación de consola para gestión de pedidos en una cafetería que permite personalizar cafés, aplicar descuentos, procesar pagos y notificar cuando los pedidos están listos.

**Problemas resueltos:**
1. **Personalización compleja de cafés**: Diferentes combinaciones de ingredientes (leche, azúcar, extras) que complican la creación del objeto.
2. **Integración con múltiples pasarelas de pago**: APIs de pago externas con interfaces incompatibles.
3. **Políticas de descuento variables**: Necesidad de aplicar diferentes algoritmos de descuento de forma flexible.
4. **Notificaciones a clientes**: Avisar a los clientes cuando su pedido esté listo sin acoplamiento fuerte.

## 🧩 Justificación Técnica de Patrones

### 1. Builder (Patrón Creacional)
- **¿Por qué?** 
  Para simplificar la creación de objetos complejos (cafés personalizados) con múltiples opciones configurables.
- **¿Cómo?** 
  A través de la clase `CafeBuilder` que permite añadir ingredientes paso a paso mediante métodos encadenados.
- **¿Dónde?** 
  En el proceso de construcción del café (`src/creacional/CafeBuilder.java`).
- **Beneficio:** Elimina la necesidad de constructores telescópicos y mejora la legibilidad del código.

### 2. Adapter (Patrón Estructural)
- **¿Por qué?** 
  Para unificar interfaces diferentes de sistemas de pago externos (PayPal y Stripe) bajo una interfaz común.
- **¿Cómo?** 
  Implementando adaptadores (`PayPal.java`, `Stripe.java`) que implementan la interfaz `PagoAdapter`.
- **¿Dónde?** 
  Durante el procesamiento de pagos (`src/estructural/`).
- **Beneficio:** Permite integrar nuevas pasarelas de pago sin modificar el código existente.

### 3. Strategy (Patrón de Comportamiento)
- **¿Por qué?** 
  Para gestionar diferentes algoritmos de descuento (estudiante, membresía) de forma intercambiable.
- **¿Cómo?** 
  Mediante la interfaz `EstrategiaDescuento` y clases concretas que implementan cada política.
- **¿Dónde?** 
  Al aplicar descuentos al precio final (`src/comportamiento/`).
- **Beneficio:** Facilita añadir nuevos descuentos sin alterar el código cliente.

### 4. Observer (Patrón de Comportamiento)
- **¿Por qué?** 
  Para notificar a los clientes cuando eventos importantes ocurren (pedido listo) sin acoplamiento.
- **¿Cómo?** 
  Implementando un sistema de suscripción/notificación con `SistemaNotificacion` y `Observador`.
- **¿Dónde?** 
  Al finalizar la preparación del café (`src/observer/`).
- **Beneficio:** Permite notificar a múltiples clientes eficientemente.

## 🛠️ Instrucciones de Compilación y Ejecución

### Requisitos Previos
- Java JDK 11 o superior

### Pasos para Ejecutar desde Terminal
1. **Clonar el repositorio**:
```bash
git clone https://github.com/Elsopapo66/Evaluacion3.git
cd Evaluacion3
