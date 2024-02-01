package com.example.geoCoding.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadAssignDto {

    private String companyId;

    private String subscriptionId;

    private String companyName;
}
