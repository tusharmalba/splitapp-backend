package com.splitapp.splitapp.repository;



import com.splitapp.splitapp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}

