package com.github.ticoyk.mvchallenge.model;

public class ApiErro {

    private String mensagem;
    private String debug;
    
    public ApiErro(String mensagem, String debug) {
        this.mensagem = mensagem;
        this.debug = debug;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

}
