package ru.bellintegrator.practice.reference.model;



import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "findCountryByCodeAndName",
                query = "SELECT c FROM Country c WHERE c.code = :code AND c.name = :name"
        ),
        @NamedQuery(
                name = "findCountryByCode",
                query = "SELECT c FROM Country c WHERE c.code = :code"
        )
})

public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private Integer code;

    private String name;

    @Version
    private Integer version;

    public Country() {
    }

    public Country(Integer code, String name) {
        this.code = code;
        this.name = name;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
