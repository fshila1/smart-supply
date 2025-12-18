package fs.smartsupply.supplier.client;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.smartsupply.supplier.soap.gen.GetSupplierRequest;
import com.smartsupply.supplier.soap.gen.GetSupplierResponse;
import com.smartsupply.supplier.soap.gen.ObjectFactory;

import jakarta.xml.bind.JAXBElement;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Component
public class SupplierSoapClient extends WebServiceGatewaySupport {

    public SupplierSoapClient(){}

    public SupplierSoapClient(WebServiceTemplate webServiceTemplate) {
        setWebServiceTemplate(webServiceTemplate);
    }

    public GetSupplierResponse getSupplier(String supplierId) {
        GetSupplierRequest request = new GetSupplierRequest();
        request.setSupplierId(supplierId);

        return (GetSupplierResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }
}

// @Component
// public class SupplierSoapClient extends WebServiceGatewaySupport {

//     public GetSupplierResponse getSupplier(String supplierId) {

//         GetSupplierRequest request = new GetSupplierRequest();
//         request.setSupplierId(supplierId);

//         return (GetSupplierResponse) getWebServiceTemplate()
//                 .marshalSendAndReceive(request);
//     }
// }

// @Component
// public class SupplierSoapClient {

//     private final WebServiceTemplate webServiceTemplate;

//     public SupplierSoapClient(WebServiceTemplate webServiceTemplate) {
//         this.webServiceTemplate = webServiceTemplate;
//     }

//     public GetSupplierResponse getSupplier(String supplierId) {

//         GetSupplierRequest request = new GetSupplierRequest();
//         request.setSupplierId(supplierId);

//         ObjectFactory factory = new ObjectFactory();
//         JAXBElement<GetSupplierRequest> element =
//                 factory.createGetSupplierRequest(request);

//         return (GetSupplierResponse)
//                 webServiceTemplate.marshalSendAndReceive(element);
//     }
// }
