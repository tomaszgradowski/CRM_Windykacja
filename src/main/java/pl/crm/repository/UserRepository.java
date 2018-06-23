package pl.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.crm.entity.User;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.* FROM users AS u JOIN authorities AS a ON u.id = a.user_id WHERE a.authority = :role",
            nativeQuery = true)
    List<User> findUsersByRole(@Param("role") String role);


    User findUserByUsername(String username);


}
