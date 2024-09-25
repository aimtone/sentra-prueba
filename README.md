# Sentra Prueba

Este proyecto es una prueba de Spring Boot para manejar usuarios.

## Tabla de Contenidos
- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Configuración](#configuracion)
- [Uso](#uso)
- [Notas](#notas)

## Características
- API para la gestión de usuarios.
- Autenticación con JWT.
- Base de datos en memoria con H2.

## Tecnologías
- **Lenguaje**: Java 17
- **Framework**: Spring Boot 4
- **Herramientas**: Maven, H2 Database, JWT (Integreado)

## Requisitos

- [Instalar Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Maven integrado en el proyecto mediante wrapper.

## Instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/aimtone/sentra-prueba.git

2. Ubicarse en la carpeta raíz del proyecto:
   ```bash
   cd sentra-prueba

3. Ejecutar la aplicación:
   
    - En Windows
        ```bash
      mvnw.cmd spring-boot:run
   - En Linux
       ```bash
     mvnw spring-boot:run

## Configuración
El proyecto está configurado para usar una base de datos en memoria H2. Los parámetros más relevantes ya están configurados en el archivo ``application.properties`` para JPA, Hibernate y JWT.

## Uso
Descargar un software para probar API Rest como [Postman](https://www.postman.com/downloads/) y ejecutar el siguiente ``curl``. Tambien lo puedes hacer directamente en la consola de Windows si tienes instalado *Curl* en tu PC
```curl
curl --location 'localhost:8080/api/v1/usuario' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=0C7AC9A2AAF70E9E593D5C03DC27BABD' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juasn@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "58"
        }
    ]
}'
```

### Ejemplo de respuesta correcta
Cuando se realiza correctamente el registro de usuario y el ``curl`` retorna información válida del usuario que ha sido grabada en base de datos
```json
{
    "id": "96758317-4d82-44c6-a9a0-62bb36328bc4",
    "name": "Juan Rodriguez",
    "email": "juasn@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "id": 1,
            "number": "1234567",
            "citycode": "1",
            "contrycode": "58"
        }
    ],
    "created": "2024-09-25T01:32:02.75731",
    "modified": "2024-09-25T01:32:02.75731",
    "last_login": "2024-09-25T01:32:02.7162701",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFzbkByb2RyaWd1ZXoub3JnIiwiaWF0IjoxNzI3MjM4NzIyLCJleHAiOjE3MjczMjUxMjJ9.FjfkYLtR7mgQCvnFWCfLqVU-mBoo-YtvTcbRShyGvdoC6S-T6aOh8-AttCY8sBhSLjjOIRYUXay6FJJ4E9XcbA",
    "isactive": true
}
```
### Ejemplo de respuesta incorrecta
Cuando ocurre algún problema de validación y/o registro de usuario a nivel de base de datos, el programa retorna una respuesta como esta
```json
{
    "mensaje": "El correo electrónico ya está registrado"
}
```
Este ejemplo corresponde a la validación de correo electrónico cuando ya existe en base de datos, cabe destacar que el programa válida tambien valores nulos o en blanco, asi como que la contraseña de usuario cumpla con ciertas caracteristicas y que el correo electrónico del usuario cumpla con el formato de email.

## Notas

Para ver los datos almacenados en la base de datos H2 puedes hacerlo ingresando a la siguiente url
```
http://localhost:8080/h2-console/
```

Las credenciales usadas son las siguientes, que tambien puedes encontrarlas en el archivo ``application.properties``

```
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password:	
```