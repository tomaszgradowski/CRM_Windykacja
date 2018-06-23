package pl.crm.entity;

import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "debtors")
public class Debtor {

    //Można zrobić walidację grupową i dodać dane firmy

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @PESEL
    private String pesel;


    @Column(name = "identity_numer")
    private String identityNumber;

    private Date created = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id")
    private Case aCase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Debtor() {
    }


    public Debtor(String firstName, String lastName, String pesel, String identityNumber, Date created, Case aCase, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.identityNumber = identityNumber;
        this.created = created;
        this.aCase = aCase;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", created=" + created +
                ", aCase=" + aCase +
                ", user=" + user +
                '}';
    }
}
