package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Telephone;

import java.util.List;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {


    @Query(value = "Select t.* From telephones t where t.debtor_id =:debtorId", nativeQuery = true)
    List<Telephone> findAllTelephonesByDebtorId(@Param("debtorId") Long id);


    @Query(value = "Select t.* From telephones t where t.debtor_id =:debtorId" ,  nativeQuery = true)
    Telephone findFirstByDebtorId(@Param("debtorId") Long id);


    @Query(value = "Select t.* From telephones t JOIN debtors d on t.debtor_id = d.id where d.case_id =:caseId" ,  nativeQuery = true)
    List<Telephone> findAllByCaseId(@Param("caseId") Long id);

}

