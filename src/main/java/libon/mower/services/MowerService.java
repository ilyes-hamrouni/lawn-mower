package libon.mower.services;


import libon.mower.mappers.MowerMapper;
import libon.mower.modals.enums.Instruction;
import libon.mower.modals.enums.Orientation;
import libon.mower.modals.dtos.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MowerService {

    public OutputPayload processPaylaod(InputPayload input) {
        FieldDto field = input.getField();
        int fieldMax_X = field.getMax_x();
        int fieldMax_Y = field.getMax_y();

        List<MowerOutputDto> results = new ArrayList<>();

        for (MowerInputDto mowerInput : input.getMowers()) {
           MowerOutputDto mowerOutputDto= processMower(mowerInput,fieldMax_X,fieldMax_Y);
           results.add(mowerOutputDto);
        }

        return new OutputPayload(results);
    }


    private MowerOutputDto processMower(MowerInputDto mowerInputDto,int fieldX, int fieldY) {
        int x = mowerInputDto.getStart_position().getX();
        int y = mowerInputDto.getStart_position().getY();
        Orientation orientation = MowerMapper.toOrientation(mowerInputDto.getOrientation());
        List<Instruction> instructions = MowerMapper.toInstructions(mowerInputDto.getInstructions());

        for (Instruction instruction : instructions) {
            switch (instruction) {
                case G:
                    orientation = orientation.turnLeft();
                    break;
                case D:
                    orientation = orientation.turnRight();
                    break;
                case A:
                    int nx = x, ny = y;
                    switch (orientation) {
                        case N -> ny = y + 1;
                        case S -> ny = y - 1;
                        case E -> nx = x + 1;
                        case W -> nx = x - 1;
                    }
                    if (nx >= 0 && nx <= fieldX && ny >= 0 && ny <= fieldY) {
                        x = nx;
                        y = ny;
                    }                    break;
            }
        }
        return MowerMapper.toOutput(mowerInputDto.getId(), x, y, orientation);
    }




}
