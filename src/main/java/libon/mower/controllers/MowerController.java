package libon.mower.controllers;

import jakarta.validation.Valid;
import libon.mower.modals.dtos.InputPayload;
import libon.mower.modals.dtos.OutputPayload;
import libon.mower.services.MowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mowers")
public class MowerController {

    private final MowerService mowerService;

    public MowerController(MowerService mowerService) {
        this.mowerService = mowerService;
    }


    @PostMapping("/process")
    public ResponseEntity<OutputPayload> process(@Valid @RequestBody InputPayload payload) {
        OutputPayload out = mowerService.processPaylaod(payload);
        return ResponseEntity.ok(out);
    }
}
