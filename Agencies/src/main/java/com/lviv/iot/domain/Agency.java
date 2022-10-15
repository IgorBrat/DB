package com.lviv.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agency {
    private Integer id;
    private Integer userId;
    private String name;
    private String owner;
    private String cityName;
    private String regionName;
    private String hqAddress;
    private String phone;
    private String email;
}
