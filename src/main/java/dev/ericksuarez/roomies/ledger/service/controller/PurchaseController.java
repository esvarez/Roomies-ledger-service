package dev.ericksuarez.roomies.ledger.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @GetMapping("/hi")
    String hi() {
        return "Hola mundo";
    }
}
