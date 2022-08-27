package com.example.backatencioness.entity;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Atenciones.class)
public class Atenciones  implements Serializable {

    @ApiModelProperty(value="ID de la atencion medica", dataType="uuid", position=1)
    @Id
    @Column("idatencion")
    @CassandraType(type = CassandraType.Name.UUID)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private UUID id;

    @ApiModelProperty(value="Es el identificador del paciente", dataType="uuid", position=2)
    @NotEmpty(message = "El identificador del paciente no puede ser vacio")
    @NotNull(message = "El identificador del paciente no puede ser nulo")
    @Column("idpaciente")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID idpaciente;

    @ApiModelProperty(value="Es el identificador del usuario que registro la atencion", dataType="uuid", position=3)
    @NotEmpty(message = "El identificador del usuario no puede ser vacio")
    @NotNull(message = "el identificador del usuario no puede ser nulo")
    @Column("idusuarioregistro")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID idusuarioregistro;

    @ApiModelProperty(value="Es la fecha en la que se dio la atencion del paciente", dataType="timestamp", position=4)
    @NotEmpty(message = "La fecha de registro no puede ser vacio")
    @NotNull(message = "La fecha de registro no puede ser nulo")
    @Column( "fecharegistro")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private String fecharegistro;

    @ApiModelProperty(value="Es la descripcion del motivo de la atencion", dataType="text", position=5)
    @NotEmpty(message = "El motivo de la consulta no puede ser vacio")
    @NotNull(message = "El motivo de la consulta no puede ser nulo")
    @Column("motivoconsulta")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String motivoconsulta;

    @ApiModelProperty(value="Son las observaciones de la atencion dada", dataType="text", position=6)
    @NotEmpty(message = "Las observaciones no pueden ser vacio")
    @NotNull(message = "Las observaciones no pueden ser nulo")
    @Column("observaciones")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String observaciones;

    @ApiModelProperty(value="Es el tiempo en el que el paciente lleva padeciendo los sintomas por los que se atiende", dataType="ascii", position=7)
    @NotEmpty(message = "El tiempo de enfermedad no puede ser vacio")
    @NotNull(message = "El tiempo de enfermedad no puede ser nulo")
    @Column("tiempoenfermedad")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String tiempoenfermedad;

    @ApiModelProperty(value="Es la forma en la que iniciaron los sintomas que padece el paciente", dataType="text", position=8)
    @NotEmpty(message = "La forma de inicio no puede ser vacio")
    @NotNull(message = "La forma de inicio no puede ser nulo")
    @Column("formainicio")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String formainicio;

    @ApiModelProperty(value="Es la forma en la que se desarrolla la enfermedad del paciente", dataType="ascii", position=9)
    @NotEmpty(message = "El curso de la enfermedad no puede ser vacio")
    @NotNull(message = "El curso de la enfermedad no puede ser nulo")
    @Column( "curso")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String curso;

    @ApiModelProperty(value="Es el identificador del acompanante del paciente", dataType="uuid", position=10)
    @NotEmpty(message = "El ID del acompanante no puede ser vacio")
    @NotNull(message = "El ID del acompanante no puede ser nulo")
    @Column("idacompanante")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID idacompanante;

    @ApiModelProperty(value="Es el identificador de los factores de riesgo relacionados al motivo de atencion", dataType="uuid", position=11)
    @NotEmpty(message = "El ID de los factores de riesgo no puede ser vacio")
    @NotNull(message = "El ID de los factores de riesgo no puede ser nulo")
    @Column("idfacriesgo")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID idfacriesgo;
    
    @ApiModelProperty(value="Es el identificador del centro médico en el que se realiza la atencion", dataType="uuid", position=12)
    @NotEmpty(message = "El ID del centro médico no puede ser vacio")
    @NotNull(message = "El ID del centro médico no puede ser nulo")
    @Column("idcentromedic")
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID idcentromedic;
}
