package com.splitapp.splitapp.service;

import com.splitapp.splitapp.model.Expense;
import com.splitapp.splitapp.repository.ExpenseRepository;
import com.splitapp.splitapp.dto.SettlementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

   public Expense addExpense(Expense expense) {
    double splitSum = expense.getSplits().values().stream().mapToDouble(Double::doubleValue).sum();
    if (Math.abs(splitSum - expense.getAmount()) > 0.01) {
        throw new IllegalArgumentException("Split amounts must total exactly: " + expense.getAmount());
    }

    return expenseRepository.save(expense);
}


    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }


    

    public List<SettlementDTO> calculateSettlements() {
        Map<String, Double> balances = new HashMap<>();
        for (Expense e : getAllExpenses()) {
            balances.put(e.getPaidBy(), balances.getOrDefault(e.getPaidBy(), 0.0) + e.getAmount());
            for (Map.Entry<String, Double> entry : e.getSplits().entrySet()) {
                balances.put(entry.getKey(), balances.getOrDefault(entry.getKey(), 0.0) - entry.getValue());
            }
        }

        // Split into creditors and debtors
        PriorityQueue<Map.Entry<String, Double>> debtors = new PriorityQueue<>(Map.Entry.comparingByValue());
        PriorityQueue<Map.Entry<String, Double>> creditors = new PriorityQueue<>((a, b) -> Double.compare(b.getValue(), a.getValue()));

        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            if (entry.getValue() < 0) debtors.offer(entry);
            else if (entry.getValue() > 0) creditors.offer(entry);
        }

        List<SettlementDTO> settlements = new ArrayList<>();

        while (!debtors.isEmpty() && !creditors.isEmpty()) {
            Map.Entry<String, Double> debtor = debtors.poll();
            Map.Entry<String, Double> creditor = creditors.poll();

            double amount = Math.min(-debtor.getValue(), creditor.getValue());
            settlements.add(new SettlementDTO(debtor.getKey(), creditor.getKey(), amount));

            double newDebtorBal = debtor.getValue() + amount;
            double newCreditorBal = creditor.getValue() - amount;

            if (newDebtorBal < 0) debtors.offer(Map.entry(debtor.getKey(), newDebtorBal));
            if (newCreditorBal > 0) creditors.offer(Map.entry(creditor.getKey(), newCreditorBal));
        }




        

        return settlements;
    }

    public Set<String> getAllPeople() {
    Set<String> people = new HashSet<>();
    for (Expense e : getAllExpenses()) {
        people.add(e.getPaidBy());
        people.addAll(e.getSplits().keySet());
    }
    return people;
}



    
}
