package util;

/**
 *
 * @author User
 */
public class AttributeBlankException extends ModelException {
    public AttributeBlankException() {super();}
    public AttributeBlankException(String attributeName) {
        super(attributeName + " cannot be blank.");
    }
}
