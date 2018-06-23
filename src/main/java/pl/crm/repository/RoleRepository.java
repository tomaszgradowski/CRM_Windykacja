package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Action;
import pl.crm.entity.Adress;
import pl.crm.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    @Query(value = "Select r.* From roles r where r.case_id =:caseId" ,  nativeQuery = true)
    List<Action> findAllByACaseId(@Param("caseId") Long id);

    @Query(value = "Select r.* From roles r where r.debtor_id =:debtorId", nativeQuery = true)
    List<Adress> findAllReByDebtorId(@Param("debtorId") Long id);


}

