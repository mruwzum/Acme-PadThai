package domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.SortedSet;

@Entity
@Access(AccessType.PROPERTY)
public class Folder extends DomainEntity {

    private String name;

    private Collection<Message> messages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Message.class, mappedBy = "folder")
    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

}
