import controller.Controller;
import model.VendingMachine;
import view.View;

public class App {
    public static void main(String[] args) throws Exception {
        View view = new View();
        VendingMachine vendingMachine = new VendingMachine();
        Controller controller = new Controller(view, vendingMachine);
        controller.run();
     
    }
}
