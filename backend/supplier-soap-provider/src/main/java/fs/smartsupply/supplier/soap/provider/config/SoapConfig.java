package fs.smartsupply.supplier.soap.provider.config;

import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.SimpleXsdSchema;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {
    
    private final EndpointInterceptor securityInterceptor;

    public SoapConfig(EndpointInterceptor securityInterceptor) {
        this.securityInterceptor = securityInterceptor;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet>
    messageDispatcherServlet(ApplicationContext context) {

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/soap/*");
    }

    @Bean(name = "supplier")
    public DefaultWsdl11Definition defaultWsdl11Definition(
            XsdSchema supplierSchema) {

        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("SupplierPort");
        wsdl.setLocationUri("/soap/supplier");
        wsdl.setTargetNamespace("http://smartsupply.com/supplier");
        wsdl.setSchema(supplierSchema);
        return wsdl;
    }

    @Bean
    public XsdSchema supplierSchema() {
        return new SimpleXsdSchema(
                new ClassPathResource("xsd/supplier.xsd")
        );
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.smartsupply.supplier.soap.gen");
        return marshaller;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor);
    }
}
