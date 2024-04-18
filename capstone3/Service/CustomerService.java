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
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final ServiceLaundryRepository serviceLaundryRepository;
    private final BranchRepository branchRepository;
    private final LaundryRepository laundryRepository;

    // Get All Customers
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }


    //• Add new Customer
    public void addCustomer(Customer customers) {

        customerRepository.save(customers);

    }


    //• Update Customer
    public void updateCustomer(Integer id,Customer customers) {
        Customer c=customerRepository.findCustomersById(id);

        if (c == null) {
            throw new ApiException("Wrong id");
        }
        c.setName(customers.getName());
        // c.setProfile(customers.getProfile());

        customerRepository.save(c);

    }

    //• Delete Customer
    public void deleteCustomer(Integer id) {
        Customer c=customerRepository.findCustomersById(id);
        if (c == null) {
            throw new ApiException("Wrong id");
        }
        customerRepository.delete(c);
    }

    //-----------------------   end crud   ------------------------------


    public void order(Integer customerId, Integer serviceId ) {

        Orders order1 = new Orders();

        Customer customer1 = customerRepository.findCustomersById(customerId);
        if (customer1 == null) {
            throw new ApiException("customer id not found");
        }

        ServiceLaundry service=serviceLaundryRepository.findServiceLaundryById(serviceId);
        if(service==null){
            throw new ApiException("service laundry found");
        }

        //  order1.setQuantity(quantity);
        // order1.setCustomerId(customerId);
        //  order1.setBranchId(service.getBranch().getId());
        order1.setBranch1(service.getBranch());
        order1.setCustomer(customer1);
        order1.setServicePrice(service.getPrice());
        order1.setDeliveryPrice(order1.getDeliveryPrice());
        order1.setTotalPrice(service.getPrice() + order1.getDeliveryPrice());
        order1.setStatus("Waiting");
        order1.setDistrictOfLaundry(service.getBranch().getDistrict());
        order1.setStreetOfLaundry(service.getBranch().getStreet());
        order1.setNationalAddressOfCustomer(customer1.getProfile().getNationalAddress());
        order1.setPhoneNumberForCustomer(customer1.getProfile().getPhoneNumber());
        order1.getBranch1().setNumberOrder(order1.getBranch1().getNumberOrder()+1);
        service.setNumberOrder(service.getNumberOrder()+1);

        orderRepository.save(order1);

    }

    //-----------------------   2 endPoint   ------------------------------

        public String statusOfOrder (Integer orderId) {
            Orders orders1 = orderRepository.findOrderById(orderId);
            if (orders1 == null) {
                throw new ApiException("order id not found");
            }

            return orders1.getStatus();
        }

        //-----------------------   3 endPoint   ------------------------------

        public List<Orders> currentOrders(Integer customerId) {
            ArrayList<Orders> orders2 = new ArrayList<>();
            Customer c=customerRepository.findCustomersById(customerId);
            if (c == null) {
                throw new ApiException("customerId not found");
            }
            List<Orders> orders3 = orderRepository.findOrdersByCustomerId(customerId);
            if (orders3== null) {
                throw new ApiException(" orders not found");
            }
            for (Orders orders1 : orders3) {
                if (orders1.getStatus().equalsIgnoreCase("received stuff") || orders1.getStatus().equalsIgnoreCase("received to laundry") || orders1.getStatus().equalsIgnoreCase("accepted") || orders1.getStatus().equalsIgnoreCase("Waiting") ){
                    orders2.add(orders1);
                }}

            return orders2;
        }

        //-----------------------   4 endPoint   ------------------------------

        public List<Orders> previousOrders(Integer customerId) {
            ArrayList<Orders> orders2 = new ArrayList<>();
            Customer c=customerRepository.findCustomersById(customerId);
            if (c == null) {
                throw new ApiException("customerId not found");
            }
            List<Orders> orders3 = orderRepository.findOrdersByCustomerId(customerId);
            if (orders3== null) {
                throw new ApiException(" orders not found");
            }
            for (Orders orders1 : orders3) {
                if (orders1.getStatus().equalsIgnoreCase("Delivered") ){
                    orders2.add(orders1);
                }}

            return orders2;
        }


        //-----------------------   5 endPoint   ------------------------------

      public void evaluationDriver(Integer customerId, Integer orderId, Double evaluation) {

        Customer customer1 = customerRepository.findCustomersById(customerId);
        if (customer1 == null) {
            throw new ApiException("customer id not found");
        }
        Orders orders1 = orderRepository.findOrderById(orderId);
        if (orders1 == null) {
            throw new ApiException(" Order id not found");
        }
        if (orders1.getStatus().equalsIgnoreCase("Delivered")) {
            if (orders1.getCustomer().getId().equals(customerId)){

                Driver driver = driverRepository.findDriversById(orders1.getDriver().getId());
                orders1.getDriver().setRating(orders1.getDriver().getRating()+evaluation);
                Double e =   (driver.getRating()/ driver.getDeliveryOrders());
                orders1.getDriver().setEvaluation(e);
                driver.setEvaluation(e);
             //   driverRepository.updateDriverEvaluationById(driver.getId(),e);
                driverRepository.save(driver);
                orderRepository.save(orders1);


            }}
    }

    //    -----------------------   6 endPoint   ------------------------------
   //KH
    public void rateOfBranch(Integer orderId,Integer rating){
        Orders order =orderRepository.findOrderById(orderId);
        if (order==null){
            throw new ApiException("order id not found");
        }
        if(order.getStatus().equalsIgnoreCase("Delivered")){
        }
        if(order.getEvaluation()==0){
            order.getBranch1().setRating(order.getBranch1().getRating()+rating);
            Double e1=(order.getBranch1().getRating()/(double)order.getBranch1().getNumberOrder());
            order.getBranch1().setEvaluation(e1);
            order.setEvaluation(e1);

        }

        orderRepository.save(order);
    }
    //    -----------------------   7 endPoint   ------------------------------
    //KH
    public Double getRatingOfBranch(Integer branchId){
        Branch branch=branchRepository.findBranchById(branchId);
        if (branch == null) {
            throw new ApiException("branch id not found");
        }
        return branch.getEvaluation();
    }
    //    -----------------------   8 endPoint   ------------------------------
   public List<Branch> getLaundryNear(Integer customerId) {

        Customer c = customerRepository.findCustomersById(customerId);
        if (c == null) {
            throw new ApiException("customerId not found");
        }
       List<Branch> branches = branchRepository.findBranchByDistrict(c.getProfile().getDistrict());

       return branches;

    }
    //    -----------------------   9 endPoint   ------------------------------
    public void CommentForOrder(Integer customerId, Integer orderId, String comment) {
        Customer c = customerRepository.findCustomersById(customerId);
        if (c == null) {
            throw new ApiException("customerId not found");
        }
        Orders order = orderRepository.findOrderById(orderId);
        if (order == null) {
            throw new ApiException("order id not found");
        }
        if (order.getStatus().equalsIgnoreCase("Delivered")) {
            if (order.getCustomer().getId()==customerId) {
                if (order.getComment() == null) {
                    order.setComment(comment);
                }
            }
        }
        orderRepository.save(order);
    }


    }


