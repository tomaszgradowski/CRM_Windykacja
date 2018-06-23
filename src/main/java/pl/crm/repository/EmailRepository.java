package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Adress;
import pl.crm.entity.Email;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {


    @Query(value = "Select e.* From emails e where e.debtor_id =:debtorId", nativeQuery = true)
    List<Email> findAllEmailsByDebtorId(@Param("debtorId") Long id);

    @Query(value = "Select e.* From emails e where e.debtor_id =:debtorId" ,  nativeQuery = true)
    Adress findFirstByDebtorId(@Param("debtorId") Long id);


}

