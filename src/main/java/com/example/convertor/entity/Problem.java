package com.example.convertor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    private int row;
    private String person;
    private int courseCode;
    private String courseName;

    public String serialize() {
        return ""+row+"#"+person+"#"+courseCode+"#"+courseName;
    }

    public void deserialize(String string) {
        String[] parts = string.split("#");
        this.row=Integer.parseInt(parts[0]);
        this.person=parts[1];
        this.courseCode=Integer.parseInt(parts[2]);
        this.courseName=parts[3];
    }
}
