package sn.ucad.gestionstock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Data
@MappedSuperclass
//@Entity
@NoArgsConstructor @AllArgsConstructor @ToString
@EntityListeners(AuditingEntityListener.class)  // Ecoute cette classe
public class AbstractEntity implements Serializable {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @CreatedDate
    @JsonIgnore
    @Column(name = "creationDate", nullable = false)
    private Instant creationDate;

     @LastModifiedDate
     @Column(name = "lastModifiedDate")
    private  Instant  lastModifiedDate;
}
