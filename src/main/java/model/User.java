package model;

import util.AttributeBlankException;
import util.AttributeLengthException;
import util.AttributeRegexException;
import util.ModelException;

/**
 *
 * @author User
 */
public class User {

    private static final String DEFAULT_ROLE = "US";
    
    private static final int LENGTH_ROLE = 2;
    private static final int LENGTH_EMAIL = 320;
    private static final int LENGTH_ATTR = 255;
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private static final String REGEX_STUDENTID = "SE\\d{6}";

    private String emailAddress;
    private String fullName;
    private String studentId;
    private String password;
    private String role;

    public User() {
    }
    
    public User(String emailAddress, String fullName, String studentId, String password) throws ModelException {
        setEmailAddress(emailAddress);
        setFullName(fullName);
        setStudentId(studentId);
        setPassword(password);
        setRole(DEFAULT_ROLE);
    }

    public User(String emailAddress, String fullName, String studentId, String password, String role) throws ModelException {
        setEmailAddress(emailAddress);
        setFullName(fullName);
        setStudentId(studentId);
        setPassword(password);
        setRole(role);
    }

    public String getRole() {
        return role;
    }

    /**
     * 
     * @param role
     * @throws AttributeLengthException less than LENGTH_ROLE characters
     */
    public void setRole(String role) throws AttributeLengthException {
        if(role.length() > LENGTH_ROLE) {
            throw new AttributeLengthException("Role",role.length(),LENGTH_ROLE);
        } else {
            this.role = role;
        }
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @param emailAddress
     * @throws AttributeLengthException less than LENGTH_EMAIL characters
     * @throws AttributeRegexException formatted according to REGEX_EMAIL
     */
    public final void setEmailAddress(String emailAddress) throws AttributeLengthException, AttributeRegexException {
        if (emailAddress.length() > LENGTH_EMAIL) {
            throw new AttributeLengthException("Email address", emailAddress.length(), LENGTH_EMAIL);
        } else if (!emailAddress.matches(REGEX_EMAIL)) {
            throw new AttributeRegexException("Email address", REGEX_EMAIL);
        } else {
            this.emailAddress = emailAddress;
        }
    }

    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     * @throws AttributeLengthException less than LENGTH_ATTR characters
     * @throws AttributeBlankException cannot be blank
     */
    public final void setFullName(String fullName) throws AttributeLengthException, AttributeBlankException {
        if (fullName.length() > LENGTH_ATTR) {
            throw new AttributeLengthException("Full name", fullName.length(), LENGTH_ATTR);
        } else if (fullName.isEmpty()) {
            throw new AttributeBlankException("Full name");
        } else {
            this.fullName = fullName;
        }
    }

    public String getStudentId() {
        return studentId;
    }

    /**
     *
     * @param studentId
     * @throws AttributeRegexException formatted according to REGEX_STUDENTID
     */
    public final void setStudentId(String studentId) throws AttributeRegexException {
        if (!studentId.matches(REGEX_STUDENTID)) {
            throw new AttributeRegexException("Student ID", REGEX_STUDENTID);
        } else {
            this.studentId = studentId;
        }
    }

    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     * @throws AttributeLengthException less than LENGTH_ATTR characters
     * TODO: Check password format
     */
    public final void setPassword(String password) throws AttributeLengthException {
        if (password.length() > LENGTH_ATTR) {
            throw new AttributeLengthException("Password", password.length(), LENGTH_ATTR);
        } else {
            this.password = password;
        }
    }
    
    @Override
    public String toString() {
        return "User: {\n\tName: " + this.fullName + "\n\tEmail: " + this.emailAddress + "\n\tStudent ID: " + this.studentId + "\n\tPassword: " + this.password + "\n\tRole: " + this.role + "\n}";
    }
}
