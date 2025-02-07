/*
 * Creation : 1 avr. 2014
 */

package javax.flat.bind.annotation.csv;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gloaguen Joel
 * 
 * <p>
 * Annotation qui sert à la description de la classe principale pour le mapping.
 * elle comporte les valeurs :<br/>
 *
 * </p>
 * @see #valuDebutLigne
 * @see #valuNumbLigne
 * @see #valuLongueurChaine
 * @see #name
 * @see #list
 * @see #charcatereRepli
 * @see #expression
 * @see #theclass
 * @see #NOEXPRES
 * 
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD })
public @interface CsvMappingParseRootElem {

	/**
	 * Constante pour l utilisation de la <br />
	 * reconnaisance des chaines de caractéres par expression réguliére.
	 * <br />
	 * elle signifie que c'est cette variable qui sera utilisée  pour l'injection br /> 
	 * par defaut. si la chaéne ne matche avec aucune expression
	 */
	public static final String NOEXPRES = "noExpres"; 
	/**
	 * détermine à partir de quelle ligne commence cet élément.<br />
	 *  les éléments sont classés selon cet ordre.
	 * 
	 * @return
	 */
	int valuDebutLigne();

	/**
	 * indique le nombre de lignes concernées par cet élément,<br />
	 *  si non indiqué.
	 * il sera le dernier ou le seul conserné.
	 * 
	 * <br />cela peut concerner les fichiers avec un nombre de lignes<br/>
	 * fixe.
	 * 
	 * @return
	 */
	
	int valuNumbLigne() default -1;

	
	/**
	 * nom de l'attribu <br />
	 *  
	 * @return
	 */
	
	String name();

	/**
	 * indique si l'attribu est un List.<br/>
	 * valeur par defaut est false<br />
	 * si la valeur est à true, indique le nom de la classe <br />
	 * au paramétre {@link #theclass()}.
	 * <p> 
	 * Pour les Listes le Getteur doit tester la nullité <br />
	 * et dans ce cas l'instancier.
	 * </p>
	 * 
	 * @return
	 */
	boolean list() default true;

	/**
	 * indique le caractere de remplisage.<br />
	 * s"il n'est pas indiqué, la valeur par default est l'espace<br />
	 * et la vaieur est prise en compte si la longueur de chaine <bt />
	 * {@link #valuLongueurChaine()}est valorisée
	 * 
	 * 
	 * @return
	 */
	char charRemplissage() default '\u0000';

	
	
	/**
	 * 
	 * expression d'appartenance.<br/>
	 *  
	 * 
	 * <p>si cette valeur est utilisée. <br>cela indique au parseur une méthode de
	 * fonctionnement différente il n'y a plus d'ordre dans la classe principale
	 * #PositionalMappingParseRootElem</p>
	 * 
	 * <p>si la ligne matche avec l'expression, celle si est injectée<br />
	 *  dans la variable d'appartenance.<br />
	 * . si c'est une liste l'objet est ajouté.
	 * </p>
	 * 
	 * <p>pour qu'une variable recupére toutes les lignes<br />
	 *  qui ne matche avec aucune expression la valeur {@link #NOEXPRES}<br />
	 *  est à utiliser, ou celle-ci ne sera pas traitée.
	 * 
	 * </p>
	 * <br/> Attantion valeur par defaut est -X
	 * 
	 * @return
	 */
	String expression() default "-X";

	/**
	 * nom de la classe de l'élément. si c'est une liste, il faut l'indiquer par
	 * defaut DEFAULT.class si list a false
	 * 
	 * @return
	 */
	Class<?> theclass() default DEFAULT.class;

	static final class DEFAULT {
	}

}
