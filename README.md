# Mi Precursorado

**Mi Precursorado** es una aplicación Android diseñada para ayudar a los precursores a gestionar y realizar un seguimiento de sus horas de predicación de manera eficiente.

## Características

- **Vista Mensual**: Lista y visualiza todos los días de predicación del mes actual o seleccionado.
- **Registro de Tiempo**: Registra fácilmente el tiempo de predicación para cualquier día.
- **Acumulación Inteligente**: Si se registra tiempo para un día que ya tiene una entrada, la aplicación permite acumular el nuevo tiempo en el registro existente.
- **Validación de Fecha**: Evita el registro de tiempo de predicación para fechas futuras para garantizar la integridad de los datos.
- **Persistencia de Datos**: Utiliza una base de datos local para asegurar que tus registros estén siempre disponibles sin conexión.

## Arquitectura y Stack Tecnológico

El proyecto sigue los principios de Clean Architecture para asegurar la mantenibilidad y testabilidad.

- **Lenguaje**: [Kotlin](https://kotlinlang.org/)
- **Framework de UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Base de Datos**: [Room](https://developer.android.com/training/data-storage/room) para persistencia local.
- **Arquitectura**: Diseño Orientado al Dominio (DDD) con una clara separación entre las capas de Dominio, Casos de Uso, Persistencia y UI.

## Estructura del Proyecto

- `domain/`: Contiene los modelos de negocio, interfaces de repositorios y excepciones personalizadas.
- `usecases/`: Implementación de la lógica de negocio específica de la aplicación.
- `persistence/`: Configuración de la base de datos Room, entidades y DAOs.
- `ui/`: Pantallas y componentes de Compose.

## Requisitos

- Android Studio Ladybug o superior.
- Android SDK 26 (Android 8.0) o superior (requerido para las APIs de `java.time`).

## Licencia

Este proyecto es para uso personal y gestión de la actividad de predicación.
