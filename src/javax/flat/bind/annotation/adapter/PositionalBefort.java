/*
 * Creation : 19 mars 2014
 * @author Gloaguen Joel
 */
package javax.flat.bind.annotation.adapter;

import javax.flat.bind.JFFPBException;

/**
 * @author Gloaguen Joel <br />
 *         class qui propose la possibilit� de convertir les valeurs dans le format demand� <br />
 * @see #unparsing
 * @see #parsing
 * @param <String> Type en entrer toujours String
 * @param <ReponceType> en fonction du traitement
 */
@SuppressWarnings("hiding")
public abstract class PositionalBefort {

    /**
     * pour traiter la chaine de caratere avant et mettre au format demander <br />
     * 
     * @param v
     * @return
     * @throws Exception
     */
    public abstract String befort(String v) throws JFFPBException;

}
