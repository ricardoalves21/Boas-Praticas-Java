package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.dto.DadosDetalhamentoAbrigo;
import br.com.alura.adopet.api.dto.ListarAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<Page<ListarAbrigoDto>> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        var page = repository.findAll(paginacao)
                .map(ListarAbrigoDto::new);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarAbrigoDto dto, UriComponentsBuilder uriBuilder) {

        Abrigo abrigo = new Abrigo(dto);
        var uri = uriBuilder.path("/abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();

        try {
            abrigoService.cadastrar(dto, abrigo);
            return ResponseEntity.created(uri).body(new DadosDetalhamentoAbrigo(abrigo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro abrigo!" + e.getMessage());
        }

    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<Pet>> listarPets(@PathVariable String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            List<Pet> pets = repository.getReferenceById(id).getPets();
            return ResponseEntity.ok(pets);
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = repository.findByNome(idOuNome).getPets();
                return ResponseEntity.ok(pets);
            } catch (EntityNotFoundException enfe) {
                return ResponseEntity.notFound().build();
            }
        }
    }

//    @PostMapping("/{idOuNome}/pets")
//    @Transactional
//    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid Pet pet) {
//        try {
//            Long id = Long.parseLong(idOuNome);
//            Abrigo abrigo = repository.getReferenceById(id);
//            pet.setAbrigo(abrigo);
//            pet.setAdotado(false);
//            abrigo.getPets().add(pet);
//            repository.save(abrigo);
//            return ResponseEntity.ok().build();
//        } catch (EntityNotFoundException enfe) {
//            return ResponseEntity.notFound().build();
//        } catch (NumberFormatException nfe) {
//            try {
//                Abrigo abrigo = repository.findByNome(idOuNome);
//                pet.setAbrigo(abrigo);
//                pet.setAdotado(false);
//                abrigo.getPets().add(pet);
//                repository.save(abrigo);
//                return ResponseEntity.ok().build();
//            } catch (EntityNotFoundException enfe) {
//                return ResponseEntity.notFound().build();
//            }
//        }
//    }

}
