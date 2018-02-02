package com.es.jointexpensetracker.service;

import com.es.jointexpensetracker.model.Expense;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ExpenseService {
    private ArrayList<Expense> expenses;
    private static volatile ExpenseService instance;

    private ExpenseService()
    {
        String expenseGroup = UUID.randomUUID().toString();
        expenses= new ArrayList<>(Arrays.asList(
                new Expense(1L, "Train tickets from Minsk to Warsaw", new BigDecimal(200), "Andrei", expenseGroup),
                new Expense(2L, "Air tickets from Warsaw to Gran Carania and back", new BigDecimal(2000), "Ivan", expenseGroup),
                new Expense(3L, "Restaurant", new BigDecimal(90), "Andrei", expenseGroup),
                new Expense(4L, "Rent a car", new BigDecimal(700), "Sergei", expenseGroup),
                new Expense(5L, "Rent a car", new BigDecimal(500), "Igor", expenseGroup),
                new Expense(6L, "Rent a house", new BigDecimal(2000), "Igor", expenseGroup),
                new Expense(7L, "Restaurant", new BigDecimal(60), "Andrei", expenseGroup),
                new Expense(8L, "Gazoline", new BigDecimal(50), "Sergei", expenseGroup),
                new Expense(9L, "Gazoline", new BigDecimal(50), "Igor", expenseGroup),
                new Expense(10L, "Surfing", new BigDecimal(30), "Sergei", expenseGroup),
                new Expense(11L, "New year party shopping", new BigDecimal(30), "Igor", expenseGroup),
                new Expense(12L, "Surfing", new BigDecimal(30), "Sergei", expenseGroup),
                new Expense(13L, "Air wing", new BigDecimal(50), "Sergei", expenseGroup),
                new Expense(14L, "Bus tickets from Warsaw to Minsk", new BigDecimal(200), "Andrei", expenseGroup)
        ));
    }

    public static ExpenseService getInstance()
    {
        if (instance == null)
        {
            synchronized (ExpenseService.class)
            {
                if (instance == null)
                {
                    instance = new ExpenseService();
                }
            }

        }
        return instance;
    }

    public Expense getExpense(Long id) throws IllegalArgumentException
    {
       return expenses.stream().filter(expense -> expense.getId().equals(id)).findAny().
               orElseThrow(()->new IllegalArgumentException("There is no expense with id "+id));
    }

    public void addExpense(Expense newExpense)
    {
        expenses.add(newExpense);
    }

    public void deleteExpense(Long id) throws IllegalArgumentException
    {
        Expense expenseToDelete = getExpense(id);
        expenses.remove(expenseToDelete);
    }


    public ArrayList<Expense> getExpenses() {
        return expenses;
    }
}