// poyezd malumotlari
package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.Train_Type;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Train {
    private UUID id;
    private Object address;
    private int name;
    private int wagonNumber;
    private Train_Type trainType;

}
