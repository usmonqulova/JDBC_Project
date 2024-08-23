//chipta oluvchi user
package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class TicketTaker {
    private UUID id;
    private Long chatId;
    private String name;
    private String gmail;
    private String jshir;
    private String cardNumber;
}
