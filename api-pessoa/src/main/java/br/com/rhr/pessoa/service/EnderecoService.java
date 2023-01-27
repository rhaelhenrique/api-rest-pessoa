package br.com.rhr.pessoa.service;

import br.com.rhr.pessoa.model.Endereco;
import br.com.rhr.pessoa.model.exception.EnderecoNotFoundException;
import br.com.rhr.pessoa.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco addEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarEndereco(){
        return StreamSupport
                .stream(enderecoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Endereco buscarEnderecoId(Long id){
        return enderecoRepository.findById(id).orElseThrow(() ->
                new EnderecoNotFoundException(id));
    }

    public Endereco deleteEnderecoId(Long id){
        Endereco endereco = buscarEnderecoId(id);
        enderecoRepository.delete(endereco);
        return endereco;
    }

    @Transactional
    public Endereco editarEndereco(Long id, Endereco endereco){
        Endereco enderecoEdit = buscarEnderecoId(id);
        enderecoEdit.setLogradouro(endereco.getLogradouro());
        enderecoEdit.setNumero(endereco.getNumero());
        enderecoEdit.setCep(endereco.getCep());
        enderecoEdit.setCidade(endereco.getCidade());
        enderecoEdit.setPrincipal(endereco.getPrincipal());
        return enderecoEdit;
    }

}
