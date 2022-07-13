package tn.esprit.bank.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractUser implements Serializable , UserDetails {
    private static final long serialVersionUID = 3876346912862238239L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    Long cin;

    String firstName;

    String lastName;

    String address;

    String password;

    String country;

    Role role;

    @ElementCollection
    private List<GrantedAuthority> authorities;

    private String userName;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
