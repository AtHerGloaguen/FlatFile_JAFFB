package javax.flat.bind.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.flat.bind.JFFPBException;
import javax.flat.bind.api.FieldCsv;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe ExtractHeadCsv utilisée pour extraire et générer les en-têtes (headers)
 * d'un fichier CSV à partir des annotations des champs d'une classe donnée.
 */
@NoArgsConstructor

public class ExtractHeadCsv {
    // La classe à partir de laquelle extraire les champs pour générer l'en-tête
    private Class<?> targetClass;

    // Le séparateur utilisé pour les colonnes dans le CSV (ex: ";", ",")
    @Setter private String columnSeparator = ";" ;

    /**
     * Constructeur pour initialiser la classe cible et le séparateur de colonnes.
     * 
     * @param targetClass       La classe cible à analyser pour générer l'en-tête CSV
     * @param columnSeparator   Le séparateur de colonnes à utiliser dans le CSV
     */
    public ExtractHeadCsv(Class<?> targetClass, String columnSeparator) {
        this.targetClass = targetClass;
        this.columnSeparator = columnSeparator;
    }

    /**
     * Retourne l'en-tête CSV basé sur les champs de la classe.
     * 
     * @return String représentant l'en-tête du fichier CSV
     * @throws JFFPBException 
     */
    public String getHeaderForClass() throws JFFPBException {
        return this.extractFieldsAsHeader();
    }

    /**
     * Permet de changer la classe cible et le séparateur après l'initialisation.
     * 
     * @param targetClass       Nouvelle classe cible pour l'extraction des en-têtes
     * @param columnSeparator   Nouveau séparateur de colonnes pour le CSV
     */
    public void setNewValueHead(Class<?> targetClass, String columnSeparator) {
        this.targetClass = targetClass;
        this.columnSeparator = columnSeparator;
    }
    
    /**
     * Permet de changer la classe cible .
     * 
     * @param targetClass       Nouvelle classe cible pour l'extraction des en-têtes
     * @param columnSeparator   Nouveau séparateur de colonnes pour le CSV
     */
    public void setNewValueHead(Class<?> targetClass) {
    	setNewValueHead(targetClass,this.columnSeparator);
    }

    /**
     * Extrait les champs de la classe et génère une chaîne d'en-têtes au format CSV.
     * 
     * @return String contenant les noms des champs sous forme d'en-tête CSV
     * @throws JFFPBException 
     */
    public static String[] extractNameFromClass(Class<?> targetClass) throws JFFPBException {
    	
    	Map<Integer, Field> csvFieldMap = new HashMap<>();
    	// Extraction des champs de la classe parente (si existante)
        Field[] parentClassFields = targetClass.getSuperclass().getDeclaredFields();
        int ordreField = 0 ;
        for (Field field : parentClassFields) {
           
                csvFieldMap.put(ordreField++, field);
          
        }

        // Extraction des champs de la classe actuelle
        Field[] currentClassFields = targetClass.getDeclaredFields();
        for (Field field : currentClassFields) {
                csvFieldMap.put(ordreField++, field);
         }
    	
        Set<Integer> set = csvFieldMap.keySet() ;
        
        String[] tables = new String[set.size()] ;
        
    	for (Integer integer : set) {
    		tables[integer] = csvFieldMap.get(integer).getName() ;
		}
    	
		return tables ;
    	
    	
    	
    	
    }
    
    /**
     * Extrait les champs de la classe et génère une chaîne d'en-têtes au format CSV.
     * 
     * @return String contenant les noms des champs sous forme d'en-tête CSV
     * @throws JFFPBException 
     */
    private String extractFieldsAsHeader() throws JFFPBException {
    	
    	
    	if (this.targetClass == null) {
    		
    		throw new JFFPBException("la declaration de la classe est obligatoire \"targetClass isNull\"");
    	}
        // Map pour stocker les champs CSV avec leur position dans le fichier
        Map<Integer, FieldCsv> csvFieldMap = new HashMap<>();

        // Extraction des champs de la classe parente (si existante)
        Field[] parentClassFields = this.targetClass.getSuperclass().getDeclaredFields();
        for (Field field : parentClassFields) {
            FieldCsv fieldCsv = new FieldCsv(field);
            if (fieldCsv.getCsvMappingParse() != null) {
                csvFieldMap.put(fieldCsv.getCsvMappingParse().offset(), fieldCsv);
            }
        }

        // Extraction des champs de la classe actuelle
        Field[] currentClassFields = this.targetClass.getDeclaredFields();
        for (Field field : currentClassFields) {
            FieldCsv fieldCsv = new FieldCsv(field);
            if (fieldCsv.getCsvMappingParse() != null) {
                csvFieldMap.put(fieldCsv.getCsvMappingParse().offset(), fieldCsv);
            }
        }

        // Construction de l'en-tête CSV en utilisant le séparateur de colonnes
        StringBuffer headerBuffer = new StringBuffer();
        Iterator<Map.Entry<Integer, FieldCsv>> iterator = csvFieldMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, FieldCsv> entry = iterator.next();
            FieldCsv fieldCsv = entry.getValue();
            // Ajoute le nom du champ au buffer, précédé du séparateur
            headerBuffer.append(this.columnSeparator).append(fieldCsv.getField().getName());
        }
        
        

        // Supprime le premier séparateur inutile
        return headerBuffer.toString().replaceFirst(this.columnSeparator, "");
    }
}
