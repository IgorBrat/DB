package com.lviv.iot.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Client {
    private Integer id;
    private Integer userId;
    private String surname;
    private String name;
    private String birthday;
    private String cityName;
    private String regionName;
    private String streetAddress;
    private Integer clientCardId;
}
