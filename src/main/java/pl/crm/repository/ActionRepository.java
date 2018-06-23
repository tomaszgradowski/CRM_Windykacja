package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Action;
import pl.crm.entity.Adress;


import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {


    @Query(value = "Select a.* From actions a where a.case_id =:caseId" ,  nativeQuery = true)
    List<Action> findAllByACaseId(@Param("caseId") Long id);

    @Query(value = "Select a.* From adresses a where a.debtor_id =:debtorId", nativeQuery = true)
    List<Adress> findAllReByDebtorId(@Param("debtorId") Long id);


}

