import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;



// ITT LESZNEK A KÖZÖS DOLGOK A PALYA RAJZOLBAN, MEG A KÜLÖN




public class Palya extends JPanel implements Runnable {
    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;

    protected boolean game_over = false;
    protected static Color palya_szine = new Color(0, 128, 60);

    protected static final int palyameret_x = 500;
    protected static final int palyameret_y = 500;


    protected int hanyadok_jatekos;



    Palya() {
    }

    Palya(Socket clientSocket, int jatekosSzam) throws IOException {

        this.hanyadok_jatekos=jatekosSzam;


        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());
    }


    // todo






    @Override
    public void run() {
        Palya_rajzol p1 = new Palya_rajzol(hanyadok_jatekos);
        new Thread(p1).start();
    }
}
