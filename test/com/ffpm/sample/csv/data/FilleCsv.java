package com.ffpm.sample.csv.data;

import java.util.ArrayList;
import java.util.List;

import javax.flat.bind.annotation.csv.CsvFormatFile;
import javax.flat.bind.annotation.csv.CsvMappingParse;
import javax.flat.bind.annotation.csv.CsvMappingParseRootElem;
import javax.flat.bind.annotation.positinal.PositionalFormatFile;
import javax.flat.bind.utils.type.HeadCsv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CsvFormatFile(name ="FilleCsv",charSeparateur = ';',type = "csv" )
public class FilleCsv {
	
	@CsvMappingParseRootElem( name = "Head", valuDebutLigne = 1,list = false,theclass =HeadCsv.class )
    private HeadCsv Head ;
    
	@CsvMappingParseRootElem( name = "corp", valuDebutLigne = 2)
    private List<?> corp  ;
    

}
