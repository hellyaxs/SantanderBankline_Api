# (API REST) Simulação de Transações Bancarias

### Recursos Utilizados neste projeto
Abaixo vou deixar as ferramentas e conhecimento adquiridos neste projeto:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#boot-features-security-and-spring-security)
* [Json web Token](https://jwt.io/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#using-boot-devtools)
* [Lombok](https://projectlombok.org/)
* [JPQL](https://www.devmedia.com.br/jpql-java-persistence-query-language/28180)
* [H2 Database](https://www.h2database.com/html/main.html)
* [docker](https://www.docker.com/)
* [docker-compose](https://docs.docker.com/compose/)
* [Postgres](https://www.postgresql.org/)

![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![Webpack](https://img.shields.io/badge/webpack-%238DD6F9.svg?style=for-the-badge&logo=webpack&logoColor=black)
![RxJS](https://img.shields.io/badge/rxjs-%23B7178C.svg?style=for-the-badge&logo=reactivex&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

**SANTANDER DEV WEEK**

## requistos para rodar o projeto:

- docker
- docker-compose
- java 11

## Como executar a aplicação

### Clone o repositório
```bash
  git clone https://github.com/hellyaxs/SantanderBankline_Api
```

```bash
  docker-compose up -f ./docker --build  -d
```

Acesse Front-end em: http://localhost:8001/

Acesse API em: http://localhost:8000/


## Documentação da API
  
    Acesse a documentação da API em: http://localhost:8000/swagger-ui.html

Acesse o projeto front-end em: [front-end](https://github.com/hellyaxs/bankline-app)


## Arquitetura do projeto
![image](arquiterure.png)

