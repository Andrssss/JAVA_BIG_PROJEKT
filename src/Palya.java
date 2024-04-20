import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Palya extends JPanel implements Runnable {
    protected Socket clientSocket;
    protected BufferedReader clientReader;
    protected PrintWriter clientWriter;

    protected boolean game_over = false;
    protected static Color palya_szine = new Color(0, 128, 60);

    protected static final int palyameret_x = 500;
    protected static final int palyameret_y = 500;
    public ArrayList<Barany> baranyok = new ArrayList<Barany>();
    public ArrayList<Farkas> farkasok = new ArrayList<Farkas>();

    protected static int hanyadok_jatekos ;

    protected boolean atlehet_menni_jobbra;
    protected boolean atlehet_menni_balra;

    Palya() {
    }

    Palya(Socket clientSocket, int jatekosSzam) throws IOException {
        if (jatekosSzam == 0) {
            atlehet_menni_jobbra = true;
            atlehet_menni_balra = false;
        } else if (jatekosSzam == 1) {
            atlehet_menni_jobbra = false;
            atlehet_menni_balra = true;
        }
        this.hanyadok_jatekos = jatekosSzam;
        this.baranyok = Jatekos.getBaranyok();
        this.farkasok = Jatekos.getFarkasok();

        this.clientSocket = clientSocket;
        this.clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.clientWriter = new PrintWriter(clientSocket.getOutputStream());
    }




    @Override
    public void run() {
        Palya_rajzol p1 = new Palya_rajzol(hanyadok_jatekos);
        new Thread(p1).start();
    }
}
