package br.com.rhr.pessoa.model;

import br.com.rhr.pessoa.model.dto.EnderecoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Entity
    public class Endereco implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "logradouro", nullable = false)
        private String logradouro;

        private String numero;
        private String cep;
        private String cidade;
        private Boolean principal = false;
        @ManyToOne
        private Pessoa pessoa;

        public static Endereco from(EnderecoDto enderecoDto){
            Endereco endereco = new Endereco();
            endereco.setLogradouro(enderecoDto.getLogradouro());
            endereco.setNumero(enderecoDto.getNumero());
            endereco.setCep(enderecoDto.getCep());
            endereco.setCidade(enderecoDto.getCidade());
            endereco.setPrincipal(enderecoDto.getPrincipal());
            return endereco;
        }
}
