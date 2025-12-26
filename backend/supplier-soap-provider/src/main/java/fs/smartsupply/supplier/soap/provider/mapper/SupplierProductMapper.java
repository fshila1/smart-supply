package fs.smartsupply.supplier.soap.provider.mapper;

import org.springframework.stereotype.Component;

import com.smartsupply.supplier.soap.gen.Inventory;
import com.smartsupply.supplier.soap.gen.SupplierProduct;

import fs.smartsupply.supplier.soap.provider.entity.InventoryEntity;
import fs.smartsupply.supplier.soap.provider.entity.SupplierProductEntity;

@Component
public class SupplierProductMapper {

    private final XmlDateTimeMapper dateTimeMapper;

    public SupplierProductMapper(XmlDateTimeMapper dateTimeMapper) {
        this.dateTimeMapper = dateTimeMapper;
    }

    public SupplierProduct toSoap(
            SupplierProductEntity entity) {

        SupplierProduct dto = new SupplierProduct();
        dto.setSupplierProductRef(entity.getSupplierProductRef());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public Inventory toSoap(
            InventoryEntity entity) {

        Inventory dto = new Inventory();
        dto.setSupplierProductRef(entity.getSupplierProductRef());
        dto.setStock(entity.getStock());
        dto.setLowStockThreshold(entity.getLowStockThreshold());
        dto.setLastUpdatedAt(dateTimeMapper.toXmlGregorianCalendar(entity.getLastUpdatedAt()));
        return dto;
    }
}
