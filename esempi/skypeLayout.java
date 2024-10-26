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

import com.skype.*;

public class skypeLayout {
    public static void main(String[] args) throws Exception {
        System.out.println("Client partito");

        //Skype.setDebug(true);
        SkypeClient.setUILanguageByISOCode("en");

        // Pausa di 3 secondi
        Thread.sleep(3*1000);

        SkypeClient.setUILanguageByISOCode("it");


        SkypeClient.setWallPaper( new File("olimpo76.png") );


        SkypeClient.showProfileWindow();

    }

}
