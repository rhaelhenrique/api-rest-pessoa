package br.com.rhr.pessoa.http.controller;

import br.com.rhr.pessoa.model.Pessoa;
import br.com.rhr.pessoa.model.dto.PessoaDto;
import br.com.rhr.pessoa.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaDto> addPessoa(@RequestBody final PessoaDto pessoaDto){
        Pessoa pessoa = pessoaService.addPessoa(Pessoa.from(pessoaDto));
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDto>> listarPessoa(){
        List<Pessoa> pessoas = pessoaService.listarPessoa();
        List<PessoaDto> pessoasDto = pessoas.stream().map(PessoaDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(pessoasDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PessoaDto> buscarPessoaId(@PathVariable final Long id){
        Pessoa pessoa = pessoaService.buscarPessoaId(id);
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PessoaDto> deletePessoaId(@PathVariable final Long id){
        Pessoa pessoa = pessoaService.deletePessoaId(id);
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<PessoaDto> editarPessoa(@PathVariable final Long id,
                                                  @RequestBody final PessoaDto pessoaDto){
        Pessoa pessoa = pessoaService.editarPessoa(id, Pessoa.from(pessoaDto));
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @PostMapping(value = "{pessoaId}/enderecos/{enderecoId}/add")
    public ResponseEntity<PessoaDto> addEndecoPessoa(@PathVariable final Long pessoaId,
                                                     @PathVariable final Long enderecoId){
        Pessoa pessoa = pessoaService.addEndecoPessoa(pessoaId, enderecoId);
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }

    @DeleteMapping(value = "{pessoaId}/enderecos/{enderecoId}/remove")
    public ResponseEntity<PessoaDto> removeEnderecoPessoa(@PathVariable final Long pessoaId,
                                                     @PathVariable final Long enderecoId){
        Pessoa pessoa = pessoaService.removeEnderecoPessoa(pessoaId, enderecoId);
        return new ResponseEntity<>(PessoaDto.from(pessoa), HttpStatus.OK);
    }
}
