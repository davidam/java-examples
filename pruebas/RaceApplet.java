/*
 * Copyright (c) 1995, 1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies. Please refer to the file "copyright.html"
 * for further important copyright and licensing information.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
import java.awt.*;

public class RaceApplet extends java.applet.Applet implements Runnable {

    final static int NUMRUNNERS = 2;
    final static int SPACING = 20;

    Runner[] runners = new Runner[NUMRUNNERS];

    Thread updateThread = null;

    public void init() {
        String raceType = getParameter("type");
        for (int i = 0; i < NUMRUNNERS; i++) {
            runners[i] = new Runner();
            if (raceType.compareTo("unfair") == 0)
                    runners[i].setPriority(i+2);
            else
                    runners[i].setPriority(2);
        }
        if (updateThread == null) {
            updateThread = new Thread(this, "Thread Race");
            updateThread.setPriority(NUMRUNNERS+2);
        }
    }

    public boolean mouseDown(java.awt.Event evt, int x, int y) {
        if (!updateThread.isAlive())
            updateThread.start();
        for (int i = 0; i < NUMRUNNERS; i++) {
            if (!runners[i].isAlive())
                runners[i].start();
        }
        return true;
    }

    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, size().width, size().height);
        g.setColor(Color.black);
        for (int i = 0; i < NUMRUNNERS; i++) {
            int pri = runners[i].getPriority();
            g.drawString(new Integer(pri).toString(), 0, (i+1)*SPACING);
        }
        update(g);
    }

    public void update(Graphics g) {
        for (int i = 0; i < NUMRUNNERS; i++) {
            g.drawLine(SPACING, (i+1)*SPACING, SPACING + (runners[i].tick)/1000, (i+1)*SPACING);
        }
    }

    public void run() {
        while (Thread.currentThread() == updateThread) {
            repaint();
            try {
                updateThread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }    

    public void stop() {
        for (int i = 0; i < NUMRUNNERS; i++) {
            if (runners[i].isAlive()) {
                runners[i] = null;
            }
        }
        if (updateThread.isAlive()) {
            updateThread = null;
        }
    }
}
