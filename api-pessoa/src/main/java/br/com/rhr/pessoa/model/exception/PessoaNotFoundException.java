package br.com.rhr.pessoa.model.exception;

import java.text.MessageFormat;

public class PessoaNotFoundException extends RuntimeException{

    public PessoaNotFoundException(final Long id){
        super(MessageFormat.format("Não foi possível encontrar a pessoa com id: {0}", id));
    }
}
