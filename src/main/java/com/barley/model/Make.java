package com.barley.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Make {
    private String id;
    private String make;
    private String company;
    private String url;
}
