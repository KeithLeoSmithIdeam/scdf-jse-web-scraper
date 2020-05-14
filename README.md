# JSE-Web Scraper (Spring Cloud task App)

Spring Cloud Task Application Starters are Spring Boot applications that may be any process including Spring Batch jobs that do not run forever, and they end/stop at some point. Spring Cloud Task Applications can be used with Spring Cloud Data Flow to create, deploy, and orchestrate short-lived data microservices.

## Notes
1) Page to scrape https://www.jse.co.za/current-companies/companies-and-financial-instruments
2) LOOK AT STRUCTURE OF EXISTING SPRING CLOUD TASK PROJECT ON GITHUB - [here](https://cloud.spring.io/spring-cloud-task-app-starters/) and [Github](https://github.com/spring-cloud/spring-cloud-task-app-starters)
3) Guide to create custom apps - [here](https://docs.spring.io/spring-cloud-task-app-starters/docs/Elston.SR1/reference/htmlsingle/)
4) Guide on deployment - [here](https://docs.spring.io/spring-cloud-dataflow-server-kubernetes/docs/current-SNAPSHOT/reference/htmlsingle/#_deploying_tasks)


## Example Arguments
```bash
--scraper.db.url=jdbc:mysql://192.168.2.13:3306/dataplatform
--scraper.db.username=root
--scraper.db.password=Password1
--scraper.db.driverClassName=com.mysql.jdbc.Driver
--scraper.truncatebefore=true
```
## Example Parameters
```bash
deployer.jse-web-scraper.kubernetes.imagePullSecret=regcred
```

## Run Dev profile
add the following to VM options
```
-Dspring.profiles.active=dev
```
