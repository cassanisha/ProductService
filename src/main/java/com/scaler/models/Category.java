package com.scaler.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@Entity //makes table of the class
public class Category extends BaseModel {
    @Column(name="description") //sets custom column name
    private String desc;
}
