# Banco App: Simulación de Pagos en Android

![Logo del Proyecto](https://static.vecteezy.com/system/resources/previews/049/649/588/non_2x/a-bank-building-icon-with-columns-and-pillars-free-vector.jpg)

![Versión](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Licencia](https://img.shields.io/badge/license-MIT-green.svg)
![Plataforma](https://img.shields.io/badge/Platform-Android-brightgreen.svg)
![Kotlin](https://img.shields.io/badge/Made%20with-Kotlin-orange.svg)

Una aplicación Android nativa que simula un flujo de autenticación de usuario y un proceso de pago con tarjeta de crédito, construida con Kotlin y Android Studio.

---

## Tabla de Contenidos

1.  [Acerca del Proyecto](#acerca-del-proyecto)
2.  [Capturas de Pantalla](#capturas-de-pantalla)
3.  [Características Principales](#características-principales)
4.  [Tecnologías Utilizadas](#tecnologías-utilizadas)
5.  [Cómo Empezar](#cómo-empezar)
    * [Prerrequisitos](#prerrequisitos)
    * [Instalación](#instalación)
6.  [Uso de la Aplicación](#uso-de-la-aplicación)
7.  [Licencia](#licencia)
8.  [Contacto](#contacto)

---

## Acerca del Proyecto

**Banco App** es un proyecto desarrollado para Android que demuestra la implementación de un flujo de usuario común en aplicaciones de comercio electrónico o servicios:

1.  **Autenticación:** Una pantalla de inicio de sesión que valida credenciales específicas.
2.  **Transacción:** Un formulario detallado para simular un pago con tarjeta de crédito, incluyendo validaciones de campos y un monto generado de forma aleatoria.
3.  **Resultado:** Una pantalla final que informa al usuario si la transacción fue exitosa o fallida, basándose en una probabilidad predefinida.

---

## Capturas de Pantalla



## Características Principales

-   👤 **Módulo de Autenticación**: Simulación de inicio de sesión con credenciales predefinidas.
-   💳 **Formulario de Pago Completo**: Captura de nombre, número de tarjeta, tipo (Visa, MasterCard, Amex), fecha de expiración, CVV y correo electrónico.
-   ✔️ **Validación de Datos**: Verificación del formato de correo electrónico y longitud de campos críticos (número de tarjeta, CVV).
-   🎲 **Monto de Pago Aleatorio**: El total a pagar se genera dinámicamente en un rango de `$100.00` a `$5,000.00`.
-   📈 **Simulación de Resultado**: La operación tiene un `75%` de probabilidad de ser exitosa y un `25%` de ser rechazada para simular un entorno real.
-   📄 **Resumen de Transacción**: La pantalla final muestra un resumen con los datos de la operación realizada.

---

## Tecnologías Utilizadas

-   **IDE:** Android Studio
-   **Lenguaje:** Kotlin
-   **Arquitectura de UI:** Vistas de Android (XML Layouts)
-   **Componentes Clave:** Activities, Intents, EditText, Spinner, TextView, Button.

---

## Cómo Empezar

Sigue estos pasos para obtener una copia local del proyecto y ponerla en funcionamiento.

### Prerrequisitos

Asegúrate de tener instalado:
* [Android Studio](https://developer.android.com/studio) (versión recomendada: Hedgehog o superior)
* Git

### Instalación

1.  **Clona el repositorio:**
    ```sh
    git clone https://github.com/Shaicko/BancoApp.git
    ```
2.  **Abre el proyecto en Android Studio:**
    * Ve a `File` -> `Open`.
    * Navega hasta la carpeta donde clonaste el repositorio y selecciónala.
3.  **Ejecuta la aplicación:**
    * Espera a que Gradle sincronice el proyecto.
    * Selecciona un emulador o un dispositivo físico y presiona el botón `Run 'app'`.

---

## Uso de la Aplicación

1.  Al abrir la app, se te presentará el formulario de inicio de sesión.
2.  Utiliza las siguientes credenciales para acceder:

    * **Usuario:** `tallercmovil`
    * **Contraseña:** `t4ll3rcm0v1l?`

3.  Una vez autenticado, llena el formulario de pago con datos de prueba.
4.  Presiona el botón "Pagar" para simular la transacción y ver la pantalla de resultado.

---

## Licencia

Distribuido bajo la Licencia MIT. Consulta el archivo `LICENSE` para más información.

---

## Contacto

-   **Nombre:** Leonardo Ariel Berdejo Guzmán
-   **Email:** berdejoleon@gmail.com
-   **Enlace del Proyecto:** [https://github.com/Shaicko/BancoApp](https://github.com/Shaicko/BancoApp.git)
