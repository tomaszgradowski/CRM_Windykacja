package pl.crm.entity;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @org.hibernate.validator.constraints.Email
    private String email;

    private Date created = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private Debtor debtor;

    public Email() {
    }

    public Email(String email, Date created, User user, Debtor debtor) {
        this.email = email;
        this.created = created;
        this.user = user;
        this.debtor = debtor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Debtor getDebtor() {
        return debtor;
    }

    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", user=" + user +
                ", debtor=" + debtor +
                '}';
    }
}
