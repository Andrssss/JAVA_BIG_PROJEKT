import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Allat {
    protected Vectorr hely = new Vectorr();
    protected Color cubeColor ;
    boolean eletben_van;

    void randomMozgas() {
        // Generálunk véletlenszerű irányokat
        int irany_x = ThreadLocalRandom.current().nextInt(-1, 2); // -1, 0, vagy 1 lehet az irány
        int irany_y = ThreadLocalRandom.current().nextInt(-1, 2); // -1, 0, vagy 1 lehet az irány

        // Beállítjuk az új pozíciót a véletlenszerű irányok alapján
        hely.x += irany_x;
        hely.y += irany_y;

        // Ellenőrizzük, hogy a pozíció a pályán belül van-e, és ha nem, akkor korrigáljuk
        hely.x = Math.max(0, Math.min(hely.x, Palya.palyameret_x - 1));
        hely.y = Math.max(0, Math.min(hely.y, Palya.palyameret_y - 1));
    }

    void rajzol(){

    }
    /*void tavolsag(Vector honnan){
        //return hely - honnan;
        hely.minus(honnan);
    }*/
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
    void menekul(){}
}
