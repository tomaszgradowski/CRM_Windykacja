package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Sms;

import java.util.List;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Long> {


    @Query(value = "Select s.* From sms s where s.case_id =:caseId" ,  nativeQuery = true)
    List<Sms> findAllByACaseId(@Param("caseId") Long id);


}

