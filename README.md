# InAdvice Excercise


## Requested Requirements.

Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de error.
Todos los mensajes deben seguir el formato:
{"mensaje": "mensaje de error"}

## Registro

- Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más
un listado de objetos "teléfono", respetando el siguiente formato:

{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}

- Responder el código de status HTTP adecuado
- En caso de éxito, retorne el usuario y los siguientes campos:
- id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más
deseable un UUID)
- created: fecha de creación del usuario
- modified: fecha de la última actualización de usuario
- last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha
de creación)
- token: token de acceso de la API (puede ser UUID o JWT)
- isactive: Indica si el usuario sigue habilitado dentro del sistema.
- Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya
registrado".
- El correo debe seguir una expresión regular para validar que formato sea el correcto.
(aaaaaaa@dominio.cl)
- La clave debe seguir una expresión regular para validar que formato sea el correcto. (El
valor de la expresión regular debe ser configurable)
- El token deberá ser persistido junto con el usuario


## Requisitos:
- Plazo: 2 días, si tienes algún inconveniente con el tiempo comunicate con nosotros
- Banco de datos en memoria. Ejemplo: HSQLDB o H2.
- Proceso de build vía Gradle o Maven.
- Persistencia con JPA. Ejemplo: EclipseLink, Hibernate u OpenJPA.
- Framework Spring Boot.
- Java 8+
- Entrega en un repositorio público (github, gitlab o bitbucket) con el código fuente y script de
creación de BD.
- Readme explicando cómo probarlo.
- Diagrama de la solución.

## Requisitos opcionales
- JWT como token
- Pruebas unitarias
- Swagger

# El proyecto contiene

  - H2.
  - Java version 8.
  - Script con usuario de prueba (username = admin@gmail.com, password = Pruebaapi1)
  - Api Rest login (para la primera prueba utilizar el usuario indicado en punto anterior).
  - Apis con autenticacion mediante JWT: users(crear usuario), all(obtener todos los usuarios), profile (obtener usuario por email), eliminar
  - JWT token.
  - Uso de Bcrypt para el password
  - Swagger
  - Build vía Maven
  - Validacion de password = Que contenga al menos una letra minúscula, una letra mayúscula, un dígito y tenga una longitud mínima de 8 caracteres


###  Antes de empezar

### Versiones utilizadas

| Dependency | Version |
| ------ | ------ |
| SpringToolSuite| 4|
| Java JDK  | 1.8 |
| Spring Boot  | 2.7.18 |


### Instalacion

- Tener version de java 8 instalada
- Clonar proyecto.
- En la consola,  ir a la ruta del proyecto y ejecutar mvn spring-boot:run -Dspring-boot.run

#Url swagger UI
http://localhost:8090/swagger-ui.html#/

------------------------------------------------------------------------------------------

### Apis


#1. Consultar la api "api/v1/login":

 - http://localhost:8090/api/v1/login
   Esta api se utiliza para que un usuario registrado, utilizando email y contraseña, inicie sesion y obtenga su token. Al iniciar sesion exitosa se actualiza en BD el token y la fecha de ultimo login..
	 Para el primer ingreso utilizar el usuario de prueba, con eso se obtendrá el token para probar las otras apis
   Ejemplo Body

 ```json
    {
         "email": "admin@gmail.com",
         "password": "Pruebaapi1"
     }
 ```
 	
 	Response
 	
	{
            "username": "admin@gmail.com",
            "token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNzE1NjU3ODgzLCJleHAiOjE3MTU3NDQyODN9.obYr1iDhAPA7LYXRt6GRLE6EscDv64i7KqZiaqf_S-0"
  }
 	
#2. Apis Crud

 - Con el token ya generado, podemos realizar las operaciones de la aplicación, accede a las APIs con el token generado, agregando siempre el token en la opción Autorización como "Bearer Token" .	

Ejemplo

![alt text](https://github.com/alanisPao/desafio-inAdvance-bci/blob/master/token.png?raw=true)

#1. Api Crear Usuario : http://localhost:8090/api/v1/users/
Debe recibir en el cuerpo un usuario con los campos "nombre", "email", "password", más una lista de objetos "phone", siguiendo el siguiente formato:

```json
   {
        "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
   }
```
#2. Api Obtener perfil usuario : http://localhost:8090/api/v1/users/profile?email=ejemplo@gmail.com
Se debe enviar el token y consultar por el email del usuario, retorna un son con los datos del usuario

#3. Api obtener todos los usuarios : http://localhost:8090/api/v1/users/all
Devuelve todos los usuarios registrados

#4. Api eliminar usuario : http://localhost:8090/api/v1/users/delete/?
Para eliminar a un usuario se debe enviar el id como parámetro

Todas las apis del crud se prueban con el token generado por JWT.
