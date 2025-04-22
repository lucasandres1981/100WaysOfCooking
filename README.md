# 100WaysOfCooking

## Descripción General

100WaysOfCooking es una aplicación móvil desarrollada en Kotlin con Jetpack Compose como proyecto universitario colaborativo. La app permite explorar una colección diversa de recetas, ver instrucciones paso a paso, acceder a videos explicativos y guardar las recetas favoritas por cada usuario. El objetivo principal fue diseñar una experiencia de usuario limpia, funcional y visualmente atractiva para cualquier tipo de usuario interesado en la cocina casera.

## Objetivo del Proyecto

Crear una app que permita:
- Consultar recetas de forma dinámica.
- Añadir o quitar recetas favoritas según el usuario que haya iniciado sesión.
- Visualizar videos de recetas a través de YouTube.
- Navegar entre pantallas de forma fluida.
- Buscar tiendas cercanas para conseguir ingredientes usando un mapa interactivo.
- Personalizar el perfil del usuario, incluyendo selección de avatar y preferencias.

## Funcionalidades Clave

- Registro y login de usuarios con almacenamiento local.
- Pantalla de inicio con menú hamburguesa y acceso a todas las secciones.
- Pantalla de búsqueda de recetas.
- Pantalla de búsqueda de Web.
- Vista de detalle con ingredientes, pasos e integración de video.
- Botón para añadir o quitar de favoritos (persistente por usuario).
- Sección de recetas favoritas personalizadas.
- Pantalla de edición de perfil y selección de avatar.
- Pantalla de mapa para encontrar tiendas cercanas.
- Diseño responsive con uso de Material 3.

## Tecnología Utilizada

- Lenguaje: Kotlin
- Framework UI: Jetpack Compose
- Navegación: Navigation Compose
- Persistencia: Archivos locales `.txt` (usuarios, preferencias, favoritos)
- Mapa: OSMDroid + OpenStreetMap
- Reproducción de video: WebView con URLs de YouTube
- IDE: Android Studio

## Estructura del Proyecto

El proyecto está organizado en paquetes como:

- `data`: contiene clases como `Receta`, `RecetasDataSource`, `User`, `SessionManager`, `FavoriteRecipesStorage`, etc.
- `ui/screens`: contiene cada una de las pantallas (login, home, favoritos, perfil, detalle, etc.).
- `ui/components`: botones personalizados, menús, scaffolds.
- `navigation`: rutas entre pantallas.
- `res/drawable`: imágenes de recetas, íconos y avatares.

## Desarrollo en Equipo

Durante el desarrollo nos reunimos constantemente para revisar avances, discutir errores y definir mejoras. Los últimos días se trabajó en la validación de email en el registro, el guardado correcto de favoritos por usuario, la corrección del diseño visual, y la restauración de pantallas dañadas por conflictos de merge. Se verificaron todas las funcionalidades y se hicieron pruebas de ejecución. La clase `YouTubeWebViewActivity` fue restituida, y se incorporaron más de 30 recetas completas con imágenes.

## Consideraciones Finales

Este proyecto es totalmente funcional y puede ampliarse fácilmente para integrar bases de datos externas, login con Firebase, y más funcionalidades como filtros avanzados o recomendaciones inteligentes.

---
![{CB541F71-B687-4459-8151-A0C396A80A1D}](https://github.com/user-attachments/assets/b90e61ab-445a-4101-bbfa-793db13b7079)
![{28A30300-5745-44E4-9B2E-1B2209DD0250}](https://github.com/user-attachments/assets/2a0489db-bb56-4c57-a257-7e218a7538f4)
![{690AB445-4735-4D11-A75F-8BBC5AB26739}](https://github.com/user-attachments/assets/68830854-6d3e-495a-818a-8dcf2d023c96)


Desarrollado por el equipo del Subgrupo 10. Repositorio principal:  
https://github.com/lucasandres1981/100WaysOfCooking
