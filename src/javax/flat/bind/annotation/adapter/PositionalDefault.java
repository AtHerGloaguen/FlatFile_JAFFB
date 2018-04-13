/*
 * Creation : 12 mars 2018
 */
package javax.flat.bind.annotation.adapter;

import javax.flat.bind.JFFPBException;

public abstract class PositionalDefault<BoundType> {

    public abstract BoundType getValue() throws JFFPBException;

}
