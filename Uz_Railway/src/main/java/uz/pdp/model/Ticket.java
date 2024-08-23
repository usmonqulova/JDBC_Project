// chipta malumotlari
package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.Ticket_Status_Type;

import java.util.UUID;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Ticket {
    private UUID id;
    private UUID ticketTakerId;
    private UUID placeId;
    private Object address;
    private Ticket_Status_Type status;

}
