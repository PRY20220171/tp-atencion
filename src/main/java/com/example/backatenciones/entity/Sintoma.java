package com.example.backatenciones.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

//import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id", scope = Sintoma.class)
public class Sintoma  implements Serializable {

    @ApiModelProperty(value="ID del signo/síntoma", dataType="uuid", position=1)
    @Id
    @Column("idsintoma")
    @CassandraType(type = CassandraType.Name.UUID)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private UUID id;

    @ApiModelProperty(value="Es el ID de la atencion medica", dataType="uuid", position=2)
    @NotEmpty(message = "El ID de la atencion no puede ser vacio")
    @NotNull(message = "El ID de la atencion no puede ser nulo")
    @Column("idatencion")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID idatencion;

    @ApiModelProperty(value="Es el nombre y detalle del signo o síntoma", dataType="ascii", position=3)
    @NotEmpty(message = "La signo o síntoma no puede ser vacio")
    @NotNull(message = "La signo o síntoma no puede ser nulo")
    @Column("signossintomas")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String signossintomas;

}
