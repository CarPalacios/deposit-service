package com.nttdata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**Se crea la clase Deposit donde se declaran las variables.*/
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "deposit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {
  
  @Id
  private String id;
  
  @Field(name = "cardNumber")
  private String cardNumber;
  
  @Field(name = "amount")
  private Double amount;
    
  @Field(name = "description")
  private String description;

  @Field(name = "depositDate")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime depositDate = LocalDateTime.now();

}
