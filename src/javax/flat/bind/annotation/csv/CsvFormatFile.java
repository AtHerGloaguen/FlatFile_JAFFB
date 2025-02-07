/*
 * Creation : 17 mars 2014
 */
package javax.flat.bind.annotation.csv;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * indique le nom de la class parent <br />
 * 
 * @see #name()
 * @author Gloaguen Joel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE, FIELD, METHOD })
public @interface CsvFormatFile {

    /**
     * nom de la classe <br />
     * 
     * @return
     */
    String name();

    String type() default "csv";
    
    /**
	 * indique le caractére de remplissage.<br />
	 * s'il n'est pas indiqué, la valeur par default est l'espace<br />
	 * et la vaieur est prise en compte si la longueur de chaine <bt />
	 * {@link #valuLongueurChaine()}est valorisée
	 * 
	 * 
	 * @return
	 */
	char charSeparateur() default '\u003B';

}
