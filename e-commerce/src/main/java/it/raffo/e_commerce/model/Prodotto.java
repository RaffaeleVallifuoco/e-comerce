package it.raffo.e_commerce.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Prodotto")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Campo Obbligatorio")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "descrizione", length = 600, nullable = true)
    private String descrizione;

    @NotNull(message = "Campo Obbligatorio")
    @Column(name = "prezzo", nullable = false)
    private Double prezzo;

    @NotNull(message = "Campo Obbligatorio")
    @Column(name = "quantita", nullable = false)
    private Integer quantita;

    @NotNull(message = "Campo Obbligatorio")
    @Column(name = "data_produzione", nullable = false)
    private LocalDate dataProduzione;

    @Column(name = "foto_path")
    private String photoPath;

    @NotNull(message = "Campo Obbligatorio")
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @Column(name = "evidence")
    private boolean evidence;

    // CONSTRUCTORS

    public Prodotto() {
    }

    public Prodotto(Integer id, String nome, String descrizione, Double prezzo, Integer quantita,
            LocalDate dataProduzione, Categoria categoria, Marca marca, String foto, boolean evidence) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.dataProduzione = dataProduzione;
        this.categoria = categoria;
        this.marca = marca;
        this.photoPath = foto;
        this.evidence = evidence;
    }

    // GETTER & SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public LocalDate getDataProduzione() {
        return dataProduzione;
    }

    public void setDataProduzione(LocalDate dataProduzione) {
        this.dataProduzione = dataProduzione;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public boolean isEvidence() {
        return evidence;
    }

    public void setEvidence(boolean evidence) {
        this.evidence = evidence;
    }

    // TOSTRING - EQUALS - HASHCODE

    @Override
    public String toString() {
        return "Prodotto [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", prezzo=" + prezzo
                + ", quantita=" + quantita + ", dataProduzione=" + dataProduzione + ", categoria=" + categoria
                + ", marca=" + marca + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
        result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
        result = prime * result + ((quantita == null) ? 0 : quantita.hashCode());
        result = prime * result + ((dataProduzione == null) ? 0 : dataProduzione.hashCode());
        result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Prodotto other = (Prodotto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (descrizione == null) {
            if (other.descrizione != null)
                return false;
        } else if (!descrizione.equals(other.descrizione))
            return false;
        if (prezzo == null) {
            if (other.prezzo != null)
                return false;
        } else if (!prezzo.equals(other.prezzo))
            return false;
        if (quantita == null) {
            if (other.quantita != null)
                return false;
        } else if (!quantita.equals(other.quantita))
            return false;
        if (dataProduzione == null) {
            if (other.dataProduzione != null)
                return false;
        } else if (!dataProduzione.equals(other.dataProduzione))
            return false;
        if (categoria == null) {
            if (other.categoria != null)
                return false;
        } else if (!categoria.equals(other.categoria))
            return false;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        return true;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

}
