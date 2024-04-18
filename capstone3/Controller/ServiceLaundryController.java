package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.ServiceLaundry;
import com.example.capstone3.Service.ServiceLaundryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/servicelaundry")
public class ServiceLaundryController {

    private final ServiceLaundryService serviceLaundryService;

    @GetMapping("/get")
    public ResponseEntity getAllServiceLaundry(){

        return ResponseEntity.status(200).body(serviceLaundryService.getAllServiceLaundry());
    }


    @PostMapping("/add/{branchid}")
    public ResponseEntity addServiceLaundry(@PathVariable Integer branchid,@RequestBody @Valid ServiceLaundry serviceLaundry){

        serviceLaundryService.addServiceLaundry(branchid, serviceLaundry);
        return ResponseEntity.status(200).body(new ApiResponse("Service Laundry added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateServiceLaundry(@PathVariable Integer id,@RequestBody @Valid ServiceLaundry serviceLaundry){
        serviceLaundryService.updateServiceLaundry(id, serviceLaundry);
        return ResponseEntity.status(200).body(new ApiResponse("Service Laundry added"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteServiceLaundry(@PathVariable Integer id){
        serviceLaundryService.deleteServiceLaundry(id);
        return ResponseEntity.status(200).body(new ApiResponse("ServiceLaundry deleted"));
    }

    //-----------------------   end crud   ------------------------------

    //-----------------------   1 endpoint   ------------------------------

    //kholud
    @GetMapping("/highSalesService")
    public ResponseEntity highSalesService(){
        return ResponseEntity.status(200).body(serviceLaundryService.highSalesService());
    }

    //-----------------------   2 endpoint   ------------------------------
    // kholud
    @GetMapping("/numberOfService/{brunchid}/{Categry}")
    public ResponseEntity numberOfService(@PathVariable Integer brunchid,@PathVariable String Categry){
        return ResponseEntity.status(200).body(serviceLaundryService.numberOfService(brunchid, Categry));
    }


}
