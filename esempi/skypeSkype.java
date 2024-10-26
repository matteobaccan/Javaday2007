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

public class skypeSkype {
    public static void main(String[] args) throws Exception {
        System.out.println("Client partito");

        // Visualizzo
        System.out.println( Skype.LIBRARY_VERSION  );
        System.out.println( Skype.getInstalledPath() );
        System.out.println( Skype.getAudioInputDevice()  );
        System.out.println( Skype.getAudioOutputDevice()  );
        System.out.println( Skype.getVersion() );
        System.out.println( Skype.getVideoDevice() );

    }

}
