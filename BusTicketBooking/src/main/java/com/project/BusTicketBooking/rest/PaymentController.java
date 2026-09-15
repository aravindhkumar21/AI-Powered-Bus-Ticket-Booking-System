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

import com.project.BusTicketBooking.dto.payment.PaymentRequestDTO;
import com.project.BusTicketBooking.dto.payment.PaymentResponseDTO;
import com.project.BusTicketBooking.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Create Payment
    @PostMapping("/pay")
    public ResponseEntity<PaymentResponseDTO> savePayment(
            @Valid @RequestBody PaymentRequestDTO dto) {

        PaymentResponseDTO savedPayment = paymentService.savePayment(dto);

        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }


    // Get All Payments
    @GetMapping("/allpayments")
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {

        return ResponseEntity.ok(paymentService.getAllPayments());
    }
    
    
 // Get Payments By User Id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getPaymentsByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                paymentService.getPaymentsByUserId(userId)
        );
    }


    // Get Payment By Id
    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }


    // Update Payment
    @PutMapping("/update-payment/{id}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(
            @PathVariable Long id,
            @Valid @RequestBody PaymentRequestDTO dto) {

        return ResponseEntity.ok(
                paymentService.updatePayment(id, dto));
    }
    // Delete Payment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {

        paymentService.deletePayment(id);

        return ResponseEntity.ok("Payment deleted successfully.");
    }
}
