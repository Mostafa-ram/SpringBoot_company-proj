package com.example.company.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long dep_id;
    private String dep_name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Employee_Department", referencedColumnName = "dep_id")
    List<Employee> emplyee = new ArrayList<>();


}
