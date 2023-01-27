package br.com.rhr.pessoa.model.dto;

import br.com.rhr.pessoa.model.Pessoa;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PlainPessoaDto {
    private Long id;
    private String nome;
    private LocalDate nascimento;

    public static PlainPessoaDto from(Pessoa pessoa){
        PlainPessoaDto plainPessoaDto = new PlainPessoaDto();
        plainPessoaDto.setId(pessoa.getId());
        plainPessoaDto.setNome(pessoa.getNome());
        plainPessoaDto.setNascimento(pessoa.getNascimento());
        return plainPessoaDto;
    }
}
