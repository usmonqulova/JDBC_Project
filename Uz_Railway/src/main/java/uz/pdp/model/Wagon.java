package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.Wagon_Type;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Wagon {
    private UUID id;
    private int placeNumber;
    private int placeEmptyNumber;
    private Wagon_Type wagonType;
}
