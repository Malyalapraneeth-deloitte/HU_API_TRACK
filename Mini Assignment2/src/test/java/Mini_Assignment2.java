import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import java.io.File;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
class Mini_Assignment2 {

    RequestSpecification requestSpecification;
    RequestSpecification requestSpecification2;
    ResponseSpecification responseSpecification;
    ResponseSpecification responseSpecification2;
    @BeforeClass
    public void setup(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://jsonplaceholder.typicode.com").
                addHeader("Content-Type","application/json");
        requestSpecification = RestAssured.with().spec(requestSpecBuilder.build());
        RequestSpecBuilder requestSpecBuilder2 = new RequestSpecBuilder();
        requestSpecBuilder2.setBaseUri("https://reqres.in/api").
                addHeader("Content-Type","application/json");
        requestSpecification2 = RestAssured.with().spec(requestSpecBuilder2.build());
        responseSpecification = RestAssured.expect().
                contentType(ContentType.JSON);
    }

    @Test
    public void getCall(){

        Response RESPONCE=
                given().
                    spec(requestSpecification).
                when().
                   get("/posts").
                then().
                   spec(responseSpecification).statusCode(200).log().status().log().headers().
                extract().response();
        assertThat(RESPONCE.path("[39].userId"), is(equalTo(4)));

        JSONArray array = new JSONArray(RESPONCE.asString());
        int flag = 1;
        for(int i=0;i<array.length();i++)
        {
            Object obj = array.getJSONObject(i).get("title");
            if( !(obj instanceof String) ) {
                flag = 0;
                break;
            }
        }
        assertThat(flag,is(equalTo(1)));
    }

    @Test
    public void putCall(){

        File jsonputData = new File("C:\\Users\\malpraneeth\\IdeaProjects\\API_ASSIGNMENT\\src\\test\\resources\\Assignment2(put).json");
        given().
            spec(requestSpecification2).
            body(jsonputData).
        when().
           put("/users").
        then().
           spec(responseSpecification).statusCode(200).log().status().log().body().log().headers();
    }
}