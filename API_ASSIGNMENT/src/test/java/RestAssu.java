import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssu {

    @Test
    public void testgetcall(){
        Response RESPONCE = (Response)
        given().
            baseUri("https://jsonplaceholder.typicode.com").
            header("Content-Type", "application/json").

        when().
           get("/posts").

        then().
           statusCode(200);
    }

    @Test
    public void testputcall(){
        File jsonFile = new File("C://Users//malpraneeth//IdeaProjects//API_ASSIGNMENT//Put.json");
        given().
            baseUri("https://reqres.in/api").
            header("Content-Type", "application/json").
            body(jsonFile).
        when().
           put("/users").
        then().
            statusCode(200).
            body("name",equalTo("Arun")).
            body("job",equalTo("Manager"));


    }

}
