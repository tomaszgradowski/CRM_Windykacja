package pl.crm.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "telephones")
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telNumber;

    private Date created = new Date();

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private Debtor debtor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Telephone() {
    }

    public Telephone(String telNumber, Date created, Debtor debtor, User user) {
        this.telNumber = telNumber;
        this.created = created;
        this.debtor = debtor;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Debtor getDebtor() {
        return debtor;
    }

    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "id=" + id +
                ", telNumber='" + telNumber + '\'' +
                ", created=" + created +
                ", debtor=" + debtor +
                ", user=" + user +
                '}';
    }
}
