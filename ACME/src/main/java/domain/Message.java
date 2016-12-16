package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

    public Message() {
        super();
    }

    private Actor sender;

    private Actor recipient;

    private Date sentDate;

    private String subject;

    private String body;

    private Priority priority;

    private Folder folder;

    @NotNull
    @OneToOne(targetEntity = Actor.class)
    public Actor getSender() {
        return sender;
    }

    public void setSender(Actor sender) {
        this.sender = sender;
    }

    @NotNull
    @OneToOne(targetEntity = Actor.class)
    public Actor getRecipient() {
        return recipient;
    }

    public void setRecipient(Actor recipient) {
        this.recipient = recipient;
    }

    @Past
    @Temporal(TemporalType.TIMESTAMP)
    // @Pattern(regexp =
    // "^\\d{2}([0][1-9]|[1][0-2])([0][1-9]|[1-2][0-9]|[3][0-1])-\\d {4}$")
    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    @NotBlank
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @NotBlank
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Enumerated(EnumType.ORDINAL)
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @ManyToOne(optional = false)
    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "Message{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
