package com.ksh.springbootmultipledatabases.entities.postgre;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "sequence_table", schema = "public")
@Data
public class Sequence {

    @Id
    private long id;
    @Column(name = "sequence_name")
    private String sequenceName;
    @Column(name = "sequence_start")
    private long sequenceStart;
    @Column(name = "sequence_end")
    private long sequenceEnd;
    @Column(name = "sequence_current")
    private long sequenceCurrent;
    @Column(name = "created_ts")
    private LocalDateTime createdTs;
    @Column(name = "updated_ts")
    private LocalDateTime updatedTs;
}
