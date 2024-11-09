# Guia para Ejecutar Proyectos
En los proyectos realizados en este repositorio, el archivo `module-info.java` ha sido eliminado de estos proyectos. Para ejecutar el proyecto correctamente sin este archivo, sigue los pasos a continuación.

## Pasos para Ejecutar el Código sin module-info.java
**Error esperado**: Al intentar ejecutar el código sin module-info.java, es probable que encuentres un error relacionado con los módulos de JavaFX. Para solucionarlo, sigue estos pasos en IntelliJ.

### Configura la Ejecución en IntelliJ:
En la barra de menú de IntelliJ, selecciona `Run` y luego haz clic en `Edit Configurations`.
Añade una nueva configuración de aplicación (`Application`).
Especifica el nombre de la clase principal (`main class`) de tu proyecto.

### Agrega las Opciones de VM:
Haz clic en Modify options y selecciona `Add VM options`.
En el campo de opciones de VM, añade la siguiente línea, reemplazando la ruta de **javafx-sdk** con la ubicación correspondiente en tu sistema:
```
--module-path "C:\Program Files\Java\openjfx-18_windows-x86_bin-sdk\javafx-sdk-18\lib" --add-modules javafx.controls,javafx.fxml
```
> [!NOTE]
> Nota: Asegúrate de que la ruta coincide con la ubicación de tu SDK de JavaFX.

### Ejecuta el Proyecto: 
Una vez configurado, deberías poder ejecutar el proyecto sin problemas.

---
>_IES Ribera de Castilla 24/25._
