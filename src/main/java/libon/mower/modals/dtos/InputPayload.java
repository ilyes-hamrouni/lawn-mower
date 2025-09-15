package libon.mower.modals.dtos;


import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputPayload {

    private FieldDto field;

    @NotNull
    private List<MowerInputDto> mowers;

}
