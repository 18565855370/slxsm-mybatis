package com.slxsm.mybatis.domain;

import lombok.*;

/**
 * @author slxsm
 * @date 2020/1/11
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserModel {

    private Long id;
    private String userName;
    private Integer age;
    private Double salary;
    private Integer sex;
}
