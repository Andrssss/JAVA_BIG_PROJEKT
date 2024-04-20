import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Palya_server implements Runnable{
    protected ServerSocket serverSocket;
    protected static final int PORT_NUMBER = 19942;
    private int hanyadik_palya ;





    /**
     * KONSTUKTOR
     */
    public Palya_server()  {
        hanyadik_palya= 0;
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Close socket
     */
    public void close() {
        try{
            serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Parhuzamosan futtathato programresz
     */
    public void run() {
        try {
            while (! Thread.currentThread().isInterrupted() ) {
                //System.out.println("To shut down shop : --close--, To open up : --open-- (def:open)");
                // EZT IRANA KI MINDIG AMIKOR UJ SOCKETET NYIT



                if(serverSocket != null){

                    Socket clientSocket = serverSocket.accept(); // itt elfogadja a kérést és nyit egy socketet
                    System.out.println("SZERVER      - UJ SOCKET NYITAS A : " + clientSocket.getPort()+" -AL" );
                    try {
                        if(hanyadik_palya == 0 ){
                            Palya palyaInstance1 = new Palya(clientSocket,1);
                            Thread palyaThread1 = new Thread(palyaInstance1);
                            palyaThread1.start();
                            hanyadik_palya++;
                        }
                        else if(hanyadik_palya == 1 ){
                            Palya palyaInstance2 = new Palya(clientSocket,2);
                            Thread palyaThread2 = new Thread(palyaInstance2);
                            palyaThread2.start();
                        }


                    } catch (IOException e) {
                        System.err.println("Failed to communicate with PALYA!");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Accept failed!");
        }
        try {
            serverSocket.close();
            System.out.println("A Szerver leáll");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("A Szerver leált");
    }





}
