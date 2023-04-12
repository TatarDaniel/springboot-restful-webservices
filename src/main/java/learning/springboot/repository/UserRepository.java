package learning.springboot.repository;

import learning.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@repository doesnt need because in JpaRepository is SimpleJpaRepisotory which has already @repository annotation
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
