package tn.esprit.bank.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class UserCreatedResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Long cin;

    public UserCreatedResponse(String userName, String firstName, String lastName, String email, Long cin) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cin = cin;
    }
}
