package fs.smartsupply.supplier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import fs.smartsupply.supplier.client.SupplierSoapClient;

@Configuration
public class SoapClientConfig {

    // --- JAXB marshaller for SOAP objects ---
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // package where generated JAXB classes exist
        marshaller.setContextPath("com.smartsupply.supplier.soap.gen");
        return marshaller;
    }

    // --- WS-Security interceptor ---
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();

        // Send UsernameToken in SOAP header
        interceptor.setSecurementActions("UsernameToken");
        interceptor.setSecurementUsername("supplier-user"); // must match provider
        interceptor.setSecurementPassword("secret");         // must match provider
        interceptor.setSecurementPasswordType("PasswordText"); // or PasswordDigest

        return interceptor;
    }

    // --- WebServiceTemplate with marshaller + WS-Security ---
    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller,
                                                 Wss4jSecurityInterceptor securityInterceptor) {
        WebServiceTemplate template = new WebServiceTemplate();

        template.setMarshaller(marshaller);
        template.setUnmarshaller(marshaller);

        // URL of provider SOAP service
        template.setDefaultUri("http://localhost:8080/soap/supplier");

        // attach WS-Security interceptor
        template.setInterceptors(new ClientInterceptor[]{securityInterceptor});

        return template;
    }

    // --- Supplier SOAP client using configured WebServiceTemplate ---
    @Bean
    public SupplierSoapClient supplierSoapClient(WebServiceTemplate webServiceTemplate) {
        SupplierSoapClient client = new SupplierSoapClient();
        client.setWebServiceTemplate(webServiceTemplate);
        return client;
    }
}










// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.oxm.jaxb.Jaxb2Marshaller;
// import org.springframework.ws.client.core.WebServiceTemplate;
// import org.springframework.ws.client.support.interceptor.ClientInterceptor;
// import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
// import org.springframework.ws.transport.http.HttpComponentsMessageSender;

// import fs.smartsupply.supplier.client.SupplierSoapClient;

// @Configuration
// public class SoapClientConfig {

//     @Bean
//     Jaxb2Marshaller marshaller() {
//         Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//         marshaller.setContextPath("com.smartsupply.supplier.soap.gen");
//         return marshaller;
//     }

//     @Bean
//     SupplierSoapClient supplierSoapClient(Jaxb2Marshaller marshaller) {
//         SupplierSoapClient client = new SupplierSoapClient();
//         // client.setDefaultUri("https://supplier.smartsupply.com/soap");
//         client.setDefaultUri("http://localhost:8080/soap");
//         client.setMarshaller(marshaller);
//         client.setUnmarshaller(marshaller);
//         return client;
//     }

//     @Bean
//     public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller, 
//         Wss4jSecurityInterceptor securityInterceptor) {

//         WebServiceTemplate template = new WebServiceTemplate();
//         template.setMarshaller(marshaller);
//         template.setUnmarshaller(marshaller);

//         template.setDefaultUri("http://localhost:8080/soap"); // provider URL

//         template.setInterceptors(new ClientInterceptor[]{securityInterceptor});
//         return template;
//     }

//     // @Bean
//     // public WebServiceTemplate webServiceTemplate() {
//     //     WebServiceTemplate template = new WebServiceTemplate();
//     //     template.setDefaultUri("http://localhost:8080/soap");
//     //     template.setInterceptors(new ClientInterceptor[]{
//     //         securityInterceptor()
//     //     });
//     //     return template;
//     // }


//     // @Bean
//     // public WebServiceTemplate webServiceTemplate() {
//     //     WebServiceTemplate template = new WebServiceTemplate();
//     //     template.setDefaultUri("http://localhost:8080/soap");

//     //     template.setInterceptors(new ClientInterceptor[]{
//     //         securityInterceptor()
//     //     });

//     //     return template;
//     // }

//     @Bean
//     public Wss4jSecurityInterceptor securityInterceptor() {
//         Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();

//         interceptor.setSecurementActions("UsernameToken");
//         interceptor.setSecurementUsername("supplier-user");
//         interceptor.setSecurementPassword("secret");
//         interceptor.setSecurementPasswordType("PasswordText");

//         return interceptor;
//     }
// }
