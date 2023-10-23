package br.com.alura.adopet.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "adocoes")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    // Muitas AÇÕES para um único TUTOR
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    // Uma ADOÇÃO para um único PET
    // O 'FetchType.LAZY' diz pra JPA somente carregar este campo na consulta se ele for chamado.
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private String justificativaStatus;

    public Adocao(Tutor tutor, Pet pet, String motivo) {
        this.tutor = tutor;
        this.pet = pet;
        this.motivo = motivo;
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
        this.data = LocalDateTime.now();
    }

    public Adocao() {}

    public void marcarComoAprovado() {
        this.status = StatusAdocao.APROVADO;
    }

    public void marcarComoReprovada(String justificativa) {
        this.status = StatusAdocao.REPROVADO;
        this.justificativaStatus = justificativa;
    }
}
