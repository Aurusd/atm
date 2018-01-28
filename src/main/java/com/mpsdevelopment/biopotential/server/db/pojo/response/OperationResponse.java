package com.mpsdevelopment.biopotential.server.db.pojo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class OperationResponse {

    private String cardNumber;
    private String operationCode;
    private Date date;
    private int amount;
    private int balance;

}
