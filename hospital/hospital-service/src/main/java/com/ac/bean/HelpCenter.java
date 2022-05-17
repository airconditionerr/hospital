package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelpCenter
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:58
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class HelpCenter {
    private int helpId;
    private String helpTitle;
    private String helpContent;
}
