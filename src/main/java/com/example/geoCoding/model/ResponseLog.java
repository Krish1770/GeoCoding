package com.example.geoCoding.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "ResponseLog")
public class ResponseLog {
    @Id
    private String id;

    private String subscriptionId;

    private Date date;

    private String method;

    private String requestURI;

    private String requestBody;

    private int status;

    private String responseBody;
}
