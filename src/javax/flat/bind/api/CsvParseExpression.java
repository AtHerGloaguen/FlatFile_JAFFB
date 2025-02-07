package javax.flat.bind.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.flat.bind.make.CsvMakeRootElem;
import javax.flat.bind.make.PositionalMakeRootElem;

/**
 * @author Gloaguen Joel
 */
public class CsvParseExpression {

    private Object newInstanceType;
    private List<FieldCsv> fd_LigneRoot;
    private Map<String, Method> map;
    private CsvMakeRootElem element;

    public CsvParseExpression(Object newInstanceType, List<FieldCsv> fd_LigneRoot, Map<String, Method> map,
            CsvMakeRootElem element) {
        this.newInstanceType = newInstanceType;
        this.fd_LigneRoot = fd_LigneRoot;
        this.map = map;
        this.element = element;
    }

    public CsvMakeRootElem getElement() {
        return element;
    }

    public Object getNewInstanceType() {
        return newInstanceType;
    }

    public List<FieldCsv> getFd_LigneRoot() {
        return fd_LigneRoot;
    }

    public Map<String, Method> getMap() {
        return map;
    }

    @Override
    public String toString() {

        Field[] nomChamps = this.getClass().getDeclaredFields();
        StringBuffer formatString = new StringBuffer(this.getClass().getSimpleName() + "(");
        for (Field field : nomChamps) {
            if (!"serialVersionUID".equals(field.getName())) {
                try {
                    Object Valu = this.getClass().getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1))
                            .invoke(this, new Object[] {});
                    formatString.append(";[ " + field.getName() + "='" + Valu.toString() + "' ]");
                } catch (Exception e) {
                    formatString.append(";[ " + field.getName() + "='null' ]");
                }

            }
        }
        return formatString.append(")\n").toString().replaceFirst(";", "");

    }
}
