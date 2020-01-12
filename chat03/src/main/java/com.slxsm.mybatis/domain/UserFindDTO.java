package com.slxsm.mybatis.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFindDTO {

    private Long userId;
    private String userName;
}
