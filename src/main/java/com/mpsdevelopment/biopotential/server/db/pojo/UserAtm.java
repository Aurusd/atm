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
@Table(name = "UserAtm")
@Getter
@Setter
@Accessors(chain = true)
public class UserAtm extends BaseObject {

    public static final String LOGIN_FIELD = "login";
    public static final String LOGIN_FIELD_GS = "l";
    public static final String PASSWORD_FIELD = "password";
    public static final String PASSWORD_FIELD_GS = "p";
    public static final String NAME_FIELD = "name";
    public static final String NAME_FIELD_GS = "n";
    public static final String SURNAME_FIELD = "surname";
    public static final String SURNAME_FIELD_GS = "s";
    public static final String PATRONYMIC_FIELD = "patronymic";
    public static final String PATRONYMIC_FIELD_GS = "patr";
    public static final String TEL_FIELD = "tel";
    public static final String TEL_FIELD_GS = "t";
    public static final String EMAIL_FIELD = "email";
    public static final String EMAIL_FIELD_GS = "e";

    @OneToMany(mappedBy = "userAtm", cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    @Expose
    @Column(name = LOGIN_FIELD)
    @SerializedName(LOGIN_FIELD_GS)
    private String login;

    //@Expose(serialize = false, deserialize = true)
    @Expose
    @Column(name = PASSWORD_FIELD)
    @SerializedName(PASSWORD_FIELD_GS)
    private String password;

    @Expose
    @Column(name = NAME_FIELD)
    @SerializedName(NAME_FIELD_GS)
    private String name;

    @Expose
    @Column(name = SURNAME_FIELD)
    @SerializedName(SURNAME_FIELD_GS)
    private String surname;
    
    @Expose
    @Column(name = PATRONYMIC_FIELD)
    @SerializedName(PATRONYMIC_FIELD_GS)
    private String patronymic;
    
    @Expose
    @Column(name = TEL_FIELD)
    @SerializedName(TEL_FIELD_GS)
    private String tel;

    @Expose
    @Column(name = EMAIL_FIELD)
    @SerializedName(EMAIL_FIELD_GS)
    private String email;

}
