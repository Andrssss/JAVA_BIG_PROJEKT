import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Palya_rajzol extends Palya implements Runnable {
    private int jatekosSzam;

    Palya_rajzol(int jatekosSzam) {
        this.jatekosSzam = jatekosSzam;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int tmp = hanyadok_jatekos;
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
                switch (hanyadok_jatekos) {
                    case 0:
                        frame.setLocation(1500, 100);
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
                hanyadok_jatekos++;
                frame.setVisible(true);

                // MOZGATAS ----------------------------------------------------
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        for (Barany b : baranyok) {
                            // Csak a saját bárányait mozgatja
                            if (b.jatekosHely == jatekosSzam) {
                                b.menekul();
                                //b.randomMozgas();
                            }
                        }
                        for (Farkas b : farkasok) {
                            // Csak a saját bárányait mozgatja
                            if (b.jatekosHely == jatekosSzam) {
                                //b.uldozBaranyok();
                                b.randomMozgas();
                            }
                        }

                        panel.repaint(); // Panel újrarajzolása
                    }
                }, 0, 100); // 100 ms-es időközönként frissítsük a pozíciójukat
            }
        });
    }
}
