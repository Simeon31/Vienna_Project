package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in.ManageAccountUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto.AccountResponse;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto.CreateAccountRequest;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto.EditAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/accounts")
public class ManageAccountUseCaseAdapter {
    private final ManageAccountUseCase manageAccountUseCase;

    public ManageAccountUseCaseAdapter(ManageAccountUseCase manageAccountUseCase) {
        this.manageAccountUseCase = manageAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
        Account account = manageAccountUseCase.createAccount(
                request.username(), request.email(), request.password(), request.roleId());
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountResponse.fromDomain(account));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> listAccounts() {
        List<AccountResponse> accounts = manageAccountUseCase.listAccounts().stream()
                .map(AccountResponse::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> editAccount(@PathVariable Integer id, @RequestBody EditAccountRequest request) {
        Account account = manageAccountUseCase.editAccount(id, request.username(), request.email());
        return ResponseEntity.ok(AccountResponse.fromDomain(account));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<AccountResponse> activateAccount(@PathVariable Integer id) {
        Account account = manageAccountUseCase.activateAccount(id);
        return ResponseEntity.ok(AccountResponse.fromDomain(account));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<AccountResponse> deactivateAccount(@PathVariable Integer id) {
        Account account = manageAccountUseCase.deactivateAccount(id);
        return ResponseEntity.ok(AccountResponse.fromDomain(account));
    }

    @ExceptionHandler(Account.AccountNotFoundException.class)
    public ResponseEntity<String> handleNotFound(Account.AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Account.DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmail(Account.DuplicateEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Account.RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFound(Account.RoleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
