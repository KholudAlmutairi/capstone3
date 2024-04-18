package com.example.capstone3.Repository;

import com.example.capstone3.Model.ServiceLaundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceLaundryRepository extends JpaRepository<ServiceLaundry,Integer> {

    ServiceLaundry findServiceLaundryById(Integer id);
    @Query("SELECT s FROM ServiceLaundry s ORDER BY s.numberOrder DESC")
    List<ServiceLaundry> searchTopByNumberOrder();

    List<ServiceLaundry> findServiceLaundriesByCategory(String categry);

}
