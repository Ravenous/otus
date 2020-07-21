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
            сервис заявки 
        */
            rest("/claim")
            .post()
                .to("direct:claim-create")
            .get()
                .to("direct:claim-read")
            .get("/{id}")
                .to("direct:claim-read")                
            .put("/{id}")
                .to("direct:claim-update") 
            .post("/{id}")
                .to("direct:claim-update") 
            .post("/{id}/close")
                .to("direct:claim-close")                     
            .delete("/{id}")
                .to("direct:claim-delete")    
        ;

        /* 
            получение информации о заявках 
        */
        from("direct:claim-read")
            .log("################# READ ${body} id=${header.id}; msisdn=${header.msisdn}; inwork=${header.filter}")
            .id("claim read")
            .choice()
                .when(header("filter").isEqualToIgnoreCase("inwork"))
                    .log("====== READ by inwork=${header.filter}")
                    .to("sql:classpath:sql/claim-read-in-work-by-msisdn.sql")            
                .when(header("msisdn").isNotNull())
                    .log("====== READ by msisdn=${header.msisdn}")
                    .to("sql:classpath:sql/claim-read-by-msisdn.sql")
                .when(header("id").isNotNull())
                    .log("====== READ by id=${header.id}")
                    .to("sql:classpath:sql/claim-read-by-id.sql")
                .otherwise()
                    .log("====== READ all")
                    .to("sql:classpath:sql/claim-read.sql")
            .end()
            .marshal().json()
        ;

        /* 
            добавление заявки 
        */
        from("direct:claim-create")
            .id("claim create")
            .log("################# CREATE ${body} msisdn=${header.msisdn}; action=${header.action}")
            .choice()
                .when(header("action").isEqualToIgnoreCase("take"))
                    .log("============ action ${body}")
                    .to("sql:classpath:sql/claim-take-to-work.sql")
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_OK))
                .otherwise()                
                    .unmarshal().json()
                    .setHeader("topic", simple("${body[topic]}"))
                    .setHeader("description", simple("${body[description]}"))
                    .setHeader("status_id", simple("${body[statusId]}"))
                    .setHeader("critical_id", simple("${body[criticalId]}"))
                    .to("sql:classpath:sql/claim-create.sql")
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_CREATED))
                    .log("============ add ${body}")
            .end()
            .marshal().json()
        ;

        /* 
            Изменение данных заявки 
        */
        from("direct:claim-update")
            .id("claim update")
            .log("################# UPDATE ${body}")
            .unmarshal().json()
            .setHeader("id", simple("${body[id]}"))
            .setHeader("statusId", simple("${body[statusId]}"))
            .setHeader("msisdn", simple("${body[msisdn]}"))
            .to("sql:classpath:sql/claim-update.sql")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_OK))
            .marshal().json()
        ;

        /* 
            Взятие заявки в работу 
        */
        from("direct:claim-take2work")
            .id("claim take2work")
            .log("################# take2work ${body}")
            .unmarshal().json()
            //.setHeader("id", simple("${header.id}"))
            .setHeader("msisdn", simple("${body[msisdn]}"))
            .to("sql:classpath:sql/claim-update.sql")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_OK))
            .marshal().json()
        ;

        /* 
            Закрытие заявки заявки 
        */
        from("direct:claim-close")
            .id("claim close")
            .log("################# CLOSE ${body} id=${header.id}")
            //.unmarshal().json()
            //.setHeader("id", simple("${header.id}"))
            .to("sql:classpath:sql/claim-close.sql")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_OK))
            .marshal().json()
        ;

        /* 
            удаление заявки 
        */
        from("direct:claim-delete")
            .id("claim delete")
            .log("################# DELETE ${body}")
            .to("sql:classpath:sql/claim-delete.sql")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, simple(HTTP_NO_CONTENT))
            .marshal().json()
        ;

    }
}