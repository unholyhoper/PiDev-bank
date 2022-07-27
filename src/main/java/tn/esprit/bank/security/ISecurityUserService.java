package tn.esprit.bank.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
