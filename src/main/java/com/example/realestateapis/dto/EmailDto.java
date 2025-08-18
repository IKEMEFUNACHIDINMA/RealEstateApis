package com.example.realestateapis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmailDto {
    private String recipient;
    private String subject;
    private String messageBody;
    private String attachment;


}
