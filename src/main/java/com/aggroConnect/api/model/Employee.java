package com.aggroConnect.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("landline")
    private String landline;
    @JsonProperty("cellphone")
    private String cellphone;
    @JsonProperty("department")
    private String department;
    @ManyToOne
    @JoinColumn(name = "site_id")
    @JsonProperty("site")
    private Site site;

}