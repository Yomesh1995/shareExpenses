package project.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserInfo {
    @NotEmpty(message = "User Name cannot be Empty")
    private String userName;
    @NotEmpty(message = "Password cannot be Empty")
    private String password;
    @NotEmpty(message = "Confirm Password cannot be Empty")
    private String confirmPassword;
    @NotEmpty(message = "Name cannot be Empty")
    private String name;
    @NotEmpty(message = "Mobile Number cannot be Empty")
    private String mobileNumber;
    @NotEmpty(message = "Email Id cannot be Empty")
    @Email(message = "Email Id not in valid format")
    private String emailId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
