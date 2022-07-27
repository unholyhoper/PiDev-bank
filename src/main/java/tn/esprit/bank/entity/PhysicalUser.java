package tn.esprit.bank.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "PHYSICAL_USER")
public class PhysicalUser extends AbstractUser {
    Role role;
}
