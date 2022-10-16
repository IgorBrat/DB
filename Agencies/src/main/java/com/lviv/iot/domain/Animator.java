package com.lviv.iot.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Animator {
    private Integer id;
    private Integer userId;
    private String surname;
    private String name;
    private String email;
    private String phone;
    private String cityName;
    private String regionName;
    private Float salaryPerHour;
}
