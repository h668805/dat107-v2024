package no.hvl.dat107.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(schema = "forelesning6a")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    private String fNr; // Fødselsnummer, 11 siffer, f.eks. "12345678901"
    
    private String fornavn;
    private String etternavn;
    
    public Person() {}
    
    public Person(String fNr, String fornavn, String etternavn) {
        this.fNr = fNr;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    @Override
    public String toString() {
        return "fNr=" + fNr + ", fornavn=" + fornavn + ", etternavn=" + etternavn;
    }

}
