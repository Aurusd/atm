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
@Table(name = "Card")
@Getter
@Setter
@Accessors(chain = true)
public class Card extends BaseObject {

    public static final String NUMBER_FIELD = "number";
    public static final String NUMBER_FIELD_GS = "n";
    public static final String PIN = "pin";
    public static final String PIN_GS = "a";
    public static final String IS_BLOCKED = "isBlocked";
    public static final String IS_BLOCKED_GS = "oc";
    public static final String BALANCE = "balance";
    public static final String BALANCE_GS = "b";
    public static final String USER_ATM = "userAtm";
    public static final String USER_ATM_GS = "ua";

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Operation> operations = new ArrayList<>();

    @Expose
    @ManyToOne(/*fetch = FetchType.LAZY,*/cascade = CascadeType.ALL)
    @JoinColumn(name = USER_ATM)
    @SerializedName(USER_ATM_GS)
    private UserAtm userAtm;

    @Expose
    @Column(name = NUMBER_FIELD)
    @SerializedName(NUMBER_FIELD_GS)
    private String number;

    @Expose
    @Column(name = PIN)
    @SerializedName(PIN_GS)
    private String pin;

    @Expose
    @Column(name = IS_BLOCKED)
    @SerializedName(IS_BLOCKED_GS)
    private boolean isBlocked;
    
    @Expose
    @Column(name = BALANCE)
    @SerializedName(BALANCE_GS)
    private int balance;

}
