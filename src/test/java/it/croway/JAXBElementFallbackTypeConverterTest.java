package it.croway;

import jakarta.xml.bind.JAXBElement;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.namespace.QName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CamelSpringBootTest
@SpringBootTest(classes = MySpringBootApplication.class)
public class JAXBElementFallbackTypeConverterTest {

	@Autowired
	public ProducerTemplate template;

	@Test
	void testJaxbFallbackTypeConverter() {
		Person person = new Person();
		person.setFirstName("Apache");
		person.setLastName("Camel");

		QName qName = new QName("person.jaxb.converter.camel.apache.org", "person");
		JAXBElement<Person> personElement = new JAXBElement<>(qName, Person.class, person);

		Person result = template.requestBody("direct:start", personElement, Person.class);
		assertNotNull(result);
		assertEquals(person.getFirstName(), result.getFirstName());
		assertEquals(person.getLastName(), result.getLastName());
	}
}
