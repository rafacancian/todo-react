package com.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class ResponseVO implements Serializable {

    @JsonProperty("tasks")
    private List<TaskVO> taskVOS;
}
