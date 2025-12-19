package fs.smartsupply.supplier.client;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.smartsupply.supplier.soap.gen.GetSupplierRequest;
import com.smartsupply.supplier.soap.gen.GetSupplierResponse;

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
