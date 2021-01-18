package com.todolist.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task")
@ToString
@EqualsAndHashCode
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Task implements Serializable {

    private static final long serialVersionUID = -3661292867055495781L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "done", nullable = false)
    private boolean done;

    @Column(name = "username", nullable = true)
    private String username;

}