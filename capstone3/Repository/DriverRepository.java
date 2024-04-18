package com.example.capstone3.Repository;

import com.example.capstone3.Model.Driver;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {

    Driver findDriversById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Driver p SET p.evaluation = :evaluation WHERE p.id = :driverId")
    void updateDriverEvaluationById(Integer driverId, Double evaluation);

    Driver findByUserNameAndPassword(String username, String password);

}
