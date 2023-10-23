package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoPet tipo;

    @NotBlank
    private String nome;

    @NotBlank
    private String raca;

    @NotNull
    private Integer idade;

    @NotBlank
    private String cor;

    @NotNull
    private Float peso;

    private Boolean adotado;

    // Muitas PETS para um único ABRIGO
    @ManyToOne
    @JsonBackReference("abrigo_pets")
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    // um único PET para uma única ADOÇÃO
    @OneToOne(mappedBy = "pet")
    @JsonBackReference("adocao_pets")
    private Adocao adocao;

    public Pet(Long id, TipoPet tipo, String nome, String raca, Integer idade, String cor, Float peso, Boolean adotado, Abrigo abrigo, Adocao adocao) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
        this.adotado = adotado;
        this.abrigo = abrigo;
        this.adocao = adocao;
    }
}
