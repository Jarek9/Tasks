package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;

    @Override
    public String toString() {
        return  id + " " + title + " " + content;
    }
}
