package pl.crm.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="cases")
public class Case {

    //Można dopisać funkcję do generowania numeru konta

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    //@Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private Date startDate;

   // @NotBlank
    @NotNull
   // @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cc_employee_id")
    private	User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lawyer_id")
    private	User user1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supervisor_id")
    private	User user2;

   // @NotNull
   // @Size(min = 26, max = 26)
    private String accountNumber;

    @NotNull
    private Date created = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user3;

    public Case() {
    }

    public Case(Date startDate, Date endDate, User user, User user1, User user2, String accountNumber, Date created, User user3) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.user1 = user1;
        this.user2 = user2;
        this.accountNumber = accountNumber;
        this.created = created;
        this.user3 = user3;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser3() {
        return user3;
    }

    public void setUser3(User user3) {
        this.user3 = user3;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                ", user1=" + user1 +
                ", user2=" + user2 +
                ", accountNumber='" + accountNumber + '\'' +
                ", created=" + created +
                ", user3=" + user3 +
                '}';
    }
}

