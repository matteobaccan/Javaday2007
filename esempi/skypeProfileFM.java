/*******************************************************************************
 * Copyright (c) 2007 Matteo Baccan <matteo@baccan.it>
 *
 * This program and the accompanying materials are made available under the
 * terms of the Common Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors: Matteo Baccan - initial implementation
 ******************************************************************************/

import com.skype.*;

import java.text.SimpleDateFormat;

public class skypeProfileFM {
    public static void main(String[] args) throws Exception {
        System.out.println("Client partito");

        //Skype.setDebug(true);
        Profile profile = Skype.getProfile();

        // Visualizzo
        if( args[0]==null || args[0].equalsIgnoreCase("MALE") ){
            System.out.println("Imposto Uomo");
            profile.setSex( Profile.Sex.MALE );
        } else {
            System.out.println("Imposto Donna");
            profile.setSex( Profile.Sex.FEMALE );
        }

        System.exit(1);
    }

}
