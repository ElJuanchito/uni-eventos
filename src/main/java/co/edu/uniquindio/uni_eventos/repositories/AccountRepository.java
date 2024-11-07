package co.edu.uniquindio.uni_eventos.repositories;

import co.edu.uniquindio.uni_eventos.entities.Account;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    @Query("{'user': { $ne: null}, 'user.cedula': ?0}")
    Optional<Account> findAccountByCedula(String cedula);

    @Query("{'email': ?0}")
    Optional<Account> findAccountByEmail(String email);

    @Query("{email: ?0, password: ?1}")
    Optional<Account> validateLogin(String email, String password);

    boolean existsById(String id);
}
