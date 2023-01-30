# Proyecto desarrollado en Spring Boot JPA MYSQL Spring Security with Maven

A. Servicios REST con Persistencia de datos.
Se utiliza un esquema local en MySQL.
a. Servicio de creación de usuarios que deberá recibir
i. Name
ii. Username
iii. Email
iv. Phone
b. Listado de usuarios registrados.
c. Obtención de usuario registrado mediante email.
d. Eliminar un usuario.
e. Los servicios de creación y eliminación deben estar protegidos con seguridad (como opción puede ser Spring Security, utilizando un
usuario y contraseña que esté dentro de los properties de la aplicación).

Para esta primera parte del proyecto fue desarrollado en Spring boot, para la persistencia se utilizo JPA Y un esquema local de BD 
de MYSQL. Para la proteccion de Seguridad se utilizo Spring Security.


B. Generar un servicio POST que consultará a una API externa y que deberá
considerar la recepción de un parámetro (param)
a. Servicio deberá cifrar el parámetro utilizando DES con la llave
“ionix123456”
b. Se deberá invocar la siguiente URL entregando el parámetro cifrado
como valor (se debe incluir el header indicado para poder realizar el
consumo)
i. GET https://my.api.mockaroo.com/test-tecnico/search/{parametro_cifrado}
ii. {parametro_cifrado} es el valor que se recibe en el servicio (param) cifrado
con DES
iii. Header- > X-API-Key: f2f719e0
c. El valor parámetro de prueba es “1-9”
C. Servicio debe retornar un objeto JSON en el formato indicado con la
cantidad de registros devuelto por el servicio llamado anteriormente. En el
campo “elapsedTime” se debe entregar el tiempo de ejecución del servicio
“search” en milisegundos.

Para esta Segunda parte se trabajo con RestTemplate para la invocacion del servicio.

Enpoints:
Listado de usuarios registrados:
curl --location --request GET 'http://localhost:8080/public/users' \
--header 'Content-Type: application/json' \

Obtencion de usuario registrado mediante ID:
curl --location --request GET 'http://localhost:8080/public/users/{id}' \
--header 'Content-Type: application/json' \

Obtencion de usuario registrado mediante email:
curl --location --request GET 'http://localhost:8080/public/users/email/{email}' \
--header 'Content-Type: application/json' \

Servicio de creacion de usuarios.
curl --location --request POST 'http://localhost:8080/admin/users/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlcjoxMjM0' \
--data-raw '{
"name":"Amelia Rodriguez",
"userName":"arodriguez",
"email":"arodriguez@gmail.com",
"phone":"04147979906"
}'

Eliminar un usuario.
curl --location -g --request DELETE 'http://localhost:8080/admin/users/{id}' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' \

Generar un servicio POST que consultar a una API externa y que deber considerar la recepcion de un parametro cifrado.
curl --location --request GET 'http://localhost:8080/api' \
--header 'Content-Type: application/json' \

SCRIPT DE BASE DE DATOS:
new_user_users.txt