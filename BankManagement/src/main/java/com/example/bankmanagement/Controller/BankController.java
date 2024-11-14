package com.example.bankmanagement.Controller;

import com.example.bankmanagement.ApiResponse.ApiResponse;
import com.example.bankmanagement.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get-customers")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/add-customer")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);

        return new ApiResponse("Customer has been added successfully");
    }

    @PutMapping("/update-customer/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer) {
        customers.set(index, customer);

        return new ApiResponse("Customer at index " + index + "  has been updated successfully");
    }

    @DeleteMapping("/delete-customer/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        customers.remove(index);

        return new ApiResponse("Customer at index " + index + " has been deleted successfully");
    }

    @PutMapping("/deposit-money/{index}")
    public ApiResponse depositMoney(@PathVariable int index, @RequestBody double amount) {
        if (amount > 0) {
            customers.get(index).setBalance(customers.get(index).getBalance() + amount);
            return new ApiResponse("Amount has been deposited successfully");
        }
        return new ApiResponse("Only positive amount is allowed");
    }

    @PutMapping("/withdraw-money/{index}")
    public ApiResponse withdrawMoney(@PathVariable int index, @RequestBody double amount) {
        if (customers.get(index).getBalance() >= amount && amount > 0) {
            customers.get(index).setBalance(customers.get(index).getBalance() - amount);
            return new ApiResponse("Amount has been withdrawn successfully");
        }
        else if (amount <= 0)
            return new ApiResponse("Only positive amount is allowed");
        return new ApiResponse("You don't have enough money to withdraw");
    }
}
