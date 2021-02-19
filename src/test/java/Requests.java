
import io.restassured.http.Method;
import org.junit.Assert;
import io.restassured.RestAssured;
import java.util.ArrayList;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class Requests extends Methods {

    public String key = "c0d02bcc2b39ca7b9c05152fc6bc515e" ;
    public String token = "94c72499c6e290901076f47e2dea41d52cc7e55ee577c2a7fdbbe71f434602c8";

    public String bid="";
    public ArrayList ListCardid = new ArrayList();

    public void createBoard(String name){
        //Specify the base URI
        RestAssured.baseURI = "https://api.trello.com/1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();
        request.put("key", key);
        request.put("token",token);
        request.put("name", name);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(request.toString());

        //Response object
        Response response = httpRequest.request(Method.POST,"/boards");

        this.bid = response.jsonPath().getString("id");

        int status = response.getStatusCode();
        Assert.assertEquals(status,200);

    }

    public void createCard(String cardName){
        String list = random(this.bid);

        RestAssured.baseURI = "https://api.trello.com/1";
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();
        request.put("key", key);
        request.put("token",token);
        request.put("name", cardName);
        request.put("idList", list);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(request.toString());

        Response response = httpRequest.request(Method.POST,"/cards");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);

        this.ListCardid.add(response.jsonPath().getString("id"));
    }

    public void updateCard(String name){

        String cardID = (String) this.ListCardid.get(0);
        //Specify the base URI
        RestAssured.baseURI = "https://api.trello.com/1";
        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("key", key);
        requestParams.put("token",token);
        requestParams.put("name", name);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(requestParams.toString());

        //Response object
        Response response = httpRequest.request(Method.PUT,"/cards/" + cardID);

        int statusCode = response.getStatusCode();
        String updatedName = response.jsonPath().getString("name");


        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(updatedName,name);
    }

    public void deleteCardFirst(){
        String cardID = (String) this.ListCardid.get(0);

        RestAssured.baseURI = "https://api.trello.com/1";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();
        request.put("key", key);
        request.put("token",token);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(request.toString());

        Response response = httpRequest.request(Method.DELETE,"/cards/" + cardID);

        int status = response.getStatusCode();

        System.out.println(status);
        Assert.assertEquals(status,200);
    }

    public void deleteBoard(){

        RestAssured.baseURI = "https://api.trello.com/1";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request = new JSONObject();
        request.put("key", key);
        request.put("token",token);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(request.toString());

        Response response = httpRequest.request(Method.DELETE,"/boards/" + bid);

        int status = response.getStatusCode();

        Assert.assertEquals(status,200);
    }

    public void deleteCardSecond(){
        String card = (String) this.ListCardid.get(1);
        RestAssured.baseURI = "https://api.trello.com/1";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject request= new JSONObject();
        request.put("key", key);
        request.put("token",token);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(request.toString());

        Response response = httpRequest.request(Method.DELETE,"/cards/" + card);

        int status = response.getStatusCode();

        Assert.assertEquals(status,200);
    }

}
