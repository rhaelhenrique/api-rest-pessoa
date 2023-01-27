package br.com.rhr.pessoa.model.exception;

import java.text.MessageFormat;

public class EnderecoExistenteException extends RuntimeException{
    public EnderecoExistenteException(final Long enderecoId, final Long pessoaId){
        super(MessageFormat.format("O endereço: {0} já existe para a pessoa: {1}.", enderecoId, pessoaId));
    }
}
