package br.com.rhr.pessoa.model.dto;

import br.com.rhr.pessoa.model.Endereco;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Objects;

@Data
public class EnderecoDto {
    private Long id;
    private String logradouro;
    private String numero;
    private String cep;
    private String cidade;
    private Boolean principal = false;
    private PlainPessoaDto plainPessoaDto;

    public static EnderecoDto from(Endereco endereco){
        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setId(endereco.getId());
        enderecoDto.setLogradouro(endereco.getLogradouro());
        enderecoDto.setNumero(endereco.getNumero());
        enderecoDto.setCep(endereco.getCep());
        enderecoDto.setCidade(endereco.getCidade());
        enderecoDto.setPrincipal(endereco.getPrincipal());
        if(Objects.nonNull(endereco.getPessoa())){
            enderecoDto.setPlainPessoaDto(PlainPessoaDto.from(endereco.getPessoa()));
        }
        return enderecoDto;
    }
}
