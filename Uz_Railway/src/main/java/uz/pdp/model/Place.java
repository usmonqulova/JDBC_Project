// o'rindiq malumotlari
package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Place {
    private UUID id;
    private UUID wagonId;
    private int name;
    private double amount;
    private boolean status;
}
