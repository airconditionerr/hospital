package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName Admin
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/27 14:44
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class Admin {
    private int id;
    private String adminId;
    private String adminUsername;
    private String adminPassword;
}
