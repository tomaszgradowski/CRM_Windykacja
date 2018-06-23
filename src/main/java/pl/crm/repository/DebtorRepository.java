package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Debtor;

import java.util.List;

@Repository
public interface DebtorRepository extends JpaRepository<Debtor, Long> {

    @Query(value = "Select d.* From debtors d where d.case_id =:caseId" ,  nativeQuery = true)
    List<Debtor> findAllByACaseId(@Param("caseId") Long id);

    @Query(value = "Select d.* From debtors d where d.case_id =:caseId" ,  nativeQuery = true)
    List<Debtor> findAllDeborsByCaseId(@Param("caseId") Long id);



}
