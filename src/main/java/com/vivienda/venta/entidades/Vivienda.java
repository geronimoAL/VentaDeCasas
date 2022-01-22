
package com.vivienda.venta.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Vivienda {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;
    private String precio;
    private String banio;
    private String dormitorio;
    private String mt;
    private String cochera;
    private String ambiente;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getBanio() {
        return banio;
    }

    public void setBanio(String banio) {
        this.banio = banio;
    }

  
    public String getDormitorio() {
        return dormitorio;
    }

    public void setDormitorio(String dormitorio) {
        this.dormitorio = dormitorio;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getCochera() {
        return cochera;
    }

    public void setCochera(String cochera) {
        this.cochera = cochera;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }


    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

   

    public Inmobiliaria getInmobiliaria() {
        return inmobiliaria;
    }

    public void setInmobiliaria(Inmobiliaria inmobiliaria) {
        this.inmobiliaria = inmobiliaria;
    }

    @Override
    public String toString() {
        return "Vivienda{" + "id=" + id + ", precio=" + precio + ", banio=" + banio + ", dormitorios=" + dormitorio + ", mt=" + mt + ", cochera=" + cochera + ", ambiente=" + ambiente + ", ubicacion=" + ubicacion + ", barrio=" + barrio + ", foto=" + foto + ", inmobiliaria=" + inmobiliaria + ", provincia=" + provincia + '}';
    }
    
    
    
}
