package fs.smartsupply.supplier.client;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.smartsupply.supplier.soap.GetSupplierRequest;
import com.smartsupply.supplier.soap.GetSupplierResponse;

@Component
public class SupplierSoapClient extends WebServiceGatewaySupport {

    public GetSupplierResponse getSupplier(String supplierId) {

        GetSupplierRequest request = new GetSupplierRequest();
        request.setSupplierId(supplierId);

        return (GetSupplierResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }
}
