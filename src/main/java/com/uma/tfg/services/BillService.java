package com.uma.tfg.services;

import com.uma.tfg.entities.Bill;
import com.uma.tfg.entities.Mail;
import com.uma.tfg.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public void createMail(Bill bill) {
        billRepository.save(bill);
    }

    public List<Bill> getAll() {
        return (List<Bill>) billRepository.findAll();
    }
}
