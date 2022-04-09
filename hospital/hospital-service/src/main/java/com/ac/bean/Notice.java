package com.ac.bean;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName Notice
 * @Description TODO
 * @Author 29175
 * @Date 2022/3/25 23:40
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
public class Notice {
    private String noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeTime;
}
