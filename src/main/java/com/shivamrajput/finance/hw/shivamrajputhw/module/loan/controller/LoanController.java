package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller;


import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanReportDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.PayDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.TimeExtensionDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.repository.LoanRepository;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.service.LoanService;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.domain.LoanRequestLog;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.repository.LoanRequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    LoanService loanService;
    @Autowired
    LoanRequestLogRepository loanRequestLogRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity test() {
        return new ResponseEntity<>(
                loanRepository.findAll(), HttpStatus.OK);
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody LoanDTO loanDTO, HttpServletRequest request) {
        LoanRequestLog loanRequestLog=new LoanRequestLog();
        loanRequestLog.setPersonalNumber(loanDTO.personalNumber);
        loanRequestLog.setIP(request.getRemoteAddr());
        loanRequestLogRepository.save(loanRequestLog);
        loanDTO.setIP(request.getRemoteAddr());
       return loanService.requestLoan(loanDTO);
    }
    @RequestMapping(value = "/findLoan", method = RequestMethod.GET)
    public ResponseEntity getLoanByPersonalNumber(@RequestParam Long personalNumber) {
        return new ResponseEntity(loanRepository.findByPersonalNumber(personalNumber),HttpStatus.OK);
    }
    @RequestMapping(value = "/amountPayableNow", method = RequestMethod.GET)
    public ResponseEntity getAmtPayableByPersonalNumberNow(@RequestParam Long personalNumber) {
        return new ResponseEntity(loanService.getAmountPaybleNow(personalNumber),HttpStatus.OK);
    }
    @RequestMapping(value = "/amountPayableAtTerm", method = RequestMethod.GET)
    public ResponseEntity getAmtPayableByPersonalNumberAterm(@RequestParam Long personalNumber) {
        return new ResponseEntity(loanService.getAmountPaybleAtTermCompletion(personalNumber),HttpStatus.OK);
    }
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ResponseEntity getReportByPersonalNumber(@RequestParam Long personalNumber) {
        return loanService.generateReport(personalNumber);
    }
    @RequestMapping(value = "/addTerm", method = RequestMethod.POST)
    public ResponseEntity addTerToExsistingLoan(@RequestBody TimeExtensionDTO timeExtensionDTO) {
        return loanService.askForExtension(timeExtensionDTO);
    }
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity payOffLoan(@RequestBody PayDTO payDTO) {
        return loanService.payOffLoan(payDTO);
    }
}