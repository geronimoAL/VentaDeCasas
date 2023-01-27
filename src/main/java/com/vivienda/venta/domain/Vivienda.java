
package com.vivienda.venta.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Vivienda {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;
    private Long precio;
    private Long banio;
    private Long dormitorio;
    private Long mt;
    private Long cochera;
    private Long ambiente;
    private String ubicacion;
    private String barrio;
    @OneToMany
    private List<Foto>foto;
    @OneToOne
    private Inmobiliaria inmobiliaria;
    @OneToOne
    private Provincia provincia;
    @OneToOne
    private Usuario usuario;
}
