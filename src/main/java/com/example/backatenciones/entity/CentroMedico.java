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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = CentroMedico.class)
public class CentroMedico implements Serializable {

    @ApiModelProperty(value = "ID del centro médico", dataType = "uuid", position = 1)
    @Id
    @Column("idcentromedic")
    @CassandraType(type = CassandraType.Name.UUID)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private UUID id;

    @ApiModelProperty(value = "Es el identificador de la ubicacion del centro médico", dataType = "UUID", position = 2)
    @NotNull(message = "El ID de la ubicacion no puede ser nulo")
    @Column("idubicacion")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID idubicacion;

    @ApiModelProperty(value = "Es el nombre del centro médico", dataType = "ascii", position = 3)
    @NotEmpty(message = "El nombre no puede ser vacio")
    @NotNull(message = "El nombre no puede ser nulo")
    @Column("nombre")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String nombre;

    @ApiModelProperty(value = "Es el sector del centro médico - privado/estatal", dataType = "ascii", position = 4)
    @NotEmpty(message = "La sector no puede ser vacio")
    @NotNull(message = "La sector no puede ser nulo")
    @Column("sector")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String sector;

}
