package com.project.BusTicketBooking.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BusTicketBooking.dto.route.RouteRequestDTO;
import com.project.BusTicketBooking.dto.route.RouteResponseDTO;
import com.project.BusTicketBooking.model.Route;
import com.project.BusTicketBooking.service.RouteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    // Save Route
    @PostMapping("/register-route")
    public ResponseEntity<RouteResponseDTO> saveRoute(@Valid @RequestBody RouteRequestDTO dto) {
    	
    	RouteResponseDTO savedRoute = routeService.saveRoute(dto);

        return new ResponseEntity<>(savedRoute, HttpStatus.CREATED);
    }

    // Get All Routes
    @GetMapping("/allroutes")
    public ResponseEntity<List<RouteResponseDTO>> getAllRoutes() {

        List<RouteResponseDTO> routes = routeService.getAllRoutes();

        return ResponseEntity.ok(routes);
    }

    // Get Route By Id
    @GetMapping("/route/{id}")
    public ResponseEntity<RouteResponseDTO> getRouteById(@PathVariable Long id) {

        RouteResponseDTO route = routeService.getRouteById(id);

        return ResponseEntity.ok(route);
    }

    // Update Route
    @PutMapping("/update-route/{id}")
    public ResponseEntity<RouteResponseDTO> updateRoute(@PathVariable Long id,
                                             @Valid @RequestBody RouteRequestDTO dto) {

        RouteResponseDTO updatedRoute = routeService.updateRoute(id, dto);

        return ResponseEntity.ok(updatedRoute);
    }

    // Delete Route
    @DeleteMapping("/delete-route/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id) {

        routeService.deleteRoute(id);

        return ResponseEntity.ok("Route deleted successfully.");
    }

}
