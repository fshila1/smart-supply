package fs.smartsupply.supplier.soap.provider.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.SimpleXsdSchema;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

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
}
