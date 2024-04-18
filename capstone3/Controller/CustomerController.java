package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }


    @PostMapping("/add")
    public ResponseEntity addCustomers(@RequestBody @Valid Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id, @RequestBody @Valid Customer customers){
        customerService.updateCustomer(id,customers);
        return ResponseEntity.status(200).body(new ApiResponse( "Customer updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted!"));
    }

    //-----------------------   end crud   ------------------------------

    //-----------------------   1 endPoint   ------------------------------


    //sara
    @PostMapping("/order/{customerId}/{serviceId}")
    public ResponseEntity order(@PathVariable Integer customerId, @PathVariable Integer serviceId ){
        customerService.order(customerId,serviceId);
        return ResponseEntity.status(200).body(new ApiResponse("order was completed successfully"));

    }

    //-----------------------   2 endPoint   ------------------------------
    //sara
    @GetMapping("/statusOfOrder/{OrderId}")
    public ResponseEntity  statusOfOrder(@PathVariable Integer OrderId){

        return ResponseEntity.status(200).body(customerService.statusOfOrder(OrderId));
    }

    //-----------------------   3 endPoint   ------------------------------
    //sara
    @GetMapping("/previousOrders/{customerId}")
    public ResponseEntity previousOrders(@PathVariable Integer customerId){

        return ResponseEntity.status(200).body(customerService.previousOrders(customerId));
    }

    //-----------------------   4 endPoint   ------------------------------

    //sara
    @GetMapping("/currentOrders/{customerId}")
    public ResponseEntity  currentOrders(@PathVariable Integer customerId){

        return ResponseEntity.status(200).body(customerService.currentOrders(customerId));
    }

    //-----------------------   5 endPoint   ------------------------------

    //sara
    @PutMapping("/evaluationDriver/{customerId}/{orderId}/{evaluation}")
    public ResponseEntity evaluationDriver(@PathVariable Integer customerId, @PathVariable Integer orderId,@PathVariable  Double evaluation){
        customerService.evaluationDriver(customerId,orderId,evaluation);
        return ResponseEntity.status(200).body(new ApiResponse( "evaluation Driver updated"));
    }
    //-----------------------   6 endPoint   ------------------------------

    //kholud
    @PutMapping("/rateOfBranch/{orderId}/{rating}")
    public ResponseEntity rateOfBranch(@PathVariable Integer orderId,@PathVariable Integer rating){
        customerService.rateOfBranch(orderId,rating);
        return ResponseEntity.status(200).body(new ApiResponse("rating done successfully"));
    }

    //-----------------------   7 endPoint   ------------------------------
    //kholud
    @GetMapping("/getRatingOfBranch/{branchId}")
    public ResponseEntity getRatingOfBranch(@PathVariable Integer branchId){
        return ResponseEntity.ok(customerService.getRatingOfBranch(branchId));
    }
//-----------------------   8 endPoint   ------------------------------

    //kholud
    @GetMapping("/getLaundryNear/{customerId}")
     public ResponseEntity getLaundryNear(@PathVariable Integer customerId){
         return ResponseEntity.status(200).body(customerService.getLaundryNear(customerId));
     }

    //-----------------------   9 endPoint   ------------------------------

    //kholud
    @PutMapping("/CommentForOrder/{customerId}/{orderId}/{comment}")
     public ResponseEntity CommentForOrder(@PathVariable Integer customerId,@PathVariable Integer orderId,@PathVariable String comment){
        customerService.CommentForOrder(customerId, orderId, comment);
         return ResponseEntity.status(200).body(new ApiResponse("Comment for order completed successfully"));
     }


}
