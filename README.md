Gestión de Agenda y Reuniones

Este proyecto es un sistema de gestión de actividades académicas y personales desarrollado en Java. Permite a los usuarios organizar su tiempo mediante el registro de clases, evaluaciones y reuniones, proporcionando herramientas para el seguimiento de la carga de trabajo y persistencia de datos a través de archivos CSV.
Requisitos del sistema

    Java SE Development Kit (JDK) 11.

    Entorno de desarrollo recomendado: NetBeans, IntelliJ IDEA o Eclipse.

Para qué sirve el programa

El sistema centraliza la organización diaria permitiendo:

    Registro de actividades específicas: Permite diferenciar entre clases universitarias (sala, profesor, asignatura), evaluaciones (ponderación, temario, tipo de grupo) y reuniones (anfitrión).

    Monitor de intensidad: Filtra y reporta actividades críticas en rangos de fechas específicos para evaluar periodos de alta exigencia académica.

    Persistencia automática: Carga información al iniciar y guarda todos los cambios al cerrar el programa mediante el archivo datos_agenda.csv.

    Interfaz dual: Ofrece una experiencia de uso a través de la terminal de comandos o mediante ventanas gráficas (GUI).

Cómo instalarlo

    Verifique que la versión de Java instalada en su equipo sea la 11 mediante el comando java -version en su terminal.

    Descargue o clone los archivos del repositorio en su máquina local(https://github.com/yetybe/-Gesti-n-de-Agenda-y-reuniones.git).

    Asegúrese de que el archivo datos_agenda.csv se encuentre en la raíz del proyecto para que la carga inicial de datos se realice correctamente.

    Abra la carpeta del proyecto en su entorno de desarrollo preferido (IDE).

    Si utiliza NetBeans, el proyecto se reconocerá automáticamente como un proyecto de Maven o Ant.

Cómo ocuparlo

    Localice la clase Main.java dentro del paquete principal y ejecútela.

    Al iniciar, el programa cargará los datos existentes en el archivo CSV.

    Se desplegará una interfaz inicial donde podrá elegir entre:

        Terminal: Para un uso rápido mediante comandos numéricos y entradas de texto.

        Ventanas: Para una navegación visual a través de botones y tablas.

    En el menú principal podrá seleccionar opciones como Habilitar Fecha, Agregar Actividad, ver Reportes o Eliminar registros.

    Al finalizar las gestiones, asegúrese de salir formalmente del programa a través de la opción Salir para que el método de guardado actualice el archivo CSV con la información más reciente.

Autores

    Vicente Girardin

    Benjamin Vargas

    Piero Yany
