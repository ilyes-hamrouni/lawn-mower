package libon.mower.modals.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MowerInputDto {
    @NotBlank
    private String id;

    @NotNull
    private PositionDto start_position;

    @NotBlank
    private String orientation;

    @NotNull
    private List<String> instructions;
}
