/**
 * 
 */
package javax.flat.bind.utils.genere;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.flat.bind.annotation.csv.CsvMappingParse;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;

/**
 * 
 */
public class ConstructionClass {
	
	
	public static void extractNewClassFramClass(Class<?> clazz) {
		
	}

	
	
	/**
	 * Construit dynamiquement une classe à partir d'un tableau de noms de champs et d'un nom de classe.
	 * 
	 * @param fieldNames Tableau contenant les noms des champs à inclure dans la classe.
	 * @param name Nom complet de la classe à créer (ex : "com.a.b.nomclasse").
	 * @return La classe générée dynamiquement.
	 */
	public static Class<?> constructClass(String[] fieldNames, String name) {

	    int position = 1; // Initialise la position pour les annotations d'offsets.
	    // Stocke les positions des champs pour les annotations CsvMappingParse
	    Map<String, Integer> offsets = new HashMap<>();

	    // Parcours des noms de champs pour générer les offsets et ignorer "serialVersionUID"
	    for (String string : fieldNames) {
	        if ("serialVersionUID".equals(string)) {
	            continue; // Ignore "serialVersionUID", si présent
	        }
	        offsets.put(string, position++); // Ajoute chaque champ avec sa position correspondante
	    }

	    // Initialise ByteBuddy pour générer une nouvelle classe
	    ByteBuddy byteBuddy = new ByteBuddy();
	    // Crée un constructeur pour la classe, en la nommant et en la faisant hériter d'Object
	    DynamicType.Builder<?> builder = byteBuddy.subclass(Object.class).name(name);

	    // Pour chaque champ, définir les attributs et méthodes (getter/setter)
	    for (String fieldName : fieldNames) {
	        if ("serialVersionUID".equals(fieldName)) {
	            // Si le champ est "serialVersionUID", on le rend public sans annotations
	            builder = builder.defineField("serialVersionUID", long.class, Modifier.PRIVATE | Modifier.STATIC | Modifier.FINAL) 
	            .value(1L);  // Déclare le champ 'serialVersionUID'
	            continue;
	        }

	        // Crée une annotation CsvMappingParse pour chaque champ avec sa position d'offset
	        AnnotationDescription csvMappingParseAnnotation = AnnotationDescription.Builder.ofType(CsvMappingParse.class)
	                .define("offset", offsets.get(fieldName)) // Définit la valeur de l'annotation pour l'offset
	                .build();

	        // Définir le champ, le rendre public, et ajouter des annotations
	        builder = builder
	                .defineField(fieldName, String.class, Modifier.PUBLIC)  // Déclare un champ public de type String
	                .annotateField(csvMappingParseAnnotation)  // Annoter le champ avec CsvMappingParse
	                .defineMethod("set" + capitalize(fieldName), void.class, Modifier.PUBLIC)  // Créer le setter public
	                .withParameters(String.class)  // Le setter prend un paramètre de type String
	                .intercept(FieldAccessor.ofField(fieldName))  // Intercepte pour gérer l'affectation du champ
	                .defineMethod("get" + capitalize(fieldName), String.class, Modifier.PUBLIC)  // Créer le getter public
	                .intercept(FieldAccessor.ofField(fieldName));  // Intercepte pour retourner la valeur du champ
	    }

	    // Génére la classe dynamique et la charge avec le classloader approprié
	    Class<?> dynamicClass = builder.make()
	            .load(ConstructionClass.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
	            .getLoaded();

	    return dynamicClass; // Retourne la classe générée
	}

	/**
	 * Capitalise le premier caractère d'une chaîne.
	 * 
	 * @param str La chaîne à capitaliser.
	 * @return La chaîne avec le premier caractère en majuscule.
	 */
	public static String capitalize(String str) {
	    if (str == null || str.length() == 0) {
	        return str; // Si la chaîne est nulle ou vide, retourne telle quelle.
	    }
	    return str.substring(0, 1).toUpperCase() + str.substring(1); // Capitalise le premier caractère
	}

	
	
}
