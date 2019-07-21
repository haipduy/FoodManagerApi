package fptu.sumer.foodstore_api.controller;


import fptu.sumer.foodstore_api.entity.BankAccountEntity;
import fptu.sumer.foodstore_api.reponsitory.BankAccountRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Bank Account Management System")
public class BankAccountController {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @GetMapping("bankaccounts/{id}")
    public ResponseEntity getBankAccountByUserId(@PathVariable String id) {

        BankAccountEntity bankAccountEntity = bankAccountRepository.findByUserIdAndIsActive(id, 1);
        if (bankAccountEntity != null) {
            return ResponseEntity.ok(bankAccountEntity);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @ApiOperation(value = "Create new account")
    @PostMapping("bankaccounts")
    public ResponseEntity createNewAccount(@RequestBody BankAccountEntity bankAccountEntity) {

        BankAccountEntity bankAccount = bankAccountRepository.findByBankIdAndIsActive(bankAccountEntity.getBankId(), 1);

        if(bankAccount!=null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        bankAccountRepository.save(bankAccountEntity);

        return ResponseEntity.ok(bankAccountEntity);

    }

    @ApiOperation(value = "Update money")
    @PutMapping("bankaccounts/{id}")
    public ResponseEntity updateMoneyBankById(
            @PathVariable(value = "id") int id,
            @RequestBody Map<String, String> bankaccount
    ) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findByBankIdAndIsActive(id, 1);

        if (bankAccountEntity != null) {
            float moneyOfBank = bankAccountEntity.getAccMoney();
            float money = Float.parseFloat(bankaccount.get("money"));
            float total = moneyOfBank + money;
            bankAccountEntity.setAccMoney(total);
            bankAccountRepository.save(bankAccountEntity);
            return new ResponseEntity(bankAccountEntity, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
