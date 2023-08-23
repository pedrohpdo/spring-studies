package br.com.pedro.springStarter.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import br.com.pedro.springStarter.models.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);
}

