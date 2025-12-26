package fs.smartsupply.supplier.soap.provider.mapper;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Component;

@Component
public class XmlDateTimeMapper {

    public XMLGregorianCalendar toXmlGregorianCalendar(Instant instant) {
        if (instant == null) {
            return null;
        }

        GregorianCalendar gc = GregorianCalendar.from(
                instant.atZone(ZoneOffset.UTC)
        );

        try {
            return DatatypeFactory.newInstance()
                                  .newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(
                "Failed to convert Instant to XMLGregorianCalendar", e
            );
        }
    }
}
