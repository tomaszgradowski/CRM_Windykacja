package pl.crm.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sms")
public class Sms {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @Column(name = "telephone_number")
//    private String telephoneNumber;

    @NotNull
    private String message;

    @NotNull
    private boolean test;

    @NotNull
    private String token = "FrdBslodVhAE9sedO2UZxfCHOlaW6QG4mPEH6ePg";

    private BigDecimal cost;

    private boolean success;

    private Date created = new Date();

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case aCase;

    @ManyToOne
    @JoinColumn(name = "telephone_id")
    private Telephone telephone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Sms() {

    }

    public Sms(String message, boolean test, String token, BigDecimal cost, boolean success, Date created, Case aCase, Telephone telephone, User user) {
        this.message = message;
        this.test = test;
        this.token = token;
        this.cost = cost;
        this.success = success;
        this.created = created;
        this.aCase = aCase;
        this.telephone = telephone;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", test=" + test +
                ", token='" + token + '\'' +
                ", cost=" + cost +
                ", success=" + success +
                ", created=" + created +
                ", aCase=" + aCase +
                ", telephone=" + telephone +
                ", user=" + user +
                '}';
    }
}
