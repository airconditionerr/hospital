package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName Feedback
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:05
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class Feedback {

    public String feedbackId;
    public String feedbackContent;
}
