package examples;

import org.junit.Test;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Part1 {
    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }
}
