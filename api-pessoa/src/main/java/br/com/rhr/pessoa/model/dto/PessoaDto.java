package br.com.rhr.pessoa.model.dto;

import br.com.rhr.pessoa.model.Endereco;
import br.com.rhr.pessoa.model.Pessoa;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PessoaDto {
    private Long id;
    private String nome;
    private LocalDate nascimento;
    private List<EnderecoDto> enderecosDtos = new ArrayList<>();

    public static PessoaDto from(Pessoa pessoa){
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId(pessoa.getId());
        pessoaDto.setNome(pessoa.getNome());
        pessoaDto.setNascimento(pessoa.getNascimento());
        pessoaDto.setEnderecosDtos(pessoa.getEnderecos().stream().map(EnderecoDto::from).collect(Collectors.toList()));
        return pessoaDto;
    }
}
