package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.crm.entity.Case;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

    @Query(value = "select max(c.id) from cases c", nativeQuery = true)
    Long maxIdFromCase();

}
