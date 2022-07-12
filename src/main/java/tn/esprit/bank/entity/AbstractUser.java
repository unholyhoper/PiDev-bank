package tn.esprit.bank.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractUser implements Serializable {
    private static final long serialVersionUID = 3876346912862238239L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long cin;

    String firstName;

    String lastName;

    String address;

    String password;

    String country;

}
