package com.ffpm.sample.csv.test;

import java.io.Serializable;

public class Colonnesextract implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom_colonnes ;
	private Boolean extraction ;
	

	public Colonnesextract() {
		super();
	}

	public Colonnesextract(String nom_colonnes, Boolean extraction) {
		super();
		this.nom_colonnes = nom_colonnes;
		this.extraction = extraction;
	}

	public String getNom_colonnes() {
		// TODO Auto-generated method stub
		return nom_colonnes;
	}

	public Boolean getExtraction() {
		// TODO Auto-generated method stub
		return extraction;
	}

	public void setNom_colonnes(String nom_colonnes) {
		this.nom_colonnes = nom_colonnes;
	}

	public void setExtraction(Boolean extraction) {
		this.extraction = extraction;
	}

}
