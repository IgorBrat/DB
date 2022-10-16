package com.lviv.iot.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String phone;
    private String email;
}
