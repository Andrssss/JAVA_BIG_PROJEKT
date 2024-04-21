import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



/// ITT LESZNEK AZOK A DOLGOK AMIKET KÜLÖN SZERETNÉNK KEZELNI
public class Palya_rajzol extends Palya implements Runnable {
    protected boolean atlehet_menni_jobbra(){
        if (jatekosSzam == 1) return true;
        else if (jatekosSzam == 2) return false;
        else {
            System.out.println("atlehet_menni_jobbra hibas bemeneti ertek :" + jatekosSzam );
        }
        return false;
    }
    protected boolean atlehet_menni_balra(){
        if (jatekosSzam == 1) return false;
        else if (jatekosSzam == 2) return true;
        else {
            System.out.println("atlehet_menni_jobbra hibas bemeneti ertek :" + jatekosSzam );
        }
        return false;
    }

    public ArrayList<Barany> baranyok;
    public ArrayList<Farkas> farkasok;
    private int jatekosSzam;

    Palya_rajzol(int jatekosSzam) {



        this.jatekosSzam = jatekosSzam;
        this.baranyok = Jatekos.getBaranyok();
        this.farkasok = Jatekos.getFarkasok();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int tmp = jatekosSzam;
                ArrayList<Barany> baranyok = Jatekos.getBaranyok(); // vektor lekérése a Játékos osztályból
                ArrayList<Farkas> farkasok = Jatekos.getFarkasok(); // vektor lekérése a Játékos osztályból
                JFrame frame = new JFrame("Palya" + tmp);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel panel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g;

                        // PALYA RAJZOLÁSA -----------------------------------------
                        g2d.setColor(palya_szine);
                        g2d.fillRect(0, 0, 500, 500); // A négyzet bal felső sarkának koordinátái és szélessége, magassága

                        // BARANY RAJZOLÁSA -----------------------------------------
                        g2d.setColor(baranyok.get(0).cubeColor);
                        for (Barany b : baranyok) {
                            // Csak a saját bárányait rajzolja ki
                            if (b.jatekosHely == jatekosSzam) {
                                g2d.fillRect((int) b.hely.x, (int) b.hely.y, 5, 5);
                            }
                        }
                        g2d.setColor(farkasok.get(0).cubeColor);
                        for (Farkas b : farkasok) {
                            // Csak a saját bárányait rajzolja ki
                            if (b.jatekosHely == jatekosSzam) {
                                g2d.fillRect((int) b.hely.x, (int) b.hely.y, 5, 5);
                            }
                        }
                    }
                };
                // frame nyitas ------------------------
                frame.add(panel);
                frame.setSize(palyameret_x + 14, palyameret_y + 37);
                frame.setLocationRelativeTo(null);
                switch (jatekosSzam) {
                    case 0:
                        frame.setLocation(0, 100);
                        break;
                    case 1:
                        frame.setLocation(100, 100);
                        break;
                    case 2:
                        frame.setLocation(700, 100);
                        break;
                    default:
                        frame.setLocationRelativeTo(null);
                }
                //hanyadok_jatekos++;
                frame.setVisible(true);

                // MOZGATAS ----------------------------------------------------
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        for (Barany b : baranyok) {

                            // Csak a saját bárányait mozgatja
                            if (b.jatekosHely == jatekosSzam) {
                                //String eredmeny = b.menekul();
                                String eredmeny = b.randomMozgas();

                                System.out.println("P" + b.jatekosHely + " "+atlehet_menni_balra() + " String : " +eredmeny);

                                if(!eredmeny.equals("sima")){
                                    if(eredmeny.equals("jobbra")){
                                        //System.out.println("Jobbra P" +b.jatekosHely );
                                        if (b.jatekosHely==1) {
                                            b.setJatekosHely(2);
                                        }
                                        else if (b.jatekosHely==2) jatekosSzam=3;
                                        else System.out.println("Valaki jobbra akar bemmi. De nem tud.");
                                    }
                                    else if(eredmeny.equals("balra")){
                                        if (b.jatekosHely==2) b.setJatekosHely(1);
                                        else if (b.jatekosHely==3) b.setJatekosHely(3);
                                        else System.out.println("Valaki jobbra akar bemmi. De nem tud.");
                                    }
                                }

                                //b.randomMozgas();
                            }
                        }
                        for (Farkas b : farkasok) {
                            // Csak a saját bárányait mozgatja
                            // Csak a saját bárányait mozgatja
                            if (b.jatekosHely == jatekosSzam) {
                                //String eredmeny = b.menekul();
                                String eredmeny = b.randomMozgas();

                                System.out.println("P" + b.jatekosHely + " "+atlehet_menni_balra() + " String : " +eredmeny);

                                if(!eredmeny.equals("sima")){
                                    if(eredmeny.equals("jobbra")){
                                        //System.out.println("Jobbra P" +b.jatekosHely );
                                        if (b.jatekosHely==1) {
                                            b.setJatekosHely(2);
                                        }
                                        else if (b.jatekosHely==2) jatekosSzam=3;
                                        else System.out.println("Valaki jobbra akar bemmi. De nem tud.");
                                    }
                                    else if(eredmeny.equals("balra")){
                                        if (b.jatekosHely==2) b.setJatekosHely(1);
                                        else if (b.jatekosHely==3) b.setJatekosHely(3);
                                        else System.out.println("Valaki jobbra akar bemmi. De nem tud.");
                                    }
                                }

                                //b.randomMozgas();
                            }
                        }

                        panel.repaint(); // Panel újrarajzolása
                    }
                }, 0, 100); // 100 ms-es időközönként frissítsük a pozíciójukat
            }
        });
    }


    public static boolean getAtmehetJobbra(int ez_a_jatekos){
        if(ez_a_jatekos == 1) return true;
        if(ez_a_jatekos == 2) return false;
        if(ez_a_jatekos == 3) return true;
        return false;
    }
    public static boolean getAtmehetBalra(int ez_a_jatekos) {
        if(ez_a_jatekos == 1) return false;
        if(ez_a_jatekos == 2) return true;
        if(ez_a_jatekos == 3) return true;
        return false;
    }
}
