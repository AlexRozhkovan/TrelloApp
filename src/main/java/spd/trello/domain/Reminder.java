package spd.trello.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.configuration.Reminder.IReminder;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "reminders")
@Entity
@IReminder
public class Reminder extends Domain {

    @Column(name = "starts")
    @NotNull(message = "Field \"start\" can't be null")
    @FutureOrPresent
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime start;

    @Column(name = "ends")
    @NotNull(message = "Field \"end\" can't be null")
    @Future
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime end;

    @Column(name = "remind_on")
    @NotNull(message = "Field \"remind on\" can't be null")
    @Future
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime remindOn;

    private Boolean alive;

}

