package libon.mower.modals.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MowerOutputDto {
    private String id;
    private PositionDto position;
    private String orientation;

}

