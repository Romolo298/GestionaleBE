package org.example.gestionale_be;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.gestionale_be.Mapper.ProdottoMapper;
import org.example.gestionale_be.Repository.FornitoreRepository;
import org.example.gestionale_be.Repository.ProdottoRepository;
import org.example.gestionale_be.Repository.ProdottoStoricoRepository;
import org.example.gestionale_be.Service.FornitoreService;
import org.example.gestionale_be.Service.ProdottoService;
import org.example.gestionale_be.Service.ProdottoStoricoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public abstract class GestionaleBeApplicationTests {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private ProdottoMapper prodottoMapper;

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    ProdottoStoricoRepository prodottoStoricoRepository;

    @Autowired
    ProdottoStoricoService prodottoStoricoService;

    @Autowired
    FornitoreService fornitoreService;

    @Autowired
    FornitoreRepository fornitoreRepository;

    @Autowired
    protected MockMvc mockMvc;

    private static final Random random = new Random();

    @BeforeEach
    void cleanRepositoryProdotto() {
        prodottoRepository.deleteAll();
    }

    @BeforeEach
    void cleanRepositoryProdottoStorico() {
        prodottoStoricoRepository.deleteAll();
    }

    @BeforeEach
    void cleanRepositoryFornitore() {
        fornitoreRepository.deleteAll();
    }

    protected static String generateRandomString(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'


        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @SneakyThrows
    protected static <T> T fillObject(T object) {
        var superclass = object.getClass().getSuperclass();
        if (superclass != null && !superclass.equals(Object.class))
            fillObject(object, superclass);

        fillObject(object, object.getClass());
        return object;
    }

    @SneakyThrows
    private static void fillObject(Object object, Class<?> clazz) {
        var fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == int.class) {
                field.setInt(object, random.nextInt());
            } else if (field.getType() == long.class) {
                field.setLong(object, random.nextLong());
            } else if (field.getType() == double.class) {
                field.setDouble(object, random.nextDouble());
            } else if (field.getType() == float.class) {
                field.setFloat(object, random.nextFloat());
            } else if (field.getType() == boolean.class) {
                field.setBoolean(object, random.nextBoolean());
            } else if (field.getType() == char.class) {
                field.setChar(object, (char) (random.nextInt(26) + 'a'));
            } else if (field.getType() == String.class) {
                field.set(object, generateRandomString(random.nextInt(20) + 1));
            } else if (field.getType() == Timestamp.class) {
                field.set(object, Timestamp.from(Instant.now()));
            } else if (field.getType() == BigInteger.class) {
                field.set(object, BigInteger.valueOf(random.nextLong(1000)));
            }
          /*  else if (field.getType() == Long.class) {
                field.set(object, random.nextLong(1000));
            }*/
        }
    }

    protected static String objectToJsonString(Object object) throws JsonProcessingException {
        var ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    protected Long extractIdFromResponse(String responseContent) {
        // Supponiamo che la risposta JSON abbia un campo "id", come { "id": 1, "nome": "Nuovo Prodotto", "prezzo": 100.0 }
        Pattern pattern = Pattern.compile("\"id\":\\s*(\\d+)"); // Regex per trovare il valore di "id"
        Matcher matcher = pattern.matcher(responseContent);
        if (matcher.find()) {
            return Long.valueOf(matcher.group(1));
        }
        return null;
    }

    protected String rimuoviIdDaJson(String json) {

        Pattern pattern = Pattern.compile("\"id\":\\s*\\d+,?");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            json = matcher.replaceFirst("");
        }

        json = json.replaceAll(",\\s*}", "}");

        return json;
    }


}
