/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Student;

/**
 *
 * @author ndupo
 */
public class StudentDTO {
    
    private String name;
    private String username;
    private String color;

    public StudentDTO() {
    }

    public StudentDTO(Student stud) {
        this.name = stud.getName();
        this.username = stud.getUsername();
        this.color = stud.getColor();
    }  
}
