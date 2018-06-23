package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.crm.entity.ResultType;

@Repository
public interface ResultTypeRepository extends JpaRepository<ResultType, Long> {



}

