package com.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;

@JsonPropertyOrder({"id", "description", "done", "username"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
public class TaskVO implements Serializable {

    private static final long serialVersionUID = 1254191434652058949L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("done")
    private boolean isDone;

    @JsonProperty("username")
    private String username;

}
