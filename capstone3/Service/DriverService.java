package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Driver;
import com.example.capstone3.Model.Orders;
import com.example.capstone3.Repository.DriverRepository;


import com.example.capstone3.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;


    public List<Driver> getDriver() {
        return driverRepository.findAll();
    }

    public void addDriver(Driver driver) {

        driverRepository.save(driver);
    }

    public void updateDriver(Integer id, Driver driver) {
        Driver driver1 = driverRepository.findDriversById(id);
        if (driver1 == null) {
            throw new ApiException("id not found");
        }
        driver1.setEmail(driver.getEmail());
        driver1.setPassword(driver.getPassword());
        driver1.setPhoneNumber(driver.getPhoneNumber());
        driverRepository.save(driver1);
    }

    public void deleteDriver(Integer id) {
        Driver driver = driverRepository.findDriversById(id);
        if (driver == null) {
            throw new ApiException("id not found");
        }
        driverRepository.delete(driver);
    }

    //-----------------------   end crud   ------------------------------


    //-----------------------   1 endPoint   ------------------------------

    public Double getEvaluationOfDriver(Integer driverId) {
        Driver driver = driverRepository.findDriversById(driverId);
        if (driver == null) {
            throw new ApiException("id not found");
        }
        return driver.getEvaluation();
    }

    //-----------------------   2 endPoint   ------------------------------

    public List<Orders> findOrders() {
        List<Orders> orders = orderRepository.findOrders();
        if (orders == null) {
            throw new ApiException("Orders not found");
        }
        return orders;
    }

    //-----------------------   3 endPoint   ------------------------------

    public void delivery(Integer driverId, Integer orderId) {
        Orders order1 = orderRepository.findOrderById(orderId);
        if (order1 == null) {
            throw new ApiException("order id not found");
        }
        Driver driver1 = driverRepository.findDriversById(driverId);
        if (driver1 == null) {
            throw new ApiException("driver id not found");
        }
        if (order1.getStatus().equalsIgnoreCase("accepted")) {
            order1.setDriver(driver1);

            driver1.setDeliveryOrders(driver1.getDeliveryOrders() + 1);
            orderRepository.save(order1);
            driverRepository.save(driver1);

        }
    }

    //-----------------------   4 endPoint   ------------------------------

    public List<Orders> previousOrders(Integer driverId) {
        ArrayList<Orders> orders2 = new ArrayList<>();
        Driver drivers = driverRepository.findDriversById(driverId);
        if (drivers == null) {
            throw new ApiException("driver id not found");
        }
        List<Orders> orders3 = orderRepository.findOrdersByDriverId(driverId);
        if (orders3 == null) {
            throw new ApiException(" orders not found");
        }
        for (Orders orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Delivered")) {
                orders2.add(orders1);
            }
        }

        return orders2;
    }

    //-----------------------   5 endPoint   ------------------------------

    public List<Orders> currentOrders(Integer driverId) {
        ArrayList<Orders> orders2 = new ArrayList<>();
        Driver driver = driverRepository.findDriversById(driverId);
        if (driver == null) {
            throw new ApiException("driver id not found");
        }
        List<Orders> orders3 = orderRepository.findOrdersByDriverId(driverId);
        if (orders3 == null) {
            throw new ApiException(" orders not found");
        }
        for (Orders orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("received stuff") || orders1.getStatus().equalsIgnoreCase("received to laundry")) {
                orders2.add(orders1);
            }
        }

        return orders2;
    }

    //-----------------------   6 endPoint   ------------------------------

    public void updateStatus(Integer driverId, Integer orderId) {
        Orders order1 = orderRepository.findOrderById(orderId);
        if (order1 == null) {
            throw new ApiException("order id not found");
        }
        Driver driver1 = driverRepository.findDriversById(driverId);
        if (driver1 == null) {
            throw new ApiException("driver id not found");
        }

        if (order1.getDriver().getId() == driverId) {
            if (order1.getStatus().equalsIgnoreCase("accepted")) {
                order1.setStatus("received stuff");
                orderRepository.save(order1);
            } else if (order1.getStatus().equalsIgnoreCase("received to laundry")) {
                order1.setStatus("Delivered");
                orderRepository.save(order1);

            }
        }
    }

        //-----------------------   7 endPoint   ------------------------------

        public Driver searchByusernameandpassword(String username,String password){
            Driver d=driverRepository.findByUserNameAndPassword(username,password);
            if(d==null){
                throw new ApiException("not found");
            }
            return d;
        }



}