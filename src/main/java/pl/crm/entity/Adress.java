package pl.crm.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "adresses")
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String street;

    private String postcode;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name ="local_number")
    private String localNumber;

    private Date created = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "debtor_id")
    private Debtor debtor;


    public Adress() {
    }

    public Adress(String city, String street, String postcode, String houseNumber, String localNumber, Date created, User user, Debtor debtor) {
        this.city = city;
        this.street = street;
        this.postcode = postcode;
        this.houseNumber = houseNumber;
        this.localNumber = localNumber;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
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
        return "Adress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", localNumber='" + localNumber + '\'' +
                ", created=" + created +
                ", user=" + user +
                ", debtor=" + debtor +
                '}';
    }
}
