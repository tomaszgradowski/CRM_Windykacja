package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Result;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {


    @Query(value = "Select r.* From results r where r.case_id =:caseId" ,  nativeQuery = true)
    List<Result> findAllByACaseId(@Param("caseId") Long id);


    @Query(value = "Select r.* From results r where r.action_id =:actionId" ,  nativeQuery = true)
    List<Result> findAllByActionId(@Param("actionId") Long id);



}

