package com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web;

import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.application.ports.in.AssignManagerAccessUseCase;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.Account;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.domain.ManagerAccess;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto.AssignManagerAccessRequest;
import com.warehouseoptimizer.warehouse_optimization_app.modules.admin.infrastructure.web.dto.ManagerAccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/accounts")
public class AssignManagerAccessUseCaseAdapter {
    private final AssignManagerAccessUseCase assignManagerAccessUseCase;

    public AssignManagerAccessUseCaseAdapter(AssignManagerAccessUseCase assignManagerAccessUseCase) {
        this.assignManagerAccessUseCase = assignManagerAccessUseCase;
    }

    @PutMapping("/{id}/manager-access")
    public ResponseEntity<ManagerAccessResponse> assignManagerAccess(@PathVariable Integer id,
                                                                     @RequestBody AssignManagerAccessRequest request) {
        ManagerAccess access = assignManagerAccessUseCase.assignManagerAccess(
                id, request.warehouseManagement(), request.storeManagement());
        return ResponseEntity.ok(ManagerAccessResponse.fromDomain(access));
    }

    @ExceptionHandler(Account.AccountNotFoundException.class)
    public ResponseEntity<String> handleNotFound(Account.AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ManagerAccess.NoAccessSelectedException.class,
            ManagerAccess.NotAManagerException.class,
            Account.RoleNotFoundException.class})
    public ResponseEntity<String> handleBadRequest(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
