package co.edu.uniquindio.uni_eventos.tests;

import co.edu.uniquindio.uni_eventos.dtos.account.CreateAccountDTO;
import co.edu.uniquindio.uni_eventos.entities.Account;
import co.edu.uniquindio.uni_eventos.repositories.AccountRepository;
import co.edu.uniquindio.uni_eventos.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService service;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        //accountRepository.deleteAll();
    }

    @Test
    public void testCreateAccount() throws Exception {
        // Crear el DTO para la cuenta
        CreateAccountDTO createAccountDTO = new CreateAccountDTO(
                "1004917061",
                "Pepesito",
                "3113684719",
                "Mercedes del Norte No1",
                "perdomocardenas1@gmail.com",
                "12345678"
        );

        String cedula = createAccountDTO.cedula();

        service.createAccount(createAccountDTO);

        assertNotNull(cedula, "La cedula devuelta no debería ser null");

        Optional<Account> createdAccount = accountRepository.findById("671063110bfb6530ab4265d1");
        assertTrue(createdAccount.isPresent(), "La cuenta debería existir en la base de datos");

        Account account = createdAccount.get();

        assertEquals(createAccountDTO.email(), account.getEmail(), "El correo electrónico no coincide");
        assertEquals(createAccountDTO.cedula(), account.getUser().getCedula(), "La cedula no coincide");
        assertEquals(createAccountDTO.name(), account.getUser().getName(), "El nombre no coincide");
        assertEquals(createAccountDTO.phone(), account.getUser().getPhone(), "El teléfono no coincide");
    }
}
