package com.ffpm.sample.csv.test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.flat.bind.JAFFBContext;
import javax.flat.bind.JFFPBException;
import javax.flat.bind.Marshaller;
import javax.flat.bind.utils.ExtractHeadCsv;
import javax.flat.bind.utils.genere.ConstructionClass;
import javax.flat.bind.utils.type.HeadCsv;

import com.ffpm.sample.csv.data.FilleCsv;

public class TEstReelJffab {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, JFFPBException {


		List<Colonnesextract> lisColonnesextract =  new ArrayList() ;
		boolean valu = true ;
		for(int i = 0 ; i < 100 ; i++) {
			
			lisColonnesextract.add(new Colonnesextract("titi_"+i,(valu ))) ;
			
			valu = valu ? false : true ;
			
		}
		String tableauxField[] = new String[lisColonnesextract.size()];

		for (int i = 0; i < lisColonnesextract.size(); i++) {
			tableauxField[i] = lisColonnesextract.get(i).getNom_colonnes();
		}

		Class<?> classForCsv = ConstructionClass.constructClass(tableauxField,"com.csv.GeneratedClassForFile");

		Object instance = classForCsv.getDeclaredConstructor().newInstance();


		for (Colonnesextract colon : lisColonnesextract) {

			Method method = classForCsv.getDeclaredMethod(
					"set" + ConstructionClass.capitalize(colon.getNom_colonnes()), String.class);
			method.setAccessible(true);
			method.invoke(instance, colon.getExtraction().toString());

		}


	

		JAFFBContext jaffbContext = JAFFBContext.newInstance();
		Marshaller jffb = jaffbContext.createMarshaller();
		ExtractHeadCsv extractHeadCsv = new ExtractHeadCsv(classForCsv, ";");
		String headcsv = extractHeadCsv.getHeaderForClass();
		System.out.println("le head ["+headcsv+"]");
		String result = jffb.convertObjectInChaineCsv(instance, ";");
		System.out.println("le result ["+result+"]");
		FilleCsv filleCsv = new FilleCsv() ;
		HeadCsv headCsv2 = new HeadCsv() ;
		headCsv2.setHeadCsv(headcsv);
		filleCsv.setHead(headCsv2);
		List<Object>  objects = new ArrayList<>() ;
		objects.add(instance) ;
		objects.add(instance) ;
		objects.add(instance) ;
		
		filleCsv.setCorp(objects); 
		
		jffb.marshalCsv(filleCsv, new File("test.csv"));
		

		Class<? extends Colonnesextract> clazz1 = lisColonnesextract.get(0).getClass() ;
		
		Field[] f = clazz1.getDeclaredFields() ;
		String[] nameFied = new String[f.length] ; 
		
		
		for (int i = 0; i < f.length; i++) {
			nameFied[i] = f[i].getName() ;
		}
		
		Class<?> classForCsvtables = ConstructionClass.constructClass(nameFied,"com.csv.GeneratedClassTableForFile");
		Object instanceTable =null;
		
		List<Object> listForParseCsv = new ArrayList();
		for (Colonnesextract colon : lisColonnesextract) {
			instanceTable = classForCsvtables.getDeclaredConstructor().newInstance();
		
				
				Method method = classForCsvtables.getDeclaredMethod(
						"set" + ConstructionClass.capitalize(nameFied[1]), String.class);
				method.setAccessible(true);
				method.invoke(instanceTable, colon.getNom_colonnes().toString());
				
				method = classForCsvtables.getDeclaredMethod(
						"set" + ConstructionClass.capitalize(nameFied[2]), String.class);
				method.setAccessible(true);
				method.invoke(instanceTable, colon.getExtraction().toString());
				
				
				
		

			listForParseCsv.add(instanceTable) ;

		}
		
		for (Object object : listForParseCsv) {
			System.out.println(	jffb.convertObjectInChaineCsv(object, ";") );
			
		}
		
		System.out.println(	jffb.convertObjectInChaineCsv(listForParseCsv, ";",true) );
		
		
		// l'inverse 
		
		
		

	}

}
