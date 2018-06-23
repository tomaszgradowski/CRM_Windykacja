package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Adress;

import java.util.List;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {


    @Query(value = "Select a.* From adresses a where a.debtor_id =:debtorId", nativeQuery = true)
    List<Adress> findAllAdressesByDebtorId(@Param("debtorId") Long id);

    @Query(value = "Select a.* From adresses a where a.debtor_id =:debtorId" ,  nativeQuery = true)
    Adress findFirstByDebtorId(@Param("debtorId") Long id);


}

