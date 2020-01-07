package just4fun.model;

import java.time.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.envers.*;

@Data
@Entity
@Table(name = "good_events")
@Audited
public class GoodEvent {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    @NotAudited
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @NotAudited
    private LocalDateTime updateDateTime;
}
