package br.com.rhr.pessoa.model.exception;

import java.text.MessageFormat;

public class EnderecoNotFoundException extends RuntimeException{

    public EnderecoNotFoundException(final Long id){
        super(MessageFormat.format("Não foi possível encontrar o endereço com id: {0}", id));
    }

}
