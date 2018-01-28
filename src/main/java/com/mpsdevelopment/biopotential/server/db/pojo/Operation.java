package com.mpsdevelopment.biopotential.server.db.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Operation")
@Getter
@Setter
@Accessors(chain = true)
public class Operation extends BaseObject {

    public static final String BALANCE_FIELD = "balance";
    public static final String BALANCE_FIELD_GS = "b";
    public static final String OPERATION_CODE = "operationCode";
    public static final String OPERATION_CODE_GS = "oc";
    public static final String DATE = "date";
    public static final String DATE_GS = "t";
    public static final String AMOUNT = "amount";
    public static final String AMOUNT_GS = "a";
    public final static String CARD = "card";
    public final static String CARD_GS = "c";

    @Expose
    @ManyToOne(/*fetch = FetchType.LAZY,*/cascade = CascadeType.ALL)
    @JoinColumn(name = CARD)
    @SerializedName(CARD_GS)
    private Card card;

    @Expose
    @Column(name = OPERATION_CODE)
    @SerializedName(OPERATION_CODE_GS)
    private String operationCode;
    
    @Expose
    @Column(name = DATE)
    @SerializedName(DATE_GS)
    private long date;
    
    @Expose
    @Column(name = AMOUNT)
    @SerializedName(AMOUNT_GS)
    private int amount;

    @Expose
    @Column(name = BALANCE_FIELD)
    @SerializedName(BALANCE_FIELD_GS)
    private int balance;
    

}
