/*******************************************************************************
 * Copyright (c) 2007 Matteo Baccan <matteo@baccan.it>
 *
 * This program and the accompanying materials are made available under the
 * terms of the Common Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors: Matteo Baccan - initial implementation
 ******************************************************************************/
import java.io.*;

import com.skype.MenuItem;
import com.skype.MenuItemClickEvent;
import com.skype.MenuItemListener;
import com.skype.Skype;
import com.skype.SkypeClient;
import com.skype.SkypeException;

public class skypeMenuItem extends Thread {
    public static void main(String[] args) throws Exception {
        Skype.setDeamon(false);
        final MenuItem item = SkypeClient.addMenuItem(MenuItem.Context.TOOLS,
                                                      "Chuck Norris",
                                                      "Abilita il mood Chuck Norris",
                                                      new File("chuck.png"),
                                                      true, null, true);

        item.addMenuItemListener(new MenuItemListener() {
            public void menuItemClicked(MenuItemClickEvent event) throws SkypeException {
                bChuck = !bChuck;
                //System.out.println("Chuck Norris: "+bChuck);
                if( bChuck ) SkypeClient.setWallPaper(new File("chuck.png"));
                else         SkypeClient.setWallPaper(null);

                // Rimuove la voce di menu
                //event.getMenuItem().dispose();
            }
        });


    }

    public void run() {
       try {
          ChuckNorris gr = new ChuckNorris();
          while( true ) {
              // Pausa di 60 secondi
              try {
                 sleep(60 *1000);
              } catch (Throwable e) {}

              // Reimposta il mood
              if( bChuck ) {
                 String cNorris = gr.getNorris();
                 Skype.getProfile().setMoodMessage( cNorris );
              }
          }
       } catch (Throwable e) {}
    }

    static boolean bChuck = false;
    static {
        skypeMenuItem th = new skypeMenuItem();
        th.start();
    }

}
