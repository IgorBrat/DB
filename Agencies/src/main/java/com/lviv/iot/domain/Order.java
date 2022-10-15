package com.lviv.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private Integer id;
    private Integer clientId;
    private Integer eventId;
    private String datetime;
    private String duration;
    private String cityName;
    private String regionName;
    private String streetAddress;
    private Float totalPrice;
}
