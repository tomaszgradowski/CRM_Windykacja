package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.crm.entity.ActionTypeResultType;

import java.util.List;

@Repository
public interface ActionTypeResultTypeRepository extends JpaRepository<ActionTypeResultType, Long> {


    List<ActionTypeResultType> findAllByActionType_Id(Long id);

}

