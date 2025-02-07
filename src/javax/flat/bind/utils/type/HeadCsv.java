package javax.flat.bind.utils.type;

import javax.flat.bind.annotation.csv.CsvMappingParse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeadCsv {

	
	@CsvMappingParse(offset = 1)
    private String headCsv ;
	
}
