import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;


public class Jatekos implements Runnable {
    int Hanyadik_jatekos;
    protected Socket clientSocket;
    protected PrintWriter clientWriter;

    static public ArrayList<Barany> baranyok = new ArrayList<Barany>();
    static public ArrayList<Farkas> farkasok = new ArrayList<Farkas>();

    protected boolean jobbra_atmehet;
    protected boolean balra_atmehet;

    Jatekos(int szam) {
        Hanyadik_jatekos = szam;
        switch (szam){
            case 1 :
                jobbra_atmehet = true;
                balra_atmehet = false;
                break;
            case 2 :
                jobbra_atmehet = false;
                balra_atmehet = true;
                break;
            case 3 :  // harmadik lesz kozepen
                jobbra_atmehet = true;
                balra_atmehet = true;
        }


        try{
            this.clientSocket = new Socket("localhost", Palya_server.PORT_NUMBER);
            this.clientWriter = new PrintWriter(clientSocket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }

        if(szam==2 ||szam==1){
            for(int i=0;i<20;i++){
                Barany tmp_bari = new Barany();
                tmp_bari.setJatekosHely(Hanyadik_jatekos);
                baranyok.add(tmp_bari);
            }
            for(int i=0;i<1;i++){
                Farkas tmp_farkas = new Farkas();
                tmp_farkas.setJatekosHely(Hanyadik_jatekos);
                farkasok.add(tmp_farkas);
            }
        }

    }





    /**
     * Szalak futasa.
     */
    public void run() {






    }







    /**
     * SZERVER-nek kuld uzenetet
     * @param line Ami kuld
     * @throws IOException Ha nem tudta elkuldeni
     */
    protected void sendLine(String line) throws IOException {
        clientWriter.print(line + "\r\n");
        clientWriter.flush();
    }
    public static ArrayList<Barany> getBaranyok() {
        return baranyok;
    }
    public static ArrayList<Farkas> getFarkasok() {
        return farkasok;
    }
}
