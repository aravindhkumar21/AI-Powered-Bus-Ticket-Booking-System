package com.project.BusTicketBooking.ai.rest;

import org.springframework.web.bind.annotation.*;

import com.project.BusTicketBooking.ai.service.AiOrchestratorService;
import com.project.BusTicketBooking.dto.ai.AiRequestDTO;
import com.project.BusTicketBooking.dto.ai.AiResponseDTO;

@RestController
@RequestMapping("/api/ai")
public class AiRestController {

    private final AiOrchestratorService aiOrchestratorService;

    public AiRestController(AiOrchestratorService aiOrchestratorService) {
        this.aiOrchestratorService = aiOrchestratorService;
    }

    @PostMapping("/chat")
    public AiResponseDTO chat(@RequestBody AiRequestDTO request) {

        String response = aiOrchestratorService.process(request.getMessage());

        return new AiResponseDTO(response);
    }
}