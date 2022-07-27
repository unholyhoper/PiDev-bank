package tn.esprit.bank.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class ChangePasswordRequest {
    private static final long serialVersionUID = 5926468583005150706L;

    private String oldpassword;
    private String newPassword;
    private String confirmNewPassword;

}
