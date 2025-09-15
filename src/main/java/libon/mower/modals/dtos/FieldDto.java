package libon.mower.modals.dtos;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto {
    @Min(0)
    private int max_x;
    @Min(0)
    private int max_y;

}
