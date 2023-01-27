package br.com.rhr.pessoa.model;

import br.com.rhr.pessoa.model.dto.PessoaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    private LocalDate nascimento;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "end_pessoa", referencedColumnName = "id")
    List <Endereco> enderecos = new ArrayList<>();

    public void addEndereco(Endereco endereco) {
        enderecos.add(endereco);
    }

    public void removeEndereco(Endereco endereco) {
        enderecos.remove(endereco);
    }

    public static Pessoa from(PessoaDto pessoaDto){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setNascimento(pessoaDto.getNascimento());
        return pessoa;
    }
}
