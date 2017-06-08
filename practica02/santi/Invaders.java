import java.applet.Applet;
import java.awt.*;
import java.io.PrintStream;

public class Invaders extends Applet
    implements Runnable
{

    public Invaders()
    {
        maxPoeng = 0;
        aktiveExplo = 0;
        teller = 0;
        maxAngripKuler = 4;
        oppdatPause = 25;
        venstre = false;
        hoyre = false;
        opp = false;
        ned = false;
        angripVenstre = true;
        gameOver = true;
        pause = false;
        romSkipExplo = false;
        ufoExplo = false;
        ufoPasserer = false;
        aktivtMissil = false;
    }

    public String getAppletInfo()
    {
        return "Invaders, Sondre Skaug Bj\370rnebekk 1999.";
    }

    public void init()
    {
        Graphics g = getGraphics();
        d = size();
        antStjerner = (d.width * d.height) / 1000;
        stjerner = new Point[antStjerner];
        for(int i = 0; i < antStjerner; i++)
            stjerner[i] = new Point((int)(Math.random() * (double)d.width), (int)(Math.random() * (double)d.height));

        angripKuler = new Point[MAXANGRIPKULER];
        eksplosjoner = new Point[MAXEXPLO];
        angripere = new Point[MAXANGRIPERE];
        settoppAngripere();
        romSkip = new Point(d.width / 2, (int)((double)d.height - (double)d.height * 0.10000000000000001D));
        kuler = new Point[MAXKULER];
        ufo = new Point((int)((double)d.width * 0.10000000000000001D), (int)((double)d.height * 0.10000000000000001D));
        font = new Font("Courier", 0, 18);
        g.setFont(font);
        fm = g.getFontMetrics();
        fontBredde = fm.getMaxAdvance();
        fontHoyde = fm.getHeight();
        requestFocus();
    }

    public void start()
    {
        requestFocus();
        if(hovedT == null)
        {
            hovedT = new Thread(this);
            hovedT.start();
        }
    }

    public void stop()
    {
        if(hovedT != null)
        {
            hovedT.stop();
            hovedT = null;
        }
    }

    public void run()
    {
        long forrige = 0L;
        do
        {
            long tid = System.currentTimeMillis();
            long diff = tid - forrige;
            forrige = tid;
            if(diff > (long)oppdatPause)
                diff = oppdatPause;
            repaint();
            try
            {
                Thread.sleep((long)oppdatPause - diff);
            }
            catch(InterruptedException e)
            {
                System.err.println(e);
                break;
            }
            if(!pause)
            {
                bevegStjerner();
                bevegRomSkip();
                bevegKuler();
                bevegAngripKuler();
                bevegAngriper();
                if(ufoPasserer)
                    bevegUfo();
                else
                if(tidUfoExplo > 0)
                {
                    tidUfoExplo--;
                } else
                {
                    ufoExplo = false;
                    ufo.x = 0;
                }
                if(aktivtMissil)
                    bevegMissil();
                sjekkTreff();
                nyeAngripKuler();
                if(tidExplo > 0)
                    tidExplo--;
                else
                    aktiveExplo = 0;
                if(tidRomSkipExplo > 0)
                    tidRomSkipExplo--;
                else
                    romSkipExplo = false;
            }
            if(poeng > maxPoeng)
                maxPoeng = poeng;
            if(aktiveAngripere == 0)
            {
                nyttBrett();
                poeng += 500;
            }
            teller++;
        } while(true);
    }

    private void settoppAngripere()
    {
        int konstant = size().width / 13;
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 6; j++)
                angripere[i + j * 6] = new Point((4 + i) * konstant, (2 + j) * konstant);

        }

        aktiveAngripere = MAXANGRIPERE;
    }

    private void nyttBrett()
    {
        settoppAngripere();
        brett++;
        aktiveKuler = 0;
        aktiveAngripKuler = 0;
        aktiveExplo = 0;
        maxAngripKuler++;
        oppdatPause--;
        if(oppdatPause < 15)
            oppdatPause = 15;
        sjekkEkstraLiv();
    }

    private void nyeAngripKuler()
    {
        for(; aktiveAngripKuler < maxAngripKuler; aktiveAngripKuler++)
        {
            int angripNr = (int)(Math.random() * (double)aktiveAngripere);
            angripKuler[aktiveAngripKuler] = new Point(angripere[angripNr].x, angripere[angripNr].y);
        }

    }

    public boolean keyDown(Event e, int key)
    {
        if(key == 1006)
            venstre = true;
        if(key == 1007)
            hoyre = true;
        if(key == 27)
            gameOver = true;
        if(key == 32 || key == 1004)
            avfyrKule();
        if(key == 112 || key == 80)
            pause = !pause;
        if(key == 1005 || key == 109 || key == 77)
            avfyrMissil();
        if(key == 115 && gameOver)
        {
            nullStillSpill();
            nyttBrett();
            gameOver = false;
        }
        return true;
    }

    public boolean keyUp(Event e, int key)
    {
        if(key == 1006)
            venstre = false;
        if(key == 1007)
            hoyre = false;
        if(key == 1004)
            opp = false;
        if(key == 1005)
            ned = false;
        return true;
    }

    public void paint(Graphics g)
    {
        update(g);
    }

    public void update(Graphics g)
    {
        d = size();
        if(bufferGraphics == null || d.width != bufferDimension.width || d.height != bufferDimension.height)
        {
            bufferDimension = d;
            bufferImage = createImage(d.width, d.height);
            bufferGraphics = bufferImage.getGraphics();
        }
        bufferGraphics.setFont(font);
        bufferGraphics.setColor(Color.black);
        bufferGraphics.fillRect(0, 0, d.width, d.height);
        bufferGraphics.setColor(Color.white);
        for(int i = 0; i < antStjerner; i++)
            bufferGraphics.drawLine(stjerner[i].x, stjerner[i].y, stjerner[i].x, stjerner[i].y);

        bufferGraphics.setColor(Color.white);
        bufferGraphics.drawString("Poeng: " + poeng, fontBredde, fontHoyde);
        bufferGraphics.drawString("Romskip: " + skipIgjen, fontBredde, d.height - fontHoyde);
        String s = "Beste: " + maxPoeng;
        bufferGraphics.drawString(s, d.width - (fontBredde + fm.stringWidth(s)), fontHoyde);
        s = "Brett: " + brett;
        bufferGraphics.drawString(s, d.width - (fontBredde + fm.stringWidth(s)), d.height - fontHoyde);
        s = "M: " + missilIgjen;
        bufferGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height - fontHoyde);
        if(gameOver)
        {
            bufferGraphics.setColor(Color.red);
            s = "I N V A D E R S";
            bufferGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 3);
            s = "av";
            bufferGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 3 + fontHoyde);
            bufferGraphics.setColor(Color.yellow);
            s = "Sondre Skaug Bj\370rnebekk,";
            bufferGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 3 + 2 * fontHoyde);
            s = "sondre@webmagi.no";
            bufferGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 3 + 3 * fontHoyde);
            bufferGraphics.setColor(Color.red);
            s = "Trykk 'S' for \345 starte";
            bufferGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 3 + 5 * fontHoyde);
            s = "Siste poengsum:" + poeng;
            bufferGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 3 + 7 * fontHoyde);
        } else
        {
            if(romSkipExplo)
            {
                Polygon rs = BasicSprite.giRomSkip(romSkip);
                if(teller % 4 == 0)
                    bufferGraphics.setColor(Color.yellow);
                else
                    bufferGraphics.setColor(Color.red);
                for(int i = 0; i < rs.npoints; i++)
                    bufferGraphics.fillPolygon(BasicSprite.giExplo(new Point(rs.xpoints[i], rs.ypoints[i])));

                if(teller % 4 == 0)
                    bufferGraphics.setColor(Color.red);
                else
                    bufferGraphics.setColor(Color.yellow);
                bufferGraphics.setColor(Color.red);
                bufferGraphics.fillPolygon(BasicSprite.giExplo(romSkip));
            } else
            if(treffbar)
            {
                bufferGraphics.setColor(new Color(100, 100, 255));
                bufferGraphics.fillPolygon(BasicSprite.giRomSkip(romSkip));
            } else
            {
                if(startTid % 3 == 0)
                    bufferGraphics.setColor(new Color(50, 50, 128));
                else
                    bufferGraphics.setColor(new Color(100, 100, 255));
                bufferGraphics.fillPolygon(BasicSprite.giRomSkip(romSkip));
                startTid--;
                if(startTid <= 0)
                    treffbar = true;
            }
            bufferGraphics.setColor(Color.red);
            for(int i = 0; i < aktiveKuler; i++)
                bufferGraphics.fillPolygon(BasicSprite.giKule(kuler[i]));

            bufferGraphics.setColor(Color.green);
            for(int i = 0; i < aktiveAngripKuler; i++)
                bufferGraphics.fillPolygon(BasicSprite.giAngripKule(angripKuler[i]));

            bufferGraphics.setColor(Color.yellow);
            for(int i = 0; i < aktiveAngripere; i++)
                bufferGraphics.fillPolygon(BasicSprite.giAngriper(angripere[i]));

            if(tidExplo % 2 == 0)
                bufferGraphics.setColor(Color.red);
            else
                bufferGraphics.setColor(Color.green);
            for(int i = 0; i < aktiveExplo; i++)
                bufferGraphics.fillPolygon(BasicSprite.giExplo(eksplosjoner[i]));

            if(ufoPasserer)
            {
                bufferGraphics.setColor(Color.red);
                bufferGraphics.fillPolygon(BasicSprite.giUfo(ufo));
            }
            if(ufoExplo)
            {
                if(tidUfoExplo % 3 == 0)
                    bufferGraphics.setColor(Color.red);
                else
                    bufferGraphics.setColor(Color.yellow);
                Polygon ufosprite = BasicSprite.giUfo(ufo);
                for(int i = 0; i < ufosprite.npoints; i++)
                    bufferGraphics.fillPolygon(BasicSprite.giExplo(new Point(ufosprite.xpoints[i], ufosprite.ypoints[i])));

            }
            if(aktivtMissil)
            {
                bufferGraphics.setColor(Color.blue);
                bufferGraphics.fillPolygon(BasicSprite.giMissil(missil));
                if(teller % 3 == 0)
                    bufferGraphics.setColor(Color.yellow);
                else
                    bufferGraphics.setColor(Color.red);
                bufferGraphics.fillPolygon(BasicSprite.giExplo(new Point(missil.x, missil.y + 10)));
            }
            if(pause)
            {
                bufferGraphics.setColor(Color.red);
                s = "Pause, trykk 'P' for \345 starte igjen";
                bufferGraphics.drawString(s, (d.width - fm.stringWidth(s)) / 2, d.height / 4);
            }
        }
        g.drawImage(bufferImage, 0, 0, this);
    }

    private void bevegStjerner()
    {
        for(int i = 0; i < stjerner.length; i++)
        {
            if(stjerner[i].y > d.height - 1)
                stjerner[i].y = 0;
            stjerner[i].move(stjerner[i].x, stjerner[i].y + 1);
        }

    }

    private void bevegRomSkip()
    {
        if(venstre)
        {
            boolean utenfor = false;
            if(romSkip.x <= 0)
                utenfor = true;
            if(!utenfor)
                romSkip.x -= 2;
        }
        if(hoyre)
        {
            boolean utenfor = false;
            if(romSkip.x >= d.width)
                utenfor = true;
            if(!utenfor)
                romSkip.x += 2;
        }
    }

    private void bevegKuler()
    {
        for(int i = 0; i < aktiveKuler; i++)
        {
            kuler[i].move(kuler[i].x, kuler[i].y - 2);
            if(kuler[i].y > 0)
                continue;
            aktiveKuler--;
            for(int j = 0; j < aktiveKuler; j++)
                kuler[j] = kuler[j + 1];

        }

    }

    private void bevegAngripKuler()
    {
        for(int i = 0; i < aktiveAngripKuler; i++)
        {
            angripKuler[i].move(angripKuler[i].x, angripKuler[i].y + 2);
            if(angripKuler[i].y < d.height)
                continue;
            aktiveAngripKuler--;
            for(int j = 0; j < aktiveAngripKuler; j++)
                angripKuler[j] = angripKuler[j + 1];

        }

    }

    private void bevegAngriper()
    {
        if(teller % 10 == 0)
        {
            int konstant = size().width / 35;
            int i;
            if(angripVenstre)
                for(i = 0; i < aktiveAngripere; i++)
                    angripere[i].move(angripere[i].x + konstant, angripere[i].y);

            else
                for(i = 0; i < aktiveAngripere; i++)
                    angripere[i].move(angripere[i].x - konstant, angripere[i].y);

            i = 0;
            boolean skiftRetning;
            for(skiftRetning = false; i < aktiveAngripere && !skiftRetning; i++)
            {
                if(angripere[i].x >= 20 && angripere[i].x <= size().width - 10)
                    continue;
                skiftRetning = true;
                for(int j = 0; j < aktiveAngripere; j++)
                    angripere[j].move(angripere[j].x, angripere[j].y + konstant);

            }

            if(skiftRetning)
                angripVenstre = !angripVenstre;
        }
    }

    private void bevegUfo()
    {
        ufo.x++;
        if(ufo.x >= d.width)
        {
            ufo.x = 0;
            ufoPasserer = false;
        }
    }

    private void sjekkTreff()
    {
        for(int i = 0; i < aktiveKuler; i++)
        {
            for(int j = 0; j < aktiveAngripere; j++)
                if(BasicSprite.giAngriper(angripere[j]).inside(kuler[i].x, kuler[i].y))
                    exploAngriper(j, i);

        }

        for(int i = 0; i < aktiveAngripKuler; i++)
        {
            if(!BasicSprite.giRomSkip(romSkip).inside(angripKuler[i].x, angripKuler[i].y))
                continue;
            for(; i < aktiveAngripKuler - 1; i++)
                angripKuler[i] = angripKuler[i + 1];

            aktiveAngripKuler--;
            exploRomSkip();
        }

        for(int i = 0; i < aktiveAngripere; i++)
            if(BasicSprite.giRomSkip(romSkip).inside(angripere[i].x, angripere[i].y) || angripere[i].y > d.height - 10)
            {
                skipIgjen--;
                exploRomSkip();
                nyttBrett();
            }

        if(ufoPasserer)
        {
            for(int i = 0; i < aktiveKuler; i++)
                if(BasicSprite.giUfo(ufo).inside(kuler[i].x, kuler[i].y))
                    exploUfo();

            if(aktivtMissil && BasicSprite.giUfo(ufo).inside(missil.x, missil.y))
                exploUfo();
        }
        if(aktivtMissil)
        {
            Polygon ms = BasicSprite.giMissil(missil);
            for(int i = 0; i < aktiveAngripere; i++)
            {
                for(int j = 0; j < ms.npoints; j++)
                    if(BasicSprite.giAngriper(angripere[i]).inside(ms.xpoints[j], ms.ypoints[j]))
                        exploAngriper(i);

            }

        }
    }

    private void avfyrKule()
    {
        if(aktiveKuler < MAXKULER)
        {
            Polygon rs = BasicSprite.giRomSkip(romSkip);
            kuler[aktiveKuler] = new Point(rs.xpoints[1], rs.ypoints[1]);
            aktiveKuler++;
        }
    }

    private void bevegMissil()
    {
        missil.y -= 4;
        if(missil.y <= 0)
            aktivtMissil = false;
    }

    private void avfyrMissil()
    {
        if(missilIgjen > 0 && !aktivtMissil)
        {
            Polygon rs = BasicSprite.giRomSkip(romSkip);
            missil = new Point(rs.xpoints[1], rs.ypoints[1]);
            aktivtMissil = true;
            missilIgjen--;
        }
    }

    private void exploAngriper(int angIndx, int kuleIndx)
    {
        poeng += 100;
        eksplosjoner[aktiveExplo] = new Point(angripere[angIndx].x, angripere[angIndx].y);
        aktiveExplo++;
        tidExplo = 8;
        for(; angIndx < aktiveAngripere - 1; angIndx++)
            angripere[angIndx] = angripere[angIndx + 1];

        aktiveAngripere--;
        for(; kuleIndx < aktiveKuler - 1; kuleIndx++)
            kuler[kuleIndx] = kuler[kuleIndx + 1];

        aktiveKuler--;
        sjekkEkstraLiv();
        if(poeng % UFOPASSERING == 0)
            ufoPasserer = true;
    }

    private void exploAngriper(int angIndx)
    {
        poeng += 100;
        eksplosjoner[aktiveExplo] = new Point(angripere[angIndx].x, angripere[angIndx].y);
        aktiveExplo++;
        tidExplo = 8;
        for(; angIndx < aktiveAngripere - 1; angIndx++)
            angripere[angIndx] = angripere[angIndx + 1];

        aktiveAngripere--;
        sjekkEkstraLiv();
        if(poeng % UFOPASSERING == 0)
            ufoPasserer = true;
    }

    private void exploRomSkip()
    {
        if(treffbar)
        {
            tidRomSkipExplo = 16;
            startTid = 64;
            treffbar = false;
            romSkipExplo = true;
            if(--skipIgjen <= 0)
                gameOver = true;
            if(skipIgjen < 0)
                skipIgjen = 0;
        }
    }

    private void exploUfo()
    {
        poeng += 1000;
        missilIgjen++;
        ufoExplo = true;
        tidUfoExplo = 8;
        ufoPasserer = false;
        sjekkEkstraLiv();
    }

    private void sjekkEkstraLiv()
    {
        if(poeng >= nesteEkstraLiv)
        {
            skipIgjen++;
            nesteEkstraLiv += EKSTRALIV;
        }
    }

    private void nullStillSpill()
    {
        skipIgjen = STARTSKIP;
        oppdatPause = STARTPAUSE;
        ufoPasserer = false;
        ufo.x = 0;
        missilIgjen = STARTMISSIL;
        aktivtMissil = false;
        treffbar = true;
        poeng = 0;
        brett = 0;
        pause = false;
        aktiveKuler = 0;
        aktiveAngripKuler = 0;
        aktiveAngripere = MAXANGRIPERE;
        aktiveExplo = 0;
        maxAngripKuler = FORSTEBRETTANGRIPKULER - 1;
        nesteEkstraLiv = EKSTRALIV;
    }

    int antStjerner;
    int skipIgjen;
    int poeng;
    int maxPoeng;
    int aktiveKuler;
    int aktiveAngripKuler;
    int aktiveAngripere;
    int aktiveExplo;
    int tidExplo;
    int teller;
    int maxAngripKuler;
    int tidRomSkipExplo;
    int tidUfoExplo;
    int brett;
    int nesteEkstraLiv;
    int oppdatPause;
    int missilIgjen;
    int startTid;
    Point stjerner[];
    Point kuler[];
    Point angripKuler[];
    Point angripere[];
    Point eksplosjoner[];
    Point romSkip;
    Point ufo;
    Point missil;
    static int STARTPAUSE = 35;
    static int MAXKULER = 6;
    static int MAXANGRIPKULER = 50;
    static int MAXANGRIPERE = 36;
    static int MAXEXPLO = 10;
    static int STARTSKIP = 3;
    static int FORSTEBRETTANGRIPKULER = 5;
    static int EKSTRALIV = 10000;
    static int UFOPASSERING = 1900;
    static int STARTMISSIL = 3;
    boolean venstre;
    boolean hoyre;
    boolean opp;
    boolean ned;
    boolean angripVenstre;
    boolean gameOver;
    boolean pause;
    boolean romSkipExplo;
    boolean ufoExplo;
    boolean ufoPasserer;
    boolean aktivtMissil;
    boolean treffbar;
    Dimension d;
    Dimension bufferDimension;
    Image bufferImage;
    Graphics bufferGraphics;
    Font font;
    FontMetrics fm;
    int fontBredde;
    int fontHoyde;
    Thread hovedT;

}
