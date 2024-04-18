package com.example.capstone3.Repository;

import com.example.capstone3.Model.Branch;
import com.example.capstone3.Model.Laundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {
    Branch findBranchById(Integer id);

    List<Branch> findBranchByLaundryId(Integer laundryid);

    @Query("select b from Branch b order by b.evaluation DESC ")
    List<Branch> searchTopByEvaluation();

    List<Branch> findBranchByDistrict(String district);
}
