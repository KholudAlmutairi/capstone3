package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Driver;
import com.example.capstone3.Service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/get")
    public ResponseEntity getDriver(){
        return ResponseEntity.status(200).body(driverService.getDriver());
    }

    @PostMapping("/add")
    public ResponseEntity addDriver(@RequestBody @Valid Driver driver){
        driverService.addDriver(driver);
        return ResponseEntity.ok().body(new ApiResponse("Driver added"));

    }
    @PutMapping("/Update/{id}")
    public ResponseEntity UpdateDriver(@PathVariable Integer id, @RequestBody @Valid Driver driver){
        driverService.updateDriver(id,driver);
        return ResponseEntity.ok().body(new ApiResponse("Driver Update"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDriver(@PathVariable Integer id){
        driverService.deleteDriver(id);
        return ResponseEntity.ok().body(new ApiResponse("Driver Deleted"));

    }

    //-----------------------   end crud   ------------------------------

    //-----------------------   1 endPoint   ------------------------------

    //sara
    @GetMapping("/getEvaluationOfDriver/{driverId}")
    public ResponseEntity getEvaluationOfDriver(@PathVariable Integer driverId){

        return ResponseEntity.status(200).body(driverService.getEvaluationOfDriver(driverId));
    }

    //-----------------------   2 endPoint   ------------------------------

    //sara
    @GetMapping("/findOrders")
    public ResponseEntity findOrders(){

        return ResponseEntity.status(200).body(driverService.findOrders());
    }

    //-----------------------   3endPoint   ------------------------------
    //sara
    @PutMapping("/delivery/{driverId}/{orderId}")
    public ResponseEntity delivery(@PathVariable Integer driverId ,@PathVariable Integer orderId ){

        driverService.delivery(driverId,orderId);
        return ResponseEntity.ok().body(new ApiResponse("Driver Update"));
    }

    //-----------------------   4endPoint   ------------------------------
    //sara
    @GetMapping("/previousOrders/{driverId}")
    public ResponseEntity previousOrders(@PathVariable Integer driverId){

        return ResponseEntity.status(200).body(driverService.previousOrders(driverId));
    }

    //-----------------------   5 endPoint   ------------------------------

    //sara
    @GetMapping("/currentOrders/{driverId}")
    public ResponseEntity  currentOrders(@PathVariable Integer driverId){

        return ResponseEntity.status(200).body(driverService.currentOrders(driverId));
    }

    //-----------------------   6 endPoint   ------------------------------
    //sara
    @PutMapping("/statusd/{driverId}/{orderId}")
    public ResponseEntity  updateStatus(@PathVariable Integer driverId ,@PathVariable Integer orderId ){

        driverService.updateStatus(driverId,orderId);
        return ResponseEntity.status(200).body(new ApiResponse("Driver Update"));
    }

    //-----------------------   7 endPoint   ------------------------------

    //kholud
    @GetMapping("username/{username}/{password}")
    public ResponseEntity searchByusernameandpassword(@PathVariable String username,@PathVariable String password){
        return ResponseEntity.status(200).body(driverService.searchByusernameandpassword(username, password));
    }


}
