import org.junit.Test;

public class TestClass {

    Requests api = new Requests();

    @Test
    public void Tests(){
        api.createBoard("Emre Board");
        api.createCard("FirstCard");
        api.createCard("SecondCard");
        api.updateCard("Update card");
        api.deleteCardFirst();
        api.deleteCardSecond();
        api.deleteBoard();
    }
}
