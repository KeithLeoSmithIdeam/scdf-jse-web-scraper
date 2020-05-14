# JSE-Web Scraper (Spring Cloud task App)

Spring Cloud Task Application Starters are Spring Boot applications that may be any process including Spring Batch jobs that do not run forever, and they end/stop at some point. Spring Cloud Task Applications can be used with Spring Cloud Data Flow to create, deploy, and orchestrate short-lived data microservices.

## Notes
1) Page to scrape https://www.jse.co.za/current-companies/companies-and-financial-instruments
2) LOOK AT STRUCTURE OF EXISTING SPRING CLOUD TASK PROJECT ON GITHUB - [here](https://cloud.spring.io/spring-cloud-task-app-starters/) and [Github](https://github.com/spring-cloud/spring-cloud-task-app-starters)
3) Guide to create custom apps - [here](https://docs.spring.io/spring-cloud-task-app-starters/docs/Elston.SR1/reference/htmlsingle/)
4) Guide on deployment - [here](https://docs.spring.io/spring-cloud-dataflow-server-kubernetes/docs/current-SNAPSHOT/reference/htmlsingle/#_deploying_tasks)

## Links to use
1) [Web scraper using JSoup and Spring Boot](https://www.thetechnojournals.com/2019/11/web-scraper-using-jsoup-and-spring-boot.html)
2) [Udemy Course](https://www.udemy.com/course/learn-web-scraping-with-java-in-just-1-hour/)
3) [Jsoup artcilce Baeldung](https://www.baeldung.com/java-with-jsoup)
4) [Spring Cloud Dataflow Docs](https://dataflow.spring.io/docs/batch-developer-guides/getting-started/)

## Custom Apps
1) using core - [here](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/index.html#_getting_started)

## Build the docker image with jib
```bash
mvn compile jib:dockerBuild
```

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
