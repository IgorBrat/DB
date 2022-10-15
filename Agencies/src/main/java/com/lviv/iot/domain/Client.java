package com.lviv.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {
    private Integer id;
    private Integer userId;
    private String surname;
    private String name;
    private String birthday;
    private String phone;
    private String email;
    private String cityName;
    private String regionName;
    private String streetAddress;
    private Integer clientCardId;
}
