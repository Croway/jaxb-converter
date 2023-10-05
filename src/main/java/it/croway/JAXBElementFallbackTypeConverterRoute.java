package it.croway;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JAXBElementFallbackTypeConverterRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("direct:start").process(exchange -> {
			Message in = exchange.getIn();
			Person body = in.getMandatoryBody(Person.class);
			exchange.getMessage().setBody(body);
		});
	}
}
