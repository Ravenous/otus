package com.ravenous.camel;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

/**
 * Camel route definitions.
 */
@ApplicationScoped
 public class CamelRoute extends RouteBuilder {

    private static final String HTTP_OK = "200";
    private static final String HTTP_CREATED = "201";
    //private static final String HTTP_ACCEPTED = "202";
    private static final String HTTP_NO_CONTENT = "204";
     
    public CamelRoute() {

    }

    @Override
    public void configure() throws Exception {

        /* 
            сервис сотрудники 
        */
            rest("/employee")
            .post()
                .to("direct:employee-create")
            .get()
                .to("direct:employee-read")
            .get("/{id}")
                .to("direct:employee-read")
            .put("/{id}")
                .to("direct:employee-update") 
            .post("/{id}")
                .to("direct:employee-update")                 
            .delete("/{id}")
                .to("direct:employee-delete")    
        ;

        /* 
            получение информации о сотрудниках 
        */
        from("direct:employee-read")
            .log("################# READ ${body}")
            .id("employee read")
            .choice()
                .when(header("id").isNull())
                    .to("sql:classpath:sql/employee-read.sql")
                .otherwise()
                    .to("sql:classpath:sql/employee-read-by-id.sql")
            .end()
            .marshal().json()
        ;

        /* 
            добавление сотрудника 
        */
        from("direct:employee-create")
            .id("employee create")
            .log("################# CREATE ${body}")
            .unmarshal().json()
            .setHeader("lastname", simple("${body[lastName]}"))
            .setHeader("firstname", simple("${body[firstName]}"))
            .setHeader("middlename", simple("${body[middleName]}"))
            .setHeader("phone", simple("${body[phone]}"))
            .to("sql:classpath:sql/employee-create.sql")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_CREATED))
            .marshal().json()
        ;

        /* 
            Изменение данных сотрудника 
        */
        from("direct:employee-update")
            .id("employee update")
            .log("################# UPDATE ${body}")
            .unmarshal().json()
            .setHeader("lastname", simple("${body[lastName]}"))
            .setHeader("firstname", simple("${body[firstName]}"))
            .setHeader("middlename", simple("${body[middleName]}"))
            .setHeader("phone", simple("${body[phone]}"))
            .to("sql:classpath:sql/employee-update.sql")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_OK))
            .marshal().json()
        ;

        /* 
            удаление сотрудника 
        */
        from("direct:employee-delete")
            .id("employee delete")
            .log("################# DELETE ${body}")
            .to("sql:classpath:sql/employee-delete.sql")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_NO_CONTENT))
            .marshal().json()
        ;
    }
}