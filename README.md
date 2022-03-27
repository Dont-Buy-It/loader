# loader
API for obtaining external brands, brand images, products and actions data.

### Environment variables
```
ADMIN_USERNAME=admin
ADMIN_PASSWORD=admin
BRANDS_CSV_URL=https://static.dwcdn.net/data/4yMOy.csv
BRAND_IMAGES_CSV_URL=https://docs.google.com/spreadsheets/d/1eX7JGrhg9Jagl0zVj3_NkRfhZmpKDbDbq8z4e-kil38/gviz/tq?tqx=out:csv&sheet=brand-images
PRODUCTS_CSV_URL=https://docs.google.com/spreadsheets/d/1eX7JGrhg9Jagl0zVj3_NkRfhZmpKDbDbq8z4e-kil38/gviz/tq?tqx=out:csv&sheet=products
ACTIONS_CSV_URL=https://docs.google.com/spreadsheets/d/1eX7JGrhg9Jagl0zVj3_NkRfhZmpKDbDbq8z4e-kil38/gviz/tq?tqx=out:csv&sheet=actions
CACHE_CRON=0 0 */12 ? * *
```

### Running the application locally
```shell
gradle bootRun
```
Application runs on http://localhost:2000

### Running unit tests
```shell
gradle test
```

### Running integration tests
```shell
gradle integrationTest
```

### Documentation
Swagger documentation: https://dont-buy-it-loader.herokuapp.com/swagger-ui/index.html

OpenAPI documentation: https://dont-buy-it-loader.herokuapp.com/spec/index

### Actuator
https://dont-buy-it-loader.herokuapp.com/actuator

### CSV sources
Brands: https://static.dwcdn.net/data/4yMOy.csv

Brand Images, Products, Actions: https://docs.google.com/spreadsheets/d/1L4vejFtHrg5v4PLy2x4cUsobTJxQtqHzgQtR_GnQrWI/edit?usp=sharing
