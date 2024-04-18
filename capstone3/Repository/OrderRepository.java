package com.example.capstone3.Repository;

import com.example.capstone3.Model.Branch;
import com.example.capstone3.Model.Customer;
import com.example.capstone3.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {
    Orders findOrderById(Integer id);




    @Query("select o.comment from Orders o where o.comment is not null ")
    List<String> findAllComments();

    @Query("select a from Orders a where a.status='accepted'")
    List<Orders> findOrders();

//    @Modifying
//    @Transactional
//    @Query("UPDATE Orders p SET p.status='Shipment has arrived at warehouse' WHERE p.id= :orderId")
    //   void updateOrderStatusById(Integer orderId);

    List<Orders> findOrdersByCustomerId(Integer customerId);




    List<Orders> findOrdersByDriverId(Integer deliverId);

    List<Orders> findOrdersByBranch1(Branch branch);
}
