package tn.esprit.bank.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "MORAL_USER")
public class MoralUser extends AbstractUser {
}