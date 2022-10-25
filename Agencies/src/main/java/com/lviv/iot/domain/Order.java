package com.lviv.iot.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
