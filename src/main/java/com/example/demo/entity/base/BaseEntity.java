package com.example.demo.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * update time
     */
    @JsonFormat(locale = "US",shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "updated")
    private Timestamp updated;

    /**
     * create time
     */
    @JsonFormat(locale = "US",shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "created")
    private Timestamp created;

    /**
     * deleted flag
     */
    @Column(name = "is_deleted")
    private boolean deleted;

    /**
     * JPA: do before database insert
     */
    @PrePersist
    protected void prePersist() {
        this.deleted = false;
        if (created == null) {
            created = new Timestamp(new Date().getTime());
        }

        if (updated == null) {
            updated = new Timestamp(new Date().getTime());
        }
    }

    /**
     * JPA: do before database update
     */
    @PreUpdate
    protected void preUpdate() {
        updated = new Timestamp(new Date().getTime());
    }
}
