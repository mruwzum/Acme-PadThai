package domain;

import javax.persistence.*;
import java.util.SortedSet;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity {

    private String name;

    private SortedSet<Message> messages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Message.class, mappedBy = "folder")
    @OrderBy("sentDate")
    public SortedSet<Message> getMessages() {
        return messages;
    }

    public void setMessages(SortedSet<Message> messages) {
        this.messages = messages;
    }

}
