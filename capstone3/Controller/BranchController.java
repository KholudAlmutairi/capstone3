package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Branch;
import com.example.capstone3.Service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping("/get")
    public ResponseEntity getBranch(){
        return ResponseEntity.status(200).body(branchService.getBranch());
    }

    @PostMapping("/add/{LaundryId}")
    public ResponseEntity addBranch(@PathVariable Integer LaundryId , @RequestBody @Valid Branch branch){
        branchService.addBranch(LaundryId,branch);
        return ResponseEntity.ok().body(new ApiResponse("Branch added"));

    }


    @PutMapping("/Update/{id}")
    public ResponseEntity UpdateBranch(@PathVariable Integer id, @RequestBody @Valid Branch branch){
        branchService.updateBranch(id,branch);

        return ResponseEntity.ok().body(new ApiResponse("Branch Update"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBranch(@PathVariable Integer id) {
        branchService.deleteBranch(id);
        return ResponseEntity.ok().body(new ApiResponse("Branch Deleted"));

    }

    //-----------------------   end crud   ------------------------------

    //reenad
    @PutMapping("/updatestatus/{orderId}")
    public ResponseEntity bupdateStatus(@PathVariable Integer orderId){
        branchService.bupdateStatus(orderId);
        return ResponseEntity.ok().body(new ApiResponse("status Updated"));
    }

    //-----------------------   1 endPoint   ------------------------------

    //reenad
    @GetMapping("/previousOrders/{branchId}")
    public ResponseEntity bpreviousOrders(@PathVariable Integer branchId){
        return ResponseEntity.status(200).body(branchService.bpreviousOrders(branchId));
    }

////    //-----------------------   2 endPoint   ------------------------------

    //reenad
    @GetMapping("/currentOrders/{branchId}")
    public ResponseEntity bcurrentOrders(@PathVariable Integer branchId){
        return ResponseEntity.status(200).body(branchService.bcurrentOrders(branchId));
    }

    //reenad
    @GetMapping("/getAllBranch/{laundryid}")
    public ResponseEntity getAllBranchByLaundryId (@PathVariable Integer laundryid){
        return ResponseEntity.status(200).body(branchService.getAllBranchByLaundryId(laundryid));
    }

    //reenad
    @GetMapping("/gettop/{laundryid}")
    public ResponseEntity getBranchByLaundryEvaluation(@PathVariable Integer laundryid){
        return ResponseEntity.status(200).body(branchService.getBranchByLaundryEvaluation(laundryid));
    }

}
