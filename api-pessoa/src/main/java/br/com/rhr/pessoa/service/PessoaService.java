package br.com.rhr.pessoa.service;

import br.com.rhr.pessoa.model.Endereco;
import br.com.rhr.pessoa.model.Pessoa;
import br.com.rhr.pessoa.model.exception.EnderecoExistenteException;
import br.com.rhr.pessoa.model.exception.PessoaNotFoundException;
import br.com.rhr.pessoa.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private EnderecoService enderecoService;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, EnderecoService enderecoService){
        this.pessoaRepository = pessoaRepository;
        this.enderecoService = enderecoService;
    }

    public Pessoa addPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarPessoa(){
        return StreamSupport
                .stream(pessoaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Pessoa buscarPessoaId(Long id){
        return pessoaRepository.findById(id).orElseThrow(() ->
                new PessoaNotFoundException(id));
    }

    public Pessoa deletePessoaId(Long id){
        Pessoa pessoa = buscarPessoaId(id);
        pessoaRepository.delete(pessoa);
        return pessoa;
    }

    @Transactional
    public Pessoa editarPessoa(Long id, Pessoa pessoa){
        Pessoa pessoaEdit = buscarPessoaId(id);
        pessoaEdit.setNome(pessoa.getNome());
        pessoaEdit.setNascimento(pessoa.getNascimento());
        return pessoaEdit;
    }

    @Transactional
    public Pessoa addEndecoPessoa(Long pessoaId, Long endeceroId){
        Pessoa pessoa = buscarPessoaId(pessoaId);
        Endereco endereco = enderecoService.buscarEnderecoId(endeceroId);
        if(Objects.nonNull(endereco.getPessoa())){
            throw new EnderecoExistenteException(endeceroId, endereco.getPessoa().getId());
        }
        pessoa.addEndereco(endereco);
        return pessoa;
    }

    @Transactional
    public Pessoa removeEnderecoPessoa(Long pessoaId, Long endeceroId){
        Pessoa pessoa = buscarPessoaId(pessoaId);
        Endereco endereco = enderecoService.buscarEnderecoId(endeceroId);
        pessoa.removeEndereco(endereco);
        return pessoa;
    }
}
