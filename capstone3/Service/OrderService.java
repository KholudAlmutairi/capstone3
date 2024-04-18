package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.*;
import com.example.capstone3.Repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ServiceLaundryRepository serviceLaundryRepository;
    private final BranchRepository branchRepository;
    private final DriverRepository driverRepository;
    private final CustomerService customerService;

    public List<Orders> getAllOrder(){
        return orderRepository.findAll();

    }

    public void addOrder(Integer orderId ,Integer customerId, Integer serviceId ) {

        Orders order1=orderRepository.findOrderById(orderId);
        if(order1==null){
            throw new ApiException("order not found");
        }

        Customer customer1 = customerRepository.findCustomersById(customerId);
        if (customer1 == null) {
            throw new ApiException("customer id not found");
        }

        ServiceLaundry service= serviceLaundryRepository.findServiceLaundryById(serviceId);
        if(service==null){
            throw new ApiException("service laundry found");
        }

        if(order1.getStatus().equalsIgnoreCase("waiting")){
        if(order1.getCustomer().getId()==customerId) {


            //  order1.setQuantity(quantity);
            // order1.setCustomerId(customerId);
            //  order1.setBranchId(service.getBranch().getId());
            //  order1.setBranch(service.getBranch());
            // order1.setCustomer(customer1);
            order1.setServicePrice(order1.getServicePrice()+service.getPrice());
            //order1.setDeliveryPrice(order1.getDeliveryPrice());
            order1.setTotalPrice(order1.getServicePrice() + order1.getDeliveryPrice());
            // order1.setStatus("Waiting");
            // order1.setDistrictOfLaundry(service.getBranch().getDistrict());
            //order1.setStreetOfLaundry(service.getBranch().getStreet());
            // order1.setNationalAddressOfCustomer(customer1.getProfile().getNationalAddress());
            //order1.getBranch().setNumberOfOrder(order1.getBranch().getNumberOfOrder() + 1);
            service.setNumberOrder(service.getNumberOrder() + 1);

            orderRepository.save(order1);
        }
        }else
            throw new ApiException("not succesfully add order");
    }





    public void updateOrder(Integer id, Orders orders){
        Orders o=orderRepository.findOrderById(id);
        if(o==null){
            throw new ApiException("orders not found");
        }
        o.setDeliveryPrice(orders.getDeliveryPrice());
        o.setTotalPrice(orders.getTotalPrice());
        o.setNationalAddressOfCustomer(orders.getNationalAddressOfCustomer());
        o.setDistrictOfLaundry(orders.getDistrictOfLaundry());
        o.setStreetOfLaundry(orders.getStreetOfLaundry());
        o.setEvaluation(orders.getEvaluation());
        o.setComment(orders.getComment());

        orderRepository.save(o);

    }


    public void deleteOrder(Integer id){
        Orders orders =orderRepository.findOrderById(id);
        if(orders ==null){
            throw new ApiException("orders not found");
        }
        orderRepository.delete(orders);
    }

    //-----------------------   end crud   ------------------------------

    //-----------------------   1 endpoint   ------------------------------




    public List<String> getcomment(Integer customerid){
        List<Orders> previousorder=orderRepository.findOrdersByCustomerId(customerid);
        if(previousorder==null){
            throw new ApiException("orders for customer not found");
        }
        List<String> allcomment=new ArrayList<>();
        for(Orders o:previousorder){
            allcomment.add(o.getComment());
        }
        return allcomment;

    }



}
