package com.shivamrajput.finance.hw.shivamrajputhw.module.loan.service;


import com.shivamrajput.finance.hw.shivamrajputhw.module.common.ErrorResponse;
import com.shivamrajput.finance.hw.shivamrajputhw.module.common.GenericResponse;
import com.shivamrajput.finance.hw.shivamrajputhw.module.common.SuccessResponse;
import com.shivamrajput.finance.hw.shivamrajputhw.module.common.SuccessWithPayloadResponse;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.LoanReportDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.PayDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.controller.dto.TimeExtensionDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain.Loan;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.domain.TimeExtension;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.repository.LoanRepository;
import com.shivamrajput.finance.hw.shivamrajputhw.module.loan.repository.TimeExtensionRepository;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core.PolicyDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.service.LendingManagerService;
import com.shivamrajput.finance.hw.shivamrajputhw.module.management.service.RiskAnalysisService;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.controller.dto.UserDTO;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.domain.User;
import com.shivamrajput.finance.hw.shivamrajputhw.module.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Loan service: disposes various loan related functions
 */
@Service
public class LoanService {
    private static final Logger log = LoggerFactory.getLogger(LoanHelperService.class);


    interface loanHandlerCallback {
        void success(GenericResponse genericResponse);

        void failed(GenericResponse genericResponse);
    }

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    UserService userService;
    @Autowired
    LendingManagerService lendingManagerService;
    @Autowired
    LoanHelperService loanHelperService;
    @Autowired
    TimeExtensionRepository timeExtensionRepository;
    @Autowired
    RiskAnalysisService riskAnalysisService;

    @Autowired
    EntityManager entityManager;

    public boolean isLoanExistWithLoanId(Long id) {
        Loan l = loanRepository.findOneById(id);
        if (l == null) {
            return false;
        } else {
            return true;
        }
    }

    public ResponseEntity requestLoan(final LoanDTO loanDTO) {
        if (loanDTO.getPersonalNumber() == null
                || loanDTO.getTerm() == null
                || loanDTO.getTerm() <= 0
                || loanDTO.getAmount() == null
                || loanDTO.getAmount() == (BigDecimal.ZERO)) {
            return new ResponseEntity<>(new ErrorResponse("Not valid parameters"), HttpStatus.BAD_REQUEST);
        }

        final ResponseEntity[] responseEntity = new ResponseEntity[1];
        if (userService.isUserExistWithPersonalNumber(loanDTO.personalNumber)) {
            checkAndIssueLoan(loanDTO, new loanHandlerCallback() {
                @Override
                public void success(GenericResponse genericResponse) {
                    responseEntity[0] = new ResponseEntity(genericResponse, HttpStatus.OK);
                }

                @Override
                public void failed(GenericResponse genericResponse) {
                    responseEntity[0] = new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);

                }
            });
        } else {
            registerUserAndIssueLoan(loanDTO, new loanHandlerCallback() {
                @Override
                public void success(GenericResponse genericResponse) {
                    responseEntity[0] = new ResponseEntity(genericResponse, HttpStatus.OK);
                }

                @Override
                public void failed(GenericResponse genericResponse) {
                    responseEntity[0] = new ResponseEntity(genericResponse, HttpStatus.BAD_REQUEST);
                }
            });
        }
        return responseEntity[0];
    }

    private void registerUserAndIssueLoan(LoanDTO loanDTO, loanHandlerCallback callback) {
        userService.createUser(new UserDTO(loanDTO.firstname, loanDTO.lastname, loanDTO.personalNumber), new UserService.userServiceCallback() {
            @Override
            public void userAdded(User user) {
                issueLoan(loanDTO, callback, "New user, Details saved in db");
            }

            @Override
            public void userAlredyExits(String message) {
                callback.failed(new ErrorResponse(GenericResponse.ERROR, "Something went wrong while handling user data"));
            }

            @Override
            public void badRequestParms(String message) {
                callback.failed(new ErrorResponse(GenericResponse.ERROR, "Something went wrong while handling user data"));

            }
        });

    }

    private void checkAndIssueLoan(LoanDTO loanDTO, loanHandlerCallback callback) {
        issueLoan(loanDTO, callback, "User found in db,[Returning customer]");
    }

    @Transactional
    public void issueLoan(LoanDTO loanDTO, loanHandlerCallback callback, String extraMessage) {

        riskAnalysisService.performAnalysis(loanDTO, new RiskAnalysisService.riskAnalysisCallback() {
            @Override
            public void passed(PolicyDTO policyDTO) {
                log.info(policyDTO.toString());
                loanDTO.setAmount(policyDTO.getNewAmount());
                Loan loan = new Loan();
                loan.setPersonalNumber(loanDTO.personalNumber);
                loan.setTerm(loanDTO.getTerm());
                loan.setAmount(loanDTO.getAmount());
                loan.setFinalPaidAmount(null);
                loan.setPaid(false);
                loan = loanRepository.save(loan);
                callback.success(new SuccessWithPayloadResponse<Loan>(GenericResponse.SUCCESS, "Loan Issued,"+policyDTO.getMessage()+" Extra User Info: " + extraMessage, loan));
            }

            @Override
            public void failed(PolicyDTO policyDTO) {
                callback.failed(new ErrorResponse(GenericResponse.ERROR, "Failed to issue loan,"+policyDTO.getMessage()+", Extra user info: " + extraMessage));
            }
        });
/*        if (lendingManagerService.isUserEligibleForLoan(loanDTO)) {
            Loan loan = new Loan();
            loan.setPersonalNumber(loanDTO.personalNumber);
            loan.setTerm(loanDTO.getTerm());
            loan.setAmount(loanDTO.getAmount());
            loan.setFinalPaidAmount(null);
            loan.setPaid(false);
            loan = loanRepository.save(loan);
            callback.success(new SuccessWithPayloadResponse<Loan>(GenericResponse.SUCCESS, "Loan Issued, Extra User Info: " + extraMessage, loan));
        } else {
            callback.failed(new ErrorResponse(GenericResponse.ERROR, "Failed to issue loan, Extra user info: " + extraMessage));
        }*/
    }

    public LoanReportDTO getAmountPaybleNow(Long personalNumber) {
        LoanReportDTO loanReportDTO = null;
        final BigDecimal[] amt = {BigDecimal.ZERO};
        List<Loan> loans = loanRepository.findByPersonalNumber(personalNumber);
        loanReportDTO = new LoanReportDTO();
        loanReportDTO.setTOTAL_AMOUNT_PAYABLE(loanHelperService.calTotalAmountForAllUnpaidLoansNow(loans));
        return loanReportDTO;
    }
    public GenericResponse getAmountPaybleAtTermCompletion(Long personalNumber) {
        LoanReportDTO loanReportDTO = null;
        final BigDecimal[] amt = {BigDecimal.ZERO};
        List<Loan> loans = loanRepository.findByPersonalNumber(personalNumber);
        loanReportDTO = new LoanReportDTO();
        loanReportDTO.setTOTAL_AMOUNT_PAYABLE(loanHelperService.calTotalAmountForAllUnpaidLoansAtTerm(loans));
        return new GenericResponse("Pay due on term completion [including extensions if any]","Amount-> "+loanHelperService.calTotalAmountForAllUnpaidLoansAtTerm(loans));
    }


    public ResponseEntity generateReport(Long personalNumber) {
        if (!userService.isUserExistWithPersonalNumber(personalNumber)) {
            return new ResponseEntity(new GenericResponse(GenericResponse.ERROR, "User not found"), HttpStatus.BAD_REQUEST);
        }
        List<Loan> loans = loanRepository.findByPersonalNumber(personalNumber);
        if (loans == null && loans.size() == 0) {
            return new ResponseEntity(new GenericResponse(GenericResponse.FAILED, "User did not took any loans yet"), HttpStatus.NOT_FOUND);
        } else {
            LoanReportDTO loanReportDTO = loanHelperService.generateUserLoanReport(loans);
            loanReportDTO.setUSER_DETAILS(userService.fetchUserByPersonalNumber(personalNumber));
            return new ResponseEntity(loanReportDTO, HttpStatus.OK);
        }
    }

    @Transactional
    @Modifying
    public ResponseEntity askForExtension(TimeExtensionDTO timeExtensionDTO) {
        if (!userService.isUserExistWithPersonalNumber(timeExtensionDTO.getPersonalNumber())) {
            return new ResponseEntity(new GenericResponse(GenericResponse.ERROR, "User not found"), HttpStatus.BAD_REQUEST);
        }
        if (!isLoanExistWithLoanId(timeExtensionDTO.getLoanId())) {
            return new ResponseEntity(new GenericResponse(GenericResponse.ERROR, "Failed to find loan with details: " + timeExtensionDTO.toString()), HttpStatus.BAD_REQUEST);
        }
        Loan loan = loanRepository.findOneById(timeExtensionDTO.getLoanId());
        TimeExtension timeExtension = new TimeExtension();
        timeExtension.setExtension(timeExtensionDTO.getExtraTerm());
        timeExtension = timeExtensionRepository.save(timeExtension);

        loan.addTimeExtension(timeExtension);
        loanRepository.save(loan);
        return new ResponseEntity(loanRepository.findOneById(timeExtensionDTO.getLoanId()),HttpStatus.OK);
    }

    public ResponseEntity payOffLoan(PayDTO payDTO) {
        if(!userService.isUserExistWithPersonalNumber(payDTO.getPersonalNumber())){
            return new ResponseEntity(new GenericResponse(GenericResponse.ERROR, "User not found"), HttpStatus.BAD_REQUEST);
        }
        if(loanRepository.findAll().size()>0){
            final Boolean[] flag = new Boolean[1];
            flag[0] =false;
            loanRepository.findByPersonalNumber(payDTO.getPersonalNumber()).forEach(l->{
                if(!l.isPaid()){ flag[0] =true;}
            });

            if(flag[0]){
             GenericResponse response=  payLoansImpl(payDTO);
                return new ResponseEntity(response, HttpStatus.OK);
            }else {
                return new ResponseEntity(new GenericResponse(GenericResponse.ERROR, "User dont have any un-paid loan"), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity(new GenericResponse(GenericResponse.ERROR, "User dont have any loan"), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @Modifying
    public GenericResponse payLoansImpl(PayDTO payDTO) {
        if(loanHelperService.isAmountEqualorGreater(loanRepository.findByPersonalNumber(payDTO.getPersonalNumber()),payDTO)>=0){
            loanRepository.findByPersonalNumber(payDTO.getPersonalNumber()).forEach(l->{
                l.setPaid(true);
                loanRepository.save(l);
            });
            return new SuccessResponse("All loans paid");
        }else{
            return new ErrorResponse("Insufficient amount supplied, Loans not paid");
        }

    }
}
