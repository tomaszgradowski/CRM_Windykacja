package pl.crm.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "action_type_result_type")
public class ActionTypeResultType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_type_id")
    private ActionType actionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "result_type_id")
    private ResultType resultType;

    private Date created = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    public ActionTypeResultType() {
    }

    public ActionTypeResultType(ActionType actionType, ResultType resultType, Date created, User user) {
        this.actionType = actionType;
        this.resultType = resultType;
        this.created = created;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
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


    @Override
    public String toString() {
        return "ActionTypeResultType{" +
                "id=" + id +
                ", actionType=" + actionType +
                ", resultType=" + resultType +
                ", created=" + created +
                ", user=" + user +
                '}';
    }
}

