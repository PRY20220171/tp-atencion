package com.example.backfactoresriesgos.entity;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = FactoresRiesgo.class)
public class FactoresRiesgo  implements Serializable {

    @ApiModelProperty(value="ID de los factores de riesgo relacionados al motivo de atencion", dataType="uuid", position=1)
    @Id
    @Column("idfacriesgo")
    @CassandraType(type = CassandraType.Name.UUID)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private UUID id;

    @ApiModelProperty(value="Es el parentezco del cuidador del paciente", dataType="ascii", example="no tiene", position=5)
    @NotEmpty(message = "El cuidador no puede ser vacio")
    @NotNull(message = "El cuidador no puede ser nulo")
    @Column("cuidador")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String cuidador;

    @ApiModelProperty(value="Es la observacion sobre el apetito del paciente en el momento de la atencion", dataType="text", position=6)
    @NotEmpty(message = "El apetito no puede ser vacio")
    @NotNull(message = "El apetito no puede ser nulo")
    @Column("apetito")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String apetito;

    @ApiModelProperty(value="Es la observacion sobre la sed del paciente en el momento de la atencion", dataType="text", position=7)
    @NotEmpty(message = "La sed no puede ser vacio")
    @NotNull(message = "La sed no puede ser nulo")
    @Column( "sed")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String sed;

    @ApiModelProperty(value="Es la observacion sobre el sueno del paciente en el momento de la atencion", dataType="text", position=8)
    @NotEmpty(message = "El sueno no puede ser vacio")
    @NotNull(message = "El sueno no puede ser nulo")
    @Column("sueno")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String sueno;

    @ApiModelProperty(value="Es la observacion sobre la orina del paciente en el momento de la atencion", dataType="text", position=9)
    @NotEmpty(message = "La orina no puede ser vacio")
    @NotNull(message = "La orina no puede ser nulo")
    @Column("orina")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String orina;

    @ApiModelProperty(value="Es la observacion sobre las deposiciones del paciente en el momento de la atencion", dataType="text", position=4)
    @NotEmpty(message = "Las deposiciones no pueden ser vacio")
    @NotNull(message = "Las deposiciones no pueden ser nulo")
    @Column( "deposiciones")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String deposiciones;

    @ApiModelProperty(value="Es la observacion sobre el estado de ánimo del paciente en el momento de la atencion", dataType="ascii", position=5)
    @NotEmpty(message = "El estado de ánimo no puede ser vacio")
    @NotNull(message = "El estado de ánimo no puede ser nulo")
    @Column("estadoanimo")
    @CassandraType(type = CassandraType.Name.ASCII)
    private String estadoanimo;

    @ApiModelProperty(value="Es la fecha del ultimo dia de la regla del paciente - si tuviera", dataType="date", position=6)
    @Column("fecultregla")
    @CassandraType(type = CassandraType.Name.DATE)
    private Date fecultregla;
}
