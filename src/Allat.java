import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Allat {
    protected Vectorr hely = new Vectorr();
    protected Color cubeColor;
    boolean eletben_van;
    int jatekosHely;
    public void setJatekosHely(int ide){
        this.jatekosHely = ide;
    }



    public synchronized String randomMozgas() {
        // Generálunk véletlenszerű irányokat
        int irany_x =6;
        //int irany_x = ThreadLocalRandom.current().nextInt(-1, 2); // -1, 0, vagy 1 lehet az irány
        int irany_y = ThreadLocalRandom.current().nextInt(-1, 2); // -1, 0, vagy 1 lehet az irány

        // Beállítjuk az új pozíciót a véletlenszerű irányok alapján
        int ujX = (int) (this.hely.x + irany_x);
        int ujY = (int) (this.hely.y + irany_y);
        System.out.print (ujX+"   P" +jatekosHely+"        " );

        // Hogy Y ne mehessen ki a palyarol ---------------------------------------------
        if( ujY > 0  && ujY < Palya.palyameret_y-5 ) this.hely.y = ujY;
        if (ujY > Palya.palyameret_x-5 )             this.hely.y = Palya.palyameret_y-5;
        if (ujY < 0 )                                this.hely.y = 0;



        if(!Palya_rajzol.getAtmehetJobbra(this.jatekosHely) && ujX >= Palya.palyameret_x-5 ) this.hely.x = Palya.palyameret_x-5;
        if(!Palya_rajzol.getAtmehetBalra(this.jatekosHely) && ujX <= 0) this.hely.x = 0;
        //frame.setSize(palyameret_x+14, palyameret_y+37);
        if (ujX > 0  && ujX < Palya.palyameret_x ) this.hely.x = ujX;


        if (ujX >= Palya.palyameret_x && Palya_rajzol.getAtmehetJobbra(jatekosHely) ) {

            this.hely.x = 0;
            return "jobbra";
        }

        if (ujX <= 0  && Palya_rajzol.getAtmehetBalra(jatekosHely) ) {
            this.hely.x = Palya.palyameret_x-5;
            return "balra";
        }

        return "marad";
    }



    void meghal(){
        eletben_van = false;
        cubeColor =  new Color(0,0,0);
    }

    Vectorr tavolsag(Vectorr honnan) {
        if (honnan != null) {
            return new Vectorr(honnan.x - this.hely.x, honnan.y - this.hely.y);
        } else {
            throw new IllegalArgumentException("honnan parameter cannot be null");
        }
    }

    float tavolsag_floatban(Vectorr honnan){
        return (float) (honnan.x - this.hely.x + honnan.y - this.hely.y);
    }
}
