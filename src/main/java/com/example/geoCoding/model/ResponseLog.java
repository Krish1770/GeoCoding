package com.example.geoCoding.model;


import com.example.geoCoding.DTO.ResponseLogDto;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "ResponseLog")
public class ResponseLog {
    @Id
    private String id;

    private String subscriptionId;

    private List<ResponseLogDto> responseLogDtoList;
}
