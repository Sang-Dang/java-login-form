package util;

/**
 *
 * @author User
 */
public class AttributeRegexException extends ModelException {
    public AttributeRegexException() {
        super();
    }
    
    public AttributeRegexException(String attributeName, String regex) {
        super(attributeName + " does not match the regex: " + regex);
    }
}
