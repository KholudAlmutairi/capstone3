package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.BranchRepository;
import com.example.capstone3.Repository.LaundryRepository;

import com.example.capstone3.Repository.ServiceLaundryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceLaundryService {

    private final ServiceLaundryRepository serviceLaundryRepository;
    private final LaundryRepository laundryRepository;
    private final BranchRepository branchRepository;


    public List<ServiceLaundry> getAllServiceLaundry(){
        return serviceLaundryRepository.findAll();
    }

    public void addServiceLaundry(Integer branchid,ServiceLaundry serviceLaundry){
        Branch branch = branchRepository.findBranchById(branchid);
        if (branch == null) {
            throw new ApiException("branchid not found");
        }
        serviceLaundry.setBranch(branch);
        serviceLaundryRepository.save(serviceLaundry);
    }


    public void updateServiceLaundry(Integer id, ServiceLaundry serviceLaundry){
        ServiceLaundry sl=serviceLaundryRepository.findServiceLaundryById(id);
        if(sl==null){
            throw new ApiException("service laundry found");
        }

        sl.setCategory(serviceLaundry.getCategory());
        sl.setName(serviceLaundry.getName());
        sl.setServiceType(serviceLaundry.getServiceType());
        sl.setPrice(serviceLaundry.getPrice());

        serviceLaundryRepository.save(sl);
    }

    public void deleteServiceLaundry(Integer id){
        ServiceLaundry sl=serviceLaundryRepository.findServiceLaundryById(id);
        if(sl==null){
            throw new ApiException("service laundry found");
        }
        serviceLaundryRepository.delete(sl);
    }

    //-----------------------   end crud   ------------------------------




   //-----------------------   1 endPoint ------------------------------
    public List<ServiceLaundry> highSalesService(){
        List<ServiceLaundry> s =serviceLaundryRepository.searchTopByNumberOrder();
        return s;
    }

//-----------------------   2 endPoint ------------------------------



    //kh     في برانش معين ترجع عددالخدمات داخل الكاتقري
    public Integer numberOfService(Integer brunchid,String Categry) {
        Integer counter=0;
        Branch branch1 = branchRepository.findBranchById(brunchid);
        if (branch1 == null) {
            throw new ApiException("id not found");
        }
        List<ServiceLaundry> s=serviceLaundryRepository.findServiceLaundriesByCategory(Categry);
        for(ServiceLaundry serviceLaundry:s){
            if(serviceLaundry.getBranch().getId()==brunchid);{
                counter++;
            }
        }

        return counter;
    }




}
