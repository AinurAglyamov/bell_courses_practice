package ru.bellintegrator.practice.employee.model;

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.reference.model.Country;
import ru.bellintegrator.practice.reference.model.DocumentType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private String middleName;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private boolean isIdentified;

    private String docNumber;

    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id")
    private Country country;

    @Version
    private Integer version;

    public Employee() {
    }


    public Employee(String firstName, String secondName, String middleName, String position, String phone, boolean isIdentified, String docNumber, Date docDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = true;
        this.docNumber = docNumber;
        this.docDate = docDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Office getOffice() {
        if(office == null) {
            office = new Office();
        }
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public DocumentType getDocumentType() {
        if(documentType == null) {
            documentType = new DocumentType();
        }
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Country getCountry() {
        if(country == null) {
            country = new Country();
        }
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
