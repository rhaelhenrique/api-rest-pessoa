package br.com.rhr.pessoa.http.controller;

import br.com.rhr.pessoa.model.Endereco;
import br.com.rhr.pessoa.model.dto.EnderecoDto;
import br.com.rhr.pessoa.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> addEndereco(@RequestBody final EnderecoDto enderecoDto){
        Endereco endereco = enderecoService.addEndereco(Endereco.from(enderecoDto));
        return new ResponseEntity<>(EnderecoDto.from(endereco), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDto>> listarEndereco(){
        List<Endereco> enderecos = enderecoService.listarEndereco();
        List<EnderecoDto> enderecosDto = enderecos.stream().map(EnderecoDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(enderecosDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EnderecoDto> buscarEnderecoId(@PathVariable final Long id){
        Endereco endereco = enderecoService.buscarEnderecoId(id);
        return new ResponseEntity<>(EnderecoDto.from(endereco), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EnderecoDto> deleteEnderecoId(@PathVariable final Long id){
        Endereco endereco = enderecoService.deleteEnderecoId(id);
        return new ResponseEntity<>(EnderecoDto.from(endereco), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EnderecoDto> editarEndereco(@PathVariable final Long id,
                                                      @RequestBody final EnderecoDto enderecoDto){
        Endereco enderecoEditado = enderecoService.editarEndereco(id, Endereco.from(enderecoDto));
        return new ResponseEntity<>(EnderecoDto.from(enderecoEditado), HttpStatus.OK);
    }
}
