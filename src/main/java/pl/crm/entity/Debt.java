package pl.crm.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "debts")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal principal;

    private BigDecimal costs;

    private BigDecimal interests;

    @Column(name = "contract_number")
    private String contractNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "contract_date")
    private Date contractDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "termination_date")
    private Date terminationDate;

    private Date created = new Date();

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case aCase;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Debt() {
    }


    public Debt(BigDecimal principal, BigDecimal costs, BigDecimal interests, String contractNumber,
                Date contractDate, Date terminationDate, Date created, Case aCase, User user) {
        this.principal = principal;
        this.costs = costs;
        this.interests = interests;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.terminationDate = terminationDate;
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

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getCosts() {
        return costs;
    }

    public void setCosts(BigDecimal costs) {
        this.costs = costs;
    }

    public BigDecimal getInterests() {
        return interests;
    }

    public void setInterests(BigDecimal interests) {
        this.interests = interests;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
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
        return "Debt{" +
                "id=" + id +
                ", principal=" + principal +
                ", costs=" + costs +
                ", interests=" + interests +
                ", contractNumber='" + contractNumber + '\'' +
                ", contractDate=" + contractDate +
                ", terminationDate='" + terminationDate + '\'' +
                ", created=" + created +
                ", aCase=" + aCase +
                ", user=" + user +
                '}';
    }
}
