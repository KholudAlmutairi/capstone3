package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Model.ServiceLaundry;
import com.example.capstone3.Repository.CustomerRepository;

import com.example.capstone3.Repository.OrderRepository;
import com.example.capstone3.Repository.OwnerRepository;
import com.example.capstone3.Repository.ServiceLaundryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ServiceLaundryRepository serviceLaundryRepository;

    public List<Owner> getAllOwner(){
        return ownerRepository.findAll();
    }

    public void addOwner(Owner owner){
        ownerRepository.save(owner);
    }

    public void updateOwner(Integer id,Owner owner){
        Owner o =ownerRepository.findOwnerById(id);
        if(o==null){
            throw new ApiException("owner not found");
        }
        o.setName(owner.getName());
        o.setPhoneNumber(owner.getPhoneNumber());
        o.setEmail(owner.getEmail());
        o.setPassword(owner.getPassword());

        ownerRepository.save(o);
    }

    public void deleteOwner(Integer id){
        Owner o =ownerRepository.findOwnerById(id);
        if(o==null){
            throw new ApiException("owner not found");
        }
        ownerRepository.delete(o);
    }

    //-----------------------   end crud   ------------------------------

    //-----------------------   1 endPoint   ------------------------------

    public void discountForServiceType(Integer servicetypeid,Double discount){
        ServiceLaundry sl=serviceLaundryRepository.findServiceLaundryById(servicetypeid);
        if(sl==null){
            throw new ApiException("service not found");
        }

           Double d=sl.getPrice()-discount;
           sl.setPrice(d);
           serviceLaundryRepository.save(sl);


    }

    //-----------------------   2 endPoint   ------------------------------









}
