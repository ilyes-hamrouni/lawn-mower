package libon.mower.services;


import libon.mower.modals.dtos.*;
import libon.mower.modals.enums.Orientation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MowerServiceTest {

    private final MowerService mowerService = new MowerService();

    @Test
    @DisplayName("should process single mower and apply movements")
    void processPayload_ShouldMoveMowerCorrectly() {
        FieldDto field = new FieldDto(5, 5);
        MowerInputDto mower = new MowerInputDto();
        mower.setId("M1");
        mower.setStart_position(new PositionDto(1, 2));
        mower.setOrientation("N");
        mower.setInstructions(List.of("G", "A", "G", "A", "G", "A", "G", "A", "A"));

        InputPayload payload = new InputPayload();
        payload.setField(field);
        payload.setMowers(List.of(mower));

        OutputPayload result = mowerService.processPaylaod(payload);

        assertEquals(1, result.getMowers().size());
        MowerOutputDto out = result.getMowers().get(0);
        assertEquals("M1", out.getId());
        assertEquals(1, out.getPosition().getX());
        assertEquals(3, out.getPosition().getY());
        assertEquals(Orientation.N.toString(), out.getOrientation());
    }

    @Test
    @DisplayName("should not move mower outside field boundaries")
    void processPayload_ShouldPreventGoingOutsideField() {
        FieldDto field = new FieldDto(1, 1);
        MowerInputDto mower = new MowerInputDto();
        mower.setId("M2");
        mower.setStart_position(new PositionDto(1, 1));
        mower.setOrientation("N");
        mower.setInstructions(List.of("A"));

        InputPayload payload = new InputPayload();
        payload.setField(field);
        payload.setMowers(List.of(mower));

        OutputPayload result = mowerService.processPaylaod(payload);

        MowerOutputDto out = result.getMowers().get(0);
        assertEquals(1, out.getPosition().getX());
        assertEquals(1, out.getPosition().getY());
        assertEquals(Orientation.N.toString(), out.getOrientation());
    }

    @Test
    @DisplayName("should process multiple mowers")
    void processPayload_ShouldHandleMultipleMowers() {
        FieldDto field = new FieldDto(3, 3);

        MowerInputDto mower1 = new MowerInputDto();
        mower1.setId("M1");
        mower1.setStart_position(new PositionDto(0, 0));
        mower1.setOrientation("E");
        mower1.setInstructions(List.of("A", "A"));

        MowerInputDto mower2 = new MowerInputDto();
        mower2.setId("M2");
        mower2.setStart_position(new PositionDto(3, 3));
        mower2.setOrientation("W");
        mower2.setInstructions(List.of("G", "G"));

        InputPayload payload = new InputPayload();
        payload.setField(field);
        payload.setMowers(List.of(mower1, mower2));

        OutputPayload result = mowerService.processPaylaod(payload);

        assertEquals(2, result.getMowers().size());

        MowerOutputDto out1 = result.getMowers().get(0);
        assertEquals(2, out1.getPosition().getX());
        assertEquals(0, out1.getPosition().getY());
        assertEquals(Orientation.E.toString(), out1.getOrientation());

        MowerOutputDto out2 = result.getMowers().get(1);
        assertEquals(3, out2.getPosition().getX());
        assertEquals(3, out2.getPosition().getY());
        assertEquals(Orientation.E.toString(), out2.getOrientation());
    }
}
