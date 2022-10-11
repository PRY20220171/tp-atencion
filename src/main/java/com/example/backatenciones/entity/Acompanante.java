package com.example.backatenciones.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.time.LocalDate;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id", scope = Acompanante.class)
public class Acompanante implements Serializable {

    @ApiModelProperty(value = "ID de la acompanante", dataType = "uuid", position = 1)
    @Id
    @Column("idacompanante")
    @CassandraType(type = CassandraType.Name.UUID)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private UUID id;

    @ApiModelProperty(value = "Son los nombres del acompanante del paciente", dataType = "ascii", position = 2)
    @NotEmpty(message = "Los nombres no pueden ser vacio")
    @NotNull(message = "Los nombres no pueden ser nulo")
    @Column("nombres")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String nombres;

    @ApiModelProperty(value = "Los apellidos de la acompanante", dataType = "ascii", position = 3)
    @NotEmpty(message = "Los apellidos no pueden ser vacio")
    @NotNull(message = "Los apellidos no pueden ser nulo")
    @Column("apellidos")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String apellidos;

    @ApiModelProperty(value = "Es el número del documento de identificación del acompanante del paciente", dataType = "ascii", position = 4)
    @NotEmpty(message = "El número del documento no puede ser vacio")
    @NotNull(message = "El número del documento no puede ser nulo")
    @Column("docnum")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String docnum;

    @ApiModelProperty(value = "Es el tipo de documento de identificación del acompanante del paciente", dataType = "ascii", position = 5)
    @NotEmpty(message = "El tipo de documento no puede ser vacio")
    @NotNull(message = "El tipo de documento no puede ser nulo")
    @Column("doctipo")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String doctipo;

    @ApiModelProperty(value = "Es la fecha de nacimiento del acompanante del paciente", dataType = "date", position = 6)
    @NotNull(message = "La fecha de nacimiento no puede ser nulo")
    @Column("fecnac")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate fecnac;

    @ApiModelProperty(value = "Es el teléfono del acompanante del paciente", dataType = "ascii", position = 7)
    @NotEmpty(message = "El telefono no puede ser vacio")
    @NotNull(message = "El telefono no puede ser nulo")
    @Column("telefono")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String telefono;

    @ApiModelProperty(value = "Es el grado de instrucción del acompanante del paciente", dataType = "ascii", position = 8)
    @NotEmpty(message = "El grado de instrucción no puede ser vacio")
    @NotNull(message = "El grado de instrucción no puede ser nulo")
    @Column("gradoinstruccion")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String gradoinstruccion;

    @ApiModelProperty(value = "Es la ocupación del acompanante del paciente", dataType = "ascii", position = 9)
    @NotEmpty(message = "La ocupacion no puede ser vacio")
    @NotNull(message = "La ocupacion no puede ser nulo")
    @Column("ocupacion")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String ocupacion;

    @ApiModelProperty(value = "Es la relacion de parentezco entre el paciente y su acompanante", dataType = "ascii", position = 10)
    @NotEmpty(message = "El parentezco no puede ser vacio")
    @NotNull(message = "El parentezco no puede ser nulo")
    @Column("parentezco")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String parentezco;
}
