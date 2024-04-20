import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Barany extends Allat {

    Barany() {
        eletben_van = true;
        hely.random();
        cubeColor = new Color(255, 255, 255);
    }
    int jatekosHely;
    public void setJatekosHely(int ide){
        this.jatekosHely = ide;
    }

    @Override
    void meghal() {
        super.meghal();
    }


    public void menekul() {
        // Szomszédos bárányok keresése
        ArrayList<Barany> szomszedok = new ArrayList<Barany>();
        float szomszed_tavolsag = 5; // Szomszédsági távolság

        /*for (Barany b : Palya.baranyok) {
            if (b != this && tavolsag_floatban(b.hely) < szomszed_tavolsag) {
                szomszedok.add(b);
            }
        }*/

        // Vezető bárány kiválasztása (opcionális)
        Barany vezeto = this; // Alapértelmezett vezető: önmaga
        for (Barany b : szomszedok) {
            // Vezető kiválasztási feltétel (pl. legnagyobb tapasztalat)
            //if (b.tapasztalat > vezeto.tapasztalat) {
            //  vezeto = b;
            //}
        }

        // Legközelebbi farkas kiszemelése a csoport számára
        float minTav = Float.MAX_VALUE; // "Végtelen" távolság
        Farkas o_uldozi = null;

        /*for (Farkas farkas : Palya.farkasok) {
            float tav = tavolsag_floatban(farkas.hely);
            if (tav < minTav) {
                o_uldozi = farkas;
                minTav = tav;
            }
        }*/



        // Menekülési irány kiszámítása a vezető alapján
        Vectorr menekulesIrany = new Vectorr();
        if (o_uldozi != null && vezeto != null) {
            float vBarany = 3; // Sebesség

            // Irány a legközelebbi farkastól
            Vectorr farkasIrany = new Vectorr(o_uldozi.hely.x - vezeto.hely.x, o_uldozi.hely.y - vezeto.hely.y);

            // Csapatkohézió (vonzerő a szomszédok felé)
            Vectorr csoportIrany = new Vectorr();
            for (Barany b : szomszedok) {
                csoportIrany.plusEquals(new Vectorr(b.hely.x - vezeto.hely.x, b.hely.y - vezeto.hely.y));
            }

            // Kombinált menekülési irány
            menekulesIrany = farkasIrany.minus(csoportIrany).normalizalt();
            menekulesIrany.szammal_szorzas(vBarany);
        }





        // Új pozíció számítása és korlátozása
        int ujX =  (int)(hely.x + menekulesIrany.x);
        int ujY =  (int)(hely.y + menekulesIrany.y);

        // RANDOOM MOZGAS -----------------------------
        //Random random = new Random();
        //ujX += random.nextInt(5) + 1;
        //ujY += random.nextInt(5) + 1;
        // --------------------------------------------

        // Bárány pozíció frissítése
        //frame.setSize(palyameret_x+14, palyameret_y+37);
        if (ujX > 0  && ujX < Palya.palyameret_x-5 ) this.hely.x = ujX;


        // A BARANY NEM TUDJA, HOGY HANYADIK PLAYERE
        // DE A PALYA SZELEIT TUDNI FOGJA. ÉS KÜLÖNBÖZŐ PLAYERHEZ, MAS PALYA
       // if (ujX >=  Palya.palyameret_x-5 &&  )
        //if( ujX > 0                      && player = 1 )
        //if( ujX > 0                      && player = 2 )
        //if (ujX >=  Palya.palyameret_x-5 && player = 2 )



        if( ujY > 0  && ujY < Palya.palyameret_y-5 ) this.hely.y = ujY;
        if (ujY > Palya.palyameret_x-5 )             this.hely.y = Palya.palyameret_y-5;





    }






    /*public void menekul(ArrayList<Farkas> farkasok) {
        // Legközelebbi farkas keresése
        float minTav = Float.MAX_VALUE; // "Végtelen" távolság
        Farkas mitol = null;
        for (Farkas farkas : farkasok) {
            Vector tavV = farkas.tavolsag(hely);
            float tav = tavV.hosszNegyzet();
            if (tav < minTav) {
                mitol = farkas;
                minTav = tav;
            }
        }

        // Legközelebbi farkastól elfele mozog
        if (mitol != null) {
            Vector mozg = mitol.tavolsag(hely);
            hely.plusEquals(mozg.normalizalt().szammal_szorzas(vBarany).plusEquals(emozg));
            emozg = mozg.normalizalt().szammal_szorzas(0.5f); // a baranyok mozgékonyabbak, mert lendületvektor felét adja hozzá
        }

        // Ha túlmegy, átugrik a rajzvászon túloldalára
        if (hely.x < 0) hely.x += Palya.palyameret_x;
        else if (hely.x > Palya.palyameret_x) hely.x -= Palya.palyameret_x;
        if (hely.y < 0) hely.y += Palya.palyameret_y;
        else if (hely.y > Palya.palyameret_y) hely.y -= Palya.palyameret_y;
        rajzol();
    }*/
}
