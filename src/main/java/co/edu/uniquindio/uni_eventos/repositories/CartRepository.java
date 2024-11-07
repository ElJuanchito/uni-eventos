package co.edu.uniquindio.uni_eventos.repositories;

import co.edu.uniquindio.uni_eventos.entities.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    @Query("{'userId': ?0}")
    Optional<Cart> getCartByUserId(ObjectId userId);
}
