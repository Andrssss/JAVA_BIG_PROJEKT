import javax.swing.*;
import java.util.ArrayList;

public class Main extends JPanel {
    public static void main(String[] args) {
        new Thread( new Palya_server()).start();



        new Thread( new Jatekos(1)).start();




        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        new Thread( new Jatekos(2)).start();
    }
}
