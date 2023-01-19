package util;

/**
 *
 * @author User
 */
public class AttributeLengthException extends ModelException {
    public AttributeLengthException() {
        super();
    }
    
    public AttributeLengthException(String attributeName, int attributeLength, int maximumLength) {
        super(attributeName + " (length=" + attributeLength + ") exceeds maximum length (" + maximumLength + ")");
    }
}
