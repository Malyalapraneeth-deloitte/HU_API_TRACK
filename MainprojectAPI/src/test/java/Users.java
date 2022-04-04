import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Users {
    @Test(priority = 1)
    public void userRegister() throws IOException {
        for (int i = 1; i <= 5; i++) {
            ExcelData edata = new ExcelData();
            String Name = edata.getString(i, 0);
            String email = edata.getString(i, 1);
            String password = edata.getString(i, 2);
            int age = edata.getAge(i, 3);
            ReadingExcelData data = new ReadingExcelData(Name, email, password, age);
            given().
                    body(data).
                    contentType(ContentType.JSON).
                    when().
                    post("https://api-nodejs-todolist.herokuapp.com/user/register").
                    then().
                    log().body().
                    statusCode(HttpStatus.SC_CREATED);
        }
    }
    @Test(priority = 2)
    public void userLogin() throws IOException {
        ExcelData ed = new ExcelData();
        String email = ed.getString(1, 1);
        String password = ed.getString(1, 2);
        ReadingExcelData data = new ReadingExcelData(email, password);
        Response response = given().
                body(data).
                contentType(ContentType.JSON).
                when().
                post("https://api-nodejs-todolist.herokuapp.com/user/login").
                then().
                log().body().
                statusCode(HttpStatus.SC_OK).extract().response();
        JSONObject jsonObject = new JSONObject(response.asString());
        Object obj = jsonObject.getJSONObject("user").get("email");
        assertThat(obj, is(email));
        Object ObjToken = jsonObject.get("token");
        ExcelData Data = new ExcelData();
        Data.writeToken(ObjToken);
    }
}