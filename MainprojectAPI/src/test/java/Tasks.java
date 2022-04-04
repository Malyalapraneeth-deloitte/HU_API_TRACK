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


public class Tasks {

    @Test(priority = 1)
    public void add_tasks() throws IOException {
        ExcelData edata=new ExcelData();
        String token=edata.getToken(0,1,4);
        for(int i=1;i<=20;i++){
            String task=edata.getToken(1,i,0);
            ReadingExcelData dt=new ReadingExcelData(task);
            Response Response = given().
                    body(dt).
                    headers(
                            "Authorization",
                            "Bearer " + token,
                            "Content-Type",
                            ContentType.JSON,
                            "Accept",
                            ContentType.JSON).
                    when().
                    post("https://api-nodejs-todolist.herokuapp.com/task").
                    then().
                    log().body().
                    statusCode(HttpStatus.SC_CREATED).extract().response();
        }
    }

    @Test(priority = 2)
    public void pagination() throws IOException {
        ExcelData edata=new ExcelData();
        String token=edata.getToken(0,1,4);
        for(int i=0;i<3;i++) {
            int k;
            if(i==0){
                k=2;
            }
            else if(i==1){
                k=5;
            }
            else
                k=10;
            Response Response = given().param("limit", k).
                    headers(
                            "Authorization",
                            "Bearer " + token,
                            "Content-Type",
                            ContentType.JSON,
                            "Accept",
                            ContentType.JSON).
                    when().
                    get("https://api-nodejs-todolist.herokuapp.com/task").
                    then().
                    log().body().
                    statusCode(HttpStatus.SC_OK).extract().response();
            JSONObject jsonObject = new JSONObject(Response.asString());
            Object ObjToken = jsonObject.get("count");
            assertThat(ObjToken, is(k));
        }

    }

}
