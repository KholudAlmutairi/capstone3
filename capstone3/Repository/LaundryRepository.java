package com.example.capstone3.Repository;

import com.example.capstone3.Model.Laundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaundryRepository extends JpaRepository<Laundry,Integer> {

    Laundry findLaundryById(Integer id);
    List<Laundry> findLaundryByOwnerId(Integer ownerid);



}
