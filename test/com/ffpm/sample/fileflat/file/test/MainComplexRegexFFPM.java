/**
 * 
 */
package com.ffpm.sample.fileflat.file.test;

import java.io.File;

import javax.flat.bind.JAFFBContext;
import javax.flat.bind.Marshaller;
import javax.flat.bind.Unmarshaller;

import com.ffpm.sample.fileflat.file.readcomplex.FileRootForFFPMComplex;

/**
 * @author Gloax29
 */
public class MainComplexRegexFFPM {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        /**
         * lecture d'un fichier pour cree un object
         */

        JAFFBContext contex = JAFFBContext.newInstance(FileRootForFFPMComplex.class);
        Unmarshaller unmarshaller = contex.createUnmarshaller();

        FileRootForFFPMComplex ffpmComplex = (FileRootForFFPMComplex) unmarshaller
                .unmarshal(new File("test/resources/TestComplexRegexFileflat1.txt"));
        for(int i = 0; i < 22; i++) {

        ffpmComplex.getLigneA().addAll( ffpmComplex.getLigneA());
        ffpmComplex.getLigneB().addAll( ffpmComplex.getLigneB());
        ffpmComplex.getLigneC().addAll( ffpmComplex.getLigneC());
        ffpmComplex.getLigneD().addAll( ffpmComplex.getLigneD());
        ffpmComplex.getLigneE().addAll( ffpmComplex.getLigneE());
        }
        //System.out.println(ffpmComplex.toString());
        
        Marshaller marshall = contex.createMarshaller();
        marshall.marshal(ffpmComplex, new File("test/resources/TestComplexRegexFileflat2.txt"));

    }

}
