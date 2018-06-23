package pl.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Debt;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {

    @Query(value = "Select d.* From debts d where d.case_id =:caseId" ,  nativeQuery = true)
    List<Debt> findAllDebtsByCaseId(@Param("caseId") Long id);

    @Query(value = "Select sum(d.principal) From debts d where d.case_id =:caseId" ,  nativeQuery = true)
    BigDecimal sumPrincipalByCaseId(@Param("caseId") Long id);

    @Query(value = "Select sum(d.costs) From debts d where d.case_id =:caseId" ,  nativeQuery = true)
    BigDecimal sumCostsByCaseId(@Param("caseId") Long id);

    @Query(value = "Select sum(d.interests) From debts d where d.case_id =:caseId" ,  nativeQuery = true)
    BigDecimal sumInterestsByCaseId(@Param("caseId") Long id);
}
