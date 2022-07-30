package tn.esprit.bank.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Long cin;
    private boolean isActivated;

    public UserResponse(String userName, String firstName, String lastName, String email, Long cin,boolean isActivated) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cin = cin;
        this.isActivated = isActivated;
    }
}
