package fs.smartsupply.supplier.soap.provider.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.smartsupply.supplier.soap.gen.GetSupplierRequest;
import com.smartsupply.supplier.soap.gen.GetSupplierResponse;


@Endpoint
public class SupplierEndpoint {

    private static final String NAMESPACE_URI =
            "http://smartsupply.com/supplier";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSupplierRequest")
    @ResponsePayload
    public GetSupplierResponse getSupplier(
            @RequestPayload GetSupplierRequest request) {

        GetSupplierResponse response = new GetSupplierResponse();
        response.setId(request.getSupplierId());
        response.setName("Demo Supplier");
        response.setStatus("ACTIVE");
        return response;
    }
}
