package libon.mower.mappers;

import libon.mower.modals.enums.Instruction;
import libon.mower.modals.enums.Orientation;
import libon.mower.modals.dtos.MowerOutputDto;
import libon.mower.modals.dtos.PositionDto;

import java.util.List;
import java.util.stream.Collectors;

public final class MowerMapper {

    private MowerMapper() {}

    public static Orientation toOrientation(String s) {
        return Orientation.from(s);
    }

    public static List<Instruction> toInstructions(List<String> raw) {
        return raw.stream().map(Instruction::from).collect(Collectors.toList());
    }

    public static MowerOutputDto toOutput(String id, int x, int y, Orientation orientation) {
        PositionDto p = new PositionDto();
        p.setX(x);
        p.setY(y);
        return new MowerOutputDto(id, p, orientation.name());
    }
}
