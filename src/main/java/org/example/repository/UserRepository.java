package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String email);

    User findByName(String name);

    User findByGoogleUsername(String googleUsername);

    User findByGoogleName(String googleName);

}
