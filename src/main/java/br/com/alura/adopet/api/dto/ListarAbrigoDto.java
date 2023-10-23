package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotBlank;

public record ListarAbrigoDto(

        Long id,

        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotBlank
        String email
) {
        public ListarAbrigoDto(Abrigo abrigo) {
                this(
                        abrigo.getId(),
                        abrigo.getNome(),
                        abrigo.getTelefone(),
                        abrigo.getEmail()
                );
        }
}
