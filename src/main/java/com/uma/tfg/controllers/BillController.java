package com.uma.tfg.controllers;

import com.uma.tfg.entities.Bill;
import com.uma.tfg.services.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) { this.billService = billService; }

    @PostMapping("/bill")
    public void createBill(@RequestBody Bill bill) throws Exception {
        billService.createMail(bill);
    }

    @GetMapping("/bills")
    public List<Bill> all() {
        return billService.getAll();
    }

}
