package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.crm.entity.ActionType;


@Repository
public interface ActionTypeRepository extends JpaRepository<ActionType, Long> {



}

