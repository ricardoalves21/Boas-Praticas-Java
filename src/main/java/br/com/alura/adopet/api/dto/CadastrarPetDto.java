package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.TipoPet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarPetDto(
        TipoPet tipo,

        @NotBlank
        String nome,

        @NotBlank
        String raca,

        @NotNull
        Integer idade,

        @NotBlank
        String cor,

        @NotNull
        Float peso,

        Boolean adotado,

        Abrigo abrigo,

        Adocao adocao
) {
}
