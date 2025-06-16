


package com.splitapp.splitapp.controller;

import com.splitapp.splitapp.dto.SettlementDTO;
import com.splitapp.splitapp.model.Expense;
import com.splitapp.splitapp.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> createExpense(@Valid @RequestBody Expense expense) {
        Expense saved = expenseService.addExpense(expense);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<String> handleIllegalArgs(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
}

    
    @PutMapping("/{id}")
public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody Expense updated) {
    Expense existing = expenseService.getExpenseById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }

    existing.setAmount(updated.getAmount());
    existing.setDescription(updated.getDescription());
    existing.setPaidBy(updated.getPaidBy());
    existing.setSplits(updated.getSplits());

    return ResponseEntity.ok(expenseService.addExpense(existing));
}


@GetMapping("/balances")
public Map<String, Double> getBalances() {
    List<Expense> expenses = expenseService.getAllExpenses();
    Map<String, Double> balances = new HashMap<>();

    for (Expense e : expenses) {
        String payer = e.getPaidBy();
        double amount = e.getAmount();
        Map<String, Double> splits = e.getSplits();

        // Credit payer
        balances.put(payer, balances.getOrDefault(payer, 0.0) + amount);

        // Debit all participants
        for (Map.Entry<String, Double> entry : splits.entrySet()) {
            String person = entry.getKey();
            double share = entry.getValue();
            balances.put(person, balances.getOrDefault(person, 0.0) - share);
        }
    }

    return balances;
}
   @GetMapping("/settlements")
public List<SettlementDTO> getSettlements() {
    return expenseService.calculateSettlements();
}


    @DeleteMapping("/{id}")
public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
    Expense existing = expenseService.getExpenseById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }

    

    expenseService.deleteExpense(id);
    return ResponseEntity.ok("Deleted successfully");
}

@GetMapping("/people")
public Set<String> getAllPeople() {
    return expenseService.getAllPeople();
}




}
