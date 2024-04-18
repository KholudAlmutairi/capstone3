package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/get")
    public ResponseEntity getAllOwner(){

        return ResponseEntity.status(200).body(ownerService.getAllOwner());
    }


    @PostMapping("/add")
    public ResponseEntity addOwner(@RequestBody @Valid Owner owner){

        ownerService.addOwner(owner);
        return ResponseEntity.status(200).body(new ApiResponse("owner added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateOwner(@PathVariable Integer id,@RequestBody @Valid Owner owner){
        ownerService.updateOwner(id, owner);
        return ResponseEntity.status(200).body(new ApiResponse("owner added"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOwner(@PathVariable Integer id){
        ownerService.deleteOwner(id);
        return ResponseEntity.status(200).body(new ApiResponse("owner deleted"));
    }

    //-----------------------   end crud   ------------------------------

    //-----------------------   1 endPoint   ------------------------------

    //reenad
    @PutMapping("/discount/{servicetypeid}/{discount}")
    public ResponseEntity discountForServiceType(@PathVariable Integer servicetypeid,@PathVariable Double discount){
        ownerService.discountForServiceType(servicetypeid, discount);
        return ResponseEntity.status(200).body(new ApiResponse("discount for service type added"));

    }


    //-----------------------   2 endPoint   ------------------------------


}
