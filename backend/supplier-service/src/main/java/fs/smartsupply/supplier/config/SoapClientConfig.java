package fs.smartsupply.supplier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import fs.smartsupply.supplier.client.SupplierSoapClient;

@Configuration
public class SoapClientConfig {

    @Bean
    Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.company.supplier.soap");
        return marshaller;
    }

    @Bean
    SupplierSoapClient supplierSoapClient(Jaxb2Marshaller marshaller) {
        SupplierSoapClient client = new SupplierSoapClient();
        client.setDefaultUri("https://supplier.example.com/soap");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate template = new WebServiceTemplate();
        
        HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
        messageSender.setConnectionTimeout(5000); // connection timeout in ms
        messageSender.setReadTimeout(5000);       // read timeout in ms
        
        template.setMessageSender(messageSender);
        return template;
    }
}
