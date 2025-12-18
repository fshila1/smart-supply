package fs.smartsupply.supplier.mapper;

import com.smartsupply.supplier.soap.gen.GetSupplierResponse;

import fs.smartsupply.supplier.DTO.SupplierResponseDTO;

public class SupplierMapper {

    public static SupplierResponseDTO toDto(GetSupplierResponse response) {

        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setId(response.getId());
        dto.setName(response.getName());
        dto.setStatus(response.getStatus());
        return dto;
    }
}
