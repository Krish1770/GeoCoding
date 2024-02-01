package com.example.geoCoding.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLogDto {

    private Date date;

    private List<LogDto> LogDtoList;
}
