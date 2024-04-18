package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.BranchRepository;
import com.example.capstone3.Repository.CustomerRepository;
import com.example.capstone3.Repository.LaundryRepository;
import com.example.capstone3.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    private final LaundryRepository laundryRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public List<Branch> getBranch() {
        return branchRepository.findAll();
    }

    public void addBranch(Integer LaundryId, Branch branch) {
        Laundry laundry = laundryRepository.findLaundryById(LaundryId);
        if (laundry == null) {
            throw new ApiException("LaundryId not found");
        }
        branch.setLaundry(laundry);
        branchRepository.save(branch);
    }

    public void updateBranch(Integer id, Branch branch) {
        Branch branch1 = branchRepository.findBranchById(id);
        if (branch1 == null) {
            throw new ApiException("id not found");
        }
        branch1.setDistrict(branch.getDistrict());
        branch1.setStreet(branch.getStreet());
        branchRepository.save(branch1);

    }

    public void deleteBranch(Integer id) {
        Branch branch1 = branchRepository.findBranchById(id);
        if (branch1 == null) {
            throw new ApiException("id not found");
        }
        branchRepository.delete(branch1);
    }


    //-----------------------   end crud   ------------------------------


    //-----------------------   1 endPoint   ------------------------------

    public void bupdateStatus(Integer orderId) {
        Orders orders1 = orderRepository.findOrderById(orderId);
        if (orders1 == null) {
            throw new ApiException("order id not found");
        }

        if (orders1.getStatus().equalsIgnoreCase("waiting")) {
            orders1.setStatus("accepted");
            orderRepository.save(orders1);
        } else if (orders1.getStatus().equalsIgnoreCase("received stuff")) {
            orders1.setStatus("received to laundry");
            orderRepository.save(orders1);

        }
        //  -----------------------   2 endPoint   ------------------------------

    }
    public List<Orders> bcurrentOrders(Integer branchId) {
        ArrayList<Orders> o2 = new ArrayList<>();
        Branch branch=branchRepository.findBranchById(branchId);
        if (branch == null) {
            throw new ApiException("branch id not found");
        }
       List<Orders> o3 = orderRepository.findOrdersByBranch1(branch);
        if (o3== null) {
            throw new ApiException(" orders not found");
        }
        for (Orders orders1 : o3) {
            if (orders1.getStatus().equalsIgnoreCase("received stuff") || orders1.getStatus().equalsIgnoreCase("received to laundry")|| orders1.getStatus().equalsIgnoreCase("accepted")){
                o2.add(orders1);
            }}

        return o2;
    }
//
//    -----------------------   3 endPoint   ------------------------------
//
    public List<Orders> bpreviousOrders(Integer branchId) {
        ArrayList<Orders> o2 = new ArrayList<>();
        Branch branch=branchRepository.findBranchById(branchId);
        if (branch == null) {
            throw new ApiException("branch id not found");
        }
        List<Orders> o3 = orderRepository.findOrdersByBranch1(branch);
        if (o3== null) {
            throw new ApiException(" orders not found");
        }
        for (Orders orders1 : o3) {
            if (orders1.getStatus().equalsIgnoreCase("Delivered")){
                o2.add(orders1);
            }}

        return o2;
    }
    //    -----------------------   4 endPoint   ------------------------------


    public List<Branch> getAllBranchByLaundryId(Integer laundryid){

        return branchRepository.findBranchByLaundryId(laundryid);
    }
    //    -----------------------   5 endPoint   ------------------------------

    public List<Branch> getBranchByLaundryEvaluation(Integer laundryid){
        List<Branch> branches=branchRepository.searchTopByEvaluation();
        List<Branch> branches1=new ArrayList<>();
        for(Branch branch:branches){
            if(branch.getLaundry().getId() == laundryid){
                branches1.add(branch);
            }
        }
        return branches1;
    }

    //    -----------------------   6 endPoint   ------------------------------


    }