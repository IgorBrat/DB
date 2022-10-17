package com.lviv.iot.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Agency {
    private Integer id;
    private Integer userId;
    private String name;
    private String owner;
    private String cityName;
    private String regionName;
    private String hqAddress;
}
