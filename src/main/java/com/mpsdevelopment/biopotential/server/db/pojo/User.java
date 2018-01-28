package com.mpsdevelopment.biopotential.server.db.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "User")
@Getter
@Setter
@Accessors(chain = true)
public class User extends BaseObject {

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
    public static final String ROLE_FIELD = "role";
    public static final String ROLE_FIELD_GS = "r";
    public static final String RANK_FIELD = "rank";
    public static final String RANK_FIELD_GS = "rank";
    public static final String DIVISION_FIELD = "division";
    public static final String DIVISION_FIELD_GS = "div";
    public static final String CALL_FIELD = "call";
    public static final String CALL_FIELD_GS = "call";
    public static final String ADMIN_FIELD = "admin";
    public static final String ADMIN_FIELD_GS = "adm";
    public static final String TEL_FIELD = "tel";
    public static final String TEL_FIELD_GS = "t";
    public static final String EMAIL_FIELD = "email";
    public static final String EMAIL_FIELD_GS = "e";
    public static final String BORNPLACE_FIELD = "bornPlace";
    public static final String BORNPLACE_FIELD_GS = "bp";
    public static final String BORNDATE_FIELD = "bornDate";
    public static final String BORNDATE_FIELD_GS = "bd";

    public enum Gender{
        Man,
        Woman
    }

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
    @Column(name = ROLE_FIELD)
    @SerializedName(ROLE_FIELD_GS)
    private String role;
    
    @Expose
    @Column(name = RANK_FIELD)
    @SerializedName(RANK_FIELD_GS)
    private String rank;
    
    @Expose
    @Column(name = DIVISION_FIELD)
    @SerializedName(DIVISION_FIELD_GS)
    private String division;
    
    @Expose
    @Column(name = CALL_FIELD)
    @SerializedName(CALL_FIELD_GS)
    private String call;
    
    @Expose
    @Column(name = ADMIN_FIELD)
    @SerializedName(ADMIN_FIELD_GS)
    private Boolean admin;

    @Expose
    @Column(name = TEL_FIELD)
    @SerializedName(TEL_FIELD_GS)
    private String tel;

    @Expose
    @Column(name = EMAIL_FIELD)
    @SerializedName(EMAIL_FIELD_GS)
    private String email;

    @Expose
    @Column(name = BORNPLACE_FIELD)
    @SerializedName(BORNPLACE_FIELD_GS)
    private String bornPlace;

    @Expose
    @Column(name = BORNDATE_FIELD)
    @SerializedName(BORNDATE_FIELD_GS)
    private Long bornDate;

    @Expose
    @Column(name = "gender")
    private String gender;

    public Boolean getAdministrator() {
        return admin;
    }

    public User setAdministrator(Boolean admin) {
        this.admin = admin;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", role='" + role + '\'' +
                ", rank='" + rank + '\'' +
                ", division='" + division + '\'' +
                ", call='" + call + '\'' +
                ", admin=" + admin +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", bornPlace='" + bornPlace + '\'' +
                ", bornDate=" + bornDate +
                ", gender='" + gender + '\'' +
                '}';
    }
}
