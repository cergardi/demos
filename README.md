#Demo ejemplo usuarios#

###Proyecto de ejemplo de la gestión API REST (CRUD) de usuarios. ###

Para la realización de la demo se ha utilizado:

- Eclipse Oxygen por el conocimiento de la herramienta y de sus plugins
- MySql Server Base de datos
- Maven para la construcción del proyecto
- GIT para tener un repositorio y un control de cambios
- Spring:boot para facilitar los despliegues
- Spring:data para facilitar el acceso a datos
- Swagger para generar la documentación


Adicionalmente en otra versión, se entregará con:

- Spring Security OAuth: Aplicará la seguridad a la aplicación
- Spring Test: Realizará los test de integración 

## Ejecución de la aplicacion ##

- Compilar y empaquetar
	mcn clean compile package
- Instalar aplicación
	mvn clean install


##Ejemplo peticiones##

- Autenticación como administrador:

	`$ curl testjwtclientid:XY7kmzoNzl100@localhost:8080/DemoApplication/oauth/token -d grant_type=password -d username=admin.admin -d password=jwtpass`

- Autenticación como usuario:

	`$ curl testjwtclientid:XY7kmzoNzl100@localhost:8080/DemoApplication/oauth/token -d grant_type=password -d username=usuario.ricoh -d password=jwtpass`
	
- Listar todos los usuarios:
	`$ curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4uYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTE3NDc4MDY3LCJhdXRob3JpdGllcyI6WyJTVEFOREFSRF9VU0VSIiwiQURNSU5fVVNFUiJdLCJqdGkiOiIyMzY0Yzg1Mi0xZjQ3LTRiNzgtYmNjYS1hMDljNjE0ZGEzYWMiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQifQ.Uvnsq8pXif1XgDPbp-RnwV7sunEtSqi5hUEQcUqVDv4" http://localhost:8080/DemoApplication/api/user/`

- Obtener usuario:

	`$ curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4uYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTE3NDc4MDY3LCJhdXRob3JpdGllcyI6WyJTVEFOREFSRF9VU0VSIiwiQURNSU5fVVNFUiJdLCJqdGkiOiIyMzY0Yzg1Mi0xZjQ3LTRiNzgtYmNjYS1hMDljNjE0ZGEzYWMiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQifQ.Uvnsq8pXif1XgDPbp-RnwV7sunEtSqi5hUEQcUqVDv4" http://localhost:8080/DemoApplication/api/user/1`

- Crear Usuario:
	
	`$ curl -i -X POST -H "Content-Type:application/json" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGVzdGp3dHJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4uYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNTE3NDc4MDY3LCJhdXRob3JpdGllcyI6WyJTVEFOREFSRF9VU0VSIiwiQURNSU5fVVNFUiJdLCJqdGkiOiIyMzY0Yzg1Mi0xZjQ3LTRiNzgtYmNjYS1hMDljNjE0ZGEzYWMiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQifQ.Uvnsq8pXif1XgDPbp-RnwV7sunEtSqi5hUEQcUqVDv4" -d "{\"id\" : \"4\", \"nombre\": \"Usuario creado\", \"apellidos\": \"Peticion Post\"}" http://localhost:8080/DemoApplication/api/user/`
	


## Documentación Asociada##

- https://docs.spring.io/spring-boot/docs/current/api/
- https://swagger.io/docs/specification/about/
- https://docs.spring.io/spring-data/data-jpa/docs/current/api/
- https://maven.apache.org/developers/mojo-api-specification.html
- http://help.eclipse.org/oxygen/index.jsp
