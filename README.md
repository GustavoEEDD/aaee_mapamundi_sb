# Uso de Docker y Docker Compose

Este proyecto utiliza **Docker** para contenerizar la aplicación Spring Boot y **Docker Compose** para levantar la aplicación junto con MySQL.

## Conceptos Clave

### Imágenes (`image`)
Contienen el sistema operativo, JDK/JRE y la aplicación empaquetada (`.jar`).

### Contenedores (`container`)
Instancias de las imágenes que se ejecutan de forma aislada.
Se pueden asignar nombres con `--name` para facilitar su gestión.

### Multi-stage build
- **Stage 1**: build de Maven para generar el `.jar`
- **Stage 2**: runtime con JRE/JDK ligero

Permite que la imagen final sea **más ligera** y solo contenga lo necesario para ejecutar la app.

### Puertos (`ports`)
Se mapean del contenedor al host con el formato `"host:container"`.

```yaml
ports:
  - "8080:8080"  # acceso a Spring Boot desde el navegador
```

### Variables de entorno (`environment`)
Configuran la aplicación y servicios externos como la conexión a MySQL.

```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/mapamundi
  SPRING_DATASOURCE_USERNAME: root
  SPRING_DATASOURCE_PASSWORD: secret
```

### Volúmenes (`volumes`)
Persisten datos fuera del contenedor. Hay dos tipos:

- **Bind mounts**: montan una ruta específica del host (`./data:/var/lib/mysql`). El contenido depende de lo que haya en esa ruta local.
- **Named volumes**: gestionados por Docker (`db_data:/var/lib/mysql`), declarados al final del `docker-compose.yml`. Son la opción recomendada para bases de datos.

```yaml
volumes:
  db_data:/var/lib/mysql

# Al final del docker-compose.yml:
volumes:
  db_data:
```

### Redes (`networks`)
Docker Compose crea automáticamente una red interna para que los servicios se comuniquen por nombre (`db` en lugar de IP), permitiendo usar `jdbc:mysql://db:3306/...` en Spring Boot.

### Dependencias entre servicios (`depends_on`)
Controla el orden de arranque, pero **solo garantiza que el contenedor haya iniciado**, no que el servicio esté listo para aceptar conexiones. MySQL puede tardar unos segundos en estar operativo, lo que puede provocar fallos en Spring Boot al arrancar.

Para esperar a que MySQL esté realmente listo, se combina con `healthcheck`:

```yaml
db:
  image: mysql:8
  healthcheck:
    test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
    interval: 10s
    timeout: 5s
    retries: 5

app:
  depends_on:
    db:
      condition: service_healthy
```

## Comandos básicos

```bash
# Construir imagen Docker (desde la carpeta en la que está el fichero Dockerfile)
docker build -t mapamundi-app .

# Lanzar un contenedor asociado a una imagen Docker
docker run -p 8080:8080 --name mapamundi_sb_xxx mapamundi-app

# Ver contenedores en ejecución
docker ps

# Levantar servicios con Docker Compose (desde la carpeta en la que está el fichero docker-compose.yml)
docker compose up -d

# Seguir los logs en tiempo real (muy útil en desarrollo)
docker compose logs -f

# Detener contenedores
docker compose down
```

---
Fuentes: [ChatGPT](https://chat.openai.com) + [Claude](https://claude.ai)