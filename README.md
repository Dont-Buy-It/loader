<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Don't Buy It Loader</h3>

  <p align="center">
    API for obtaining external brands and products data
    <br />
    <br />
    <a href="https://dont-buy-it-loader.herokuapp.com/swagger-ui/index.html">Swagger</a>
    ·
    <a href="https://dont-buy-it-loader.herokuapp.com/spec/index">OpenAPI</a>
  </p>
</div>

### Environment variables

```
ADMIN_USERNAME=admin
ADMIN_PASSWORD=admin
BRANDS_CSV_URL=https://docs.google.com/spreadsheets/d/1XPEuCgVmkOHUcYIqSmJ8lFIQPaK2F-tV738xsj5muWQ/gviz/tq?tqx=out:csv&sheet=brands
PRODUCTS_CSV_URL=https://docs.google.com/spreadsheets/d/1XPEuCgVmkOHUcYIqSmJ8lFIQPaK2F-tV738xsj5muWQ/gviz/tq?tqx=out:csv&sheet=products
GRADES_CSV_URL=https://docs.google.com/spreadsheets/d/1XPEuCgVmkOHUcYIqSmJ8lFIQPaK2F-tV738xsj5muWQ/gviz/tq?tqx=out:csv&sheet=grades
CACHE_CRON=0 0 * * * *
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

### Actuator

https://dont-buy-it-loader.herokuapp.com/actuator

### Data source

https://airtable.com/shr9NqGkVlMjSqOZM/tbldUvNp7cLAmH8Lh
