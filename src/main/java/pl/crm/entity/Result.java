package pl.crm.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "result_type_id")
    private ResultType  resultType;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id")
    private Case aCase;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_id")
    private Action action;


    public Result() {
    }

    public Result(String comment, Date created, ResultType resultType, User user, Case aCase, Action action) {
        this.comment = comment;
        this.created = created;
        this.resultType = resultType;
        this.user = user;
        this.aCase = aCase;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", created=" + created +
                ", resultType=" + resultType +
                ", user=" + user +
                ", aCase=" + aCase +
                ", action=" + action +
                '}';
    }
}
