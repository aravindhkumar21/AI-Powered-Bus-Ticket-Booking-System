package com.project.BusTicketBooking.ai.rest;

import org.springframework.web.bind.annotation.*;

import com.project.BusTicketBooking.ai.service.AiOrchestratorService;
import com.project.BusTicketBooking.dto.ai.AiRequestDTO;
import com.project.BusTicketBooking.dto.ai.AiResponseDTO;

@RestController
@RequestMapping("/api/ai")
public class AiRestController {

    private final AiOrchestratorService aiOrchestratorService;

    public AiRestController(
            AiOrchestratorService aiOrchestratorService) {

        this.aiOrchestratorService =
                aiOrchestratorService;
    }

//    @PostMapping("/chat")
//    public AiResponseDTO chat(
//            @RequestBody AiRequestDTO request) {
//
//        String response =
//                aiOrchestratorService.process(
//                        request.getSessionId(),
//                        request.getUserId(),
//                        request.getMessage()
//                );
//
//        return new AiResponseDTO(response);
//    }
    @PostMapping("/chat")
    public AiResponseDTO chat(@RequestBody AiRequestDTO request) {

        try {
        	String response = aiOrchestratorService.process(
        	        request.getSessionId(),
        	        request.getUserId(),
        	        request.getMessage()
        	);
            return new AiResponseDTO(response);

        } catch (Exception e) {

            e.printStackTrace();   // temporary debugging

            throw e;
        }
    }
}