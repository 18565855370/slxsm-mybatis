package com.slxsm.chat05.domain;

import lombok.*;

/**
 * @author slxsm
 * @date 2020/1/13
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel1 {

    private Integer id;
    private Integer userId;
    private Long createTime;
    private Long upTime;

    private UserModel userModel;
}
