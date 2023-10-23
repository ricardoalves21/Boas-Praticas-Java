package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;

import java.util.List;

public record DadosDetalhamentoAbrigo(
    Long id,
    String nome,
    String telefone,
    String email,
    List<Pet> pets
) {
    public DadosDetalhamentoAbrigo(Abrigo abrigo) {
            this(
                    abrigo.getId(),
                    abrigo.getNome(),
                    abrigo.getTelefone(),
                    abrigo.getEmail(),
                    abrigo.getPets()
            );
    }
}
