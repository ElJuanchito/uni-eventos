package co.edu.uniquindio.uni_eventos.services.impl;

import co.edu.uniquindio.uni_eventos.services.CodeGeneratorService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public String generateCode(int length) {
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}
