package fs.smartsupply.supplier.service;

import org.springframework.stereotype.Service;

import com.smartsupply.supplier.soap.GetSupplierResponse;

import fs.smartsupply.supplier.DTO.SupplierResponseDTO;
import fs.smartsupply.supplier.client.SupplierSoapClient;
import fs.smartsupply.supplier.mapper.SupplierMapper;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class SupplierService {

    private final SupplierSoapClient soapClient;

    public SupplierService(SupplierSoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @Retry(name = "supplierService")
    public SupplierResponseDTO getSupplier(String supplierId) {

        GetSupplierResponse soapResponse =
                soapClient.getSupplier(supplierId);

        return SupplierMapper.toDto(soapResponse);
    }
}
