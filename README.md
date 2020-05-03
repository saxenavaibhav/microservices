The Globomart Product Catalog Microservice

Steps to run the service:

1. Extract the contents of GloboMart.zip on the local drive.
2. Using a termincal CD into the GloboMart directory and run 'mvn clean install'.
3. Start the (Spring Boot) projects in the following order (Either using Eclipse or mvn spring-boot:run):
	- confsvr
	- eurekssvr
	- ProductCatalogueService
	- zuulsvr
4. A in-memory database (H2) is configured to work with the application:

H2 console can be accessed at: http://localhost:8080/h2-console/

A PRODUCTS table has been created by default and this will contain product definitions for 5 products. This will be done at startup.

To Test the API, make use of Swagger docs: http://localhost:8080/swagger-ui.html#/

The Product Catalog Microservice demo consists of teh following core components:

1. Configuration Server - This is the configuration server. This helps separate the application configuration from the actual deployed code.
Configuration environment will automatically be passed to the starting service when the service starts. We use 'Spring Cloud Configuraion Server' for this implementation. Runs on port 8888.
The configuration of productservice lives inside /resources/config/productservice/productservice-<env>.yml

The JSON payload containing the configuration is available at: http://localhost:8888/productservice/default

2. Eureka Server - This is the Service discovery server. As service instances start up, they’ll register their physical location, path, and port with the service discovery instances. 
Eureka runs on port 8761.
Eureka REST endpoint to see contents of registry: http://localhost:8761/eureka/apps/PRODUCTSERVICE
Eureka Console is available at: http://localhost:8761/

For demo purposes, a RIBBON aware RESTTemplate instance has been configured. It is not being used as of now. (We only have a single microservice).

3. ZUUL Server - used for service routing. Act as a filter and router for all the microservice calls in your application.This means our service clients no longer directly call a service. Instead, all calls are routed through the service gateway. This service gateway runs on port 5555.

A sample call can be made to one of the resources (GET products by product type) using the ZUUL gateway:

http://localhost:5555/productservice/v1/catalog/cat/product/producttype/TYPE

4. ProductCatalogueService - This is the main Product catalogue microservice that exposes the following endpoints:

- v1/catalog/{catalogId}/product/producttype/{producttype} - Find products by product type.
- v1/catalog/{catalogId}/product -  Add a new product
- v1/catalog/{catalogId}/product/{productId} - Delete a product by product ID.

The swagger documentation for this API is available at: http://localhost:8080/swagger-ui.html#/

On server startup, it calls the configuration registry at http://localhost:8080 and reads all configuration.
It also registers itself with service discovery agent EUREKA at startup.


NOTES:
- Short circuit has been implemented with the help of Hystrix libraries. In case a service takes more than 12 seconds, circuit breaker kicks in.
- Bulkhead has bene implemented in public List<ProductDto> findByProductType(final String productType) method where we define a fallback method in case the service takes more than 12 seconds.
- Spring boot actuator has been configured for monitoring: http://localhost:8080/actuator/