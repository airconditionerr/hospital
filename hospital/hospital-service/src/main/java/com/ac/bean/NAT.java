package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName NAT
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/26 0:42
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class NAT {
    private String natId;
    private String transactDate;
    private String transactTime;
    private String userId;
    private String natStatus;
}
