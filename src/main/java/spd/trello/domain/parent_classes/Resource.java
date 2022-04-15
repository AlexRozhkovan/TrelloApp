package spd.trello.domain.parent_classes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Resource extends Domain {
    @Column(updatable = false)
    private String createdBy;
    private String updatedBy;

    @CreatedDate
    @Column(updatable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedDate;
}