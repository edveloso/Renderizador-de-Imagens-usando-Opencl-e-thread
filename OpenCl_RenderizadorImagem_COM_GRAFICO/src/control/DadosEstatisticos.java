/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


/**
 *
 * @author Carlos / andre
 */
public class DadosEstatisticos {

    private String descricao;
    private float largura;
    private float altura;
    private float tempoGasto;

    public DadosEstatisticos(String descricao, float largura, float altura, float tempoGasto) {
        this.descricao = descricao;
        this.largura = largura;
        this.altura = altura;
        this.tempoGasto = tempoGasto;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTempoGasto(float tempoGasto) {
        this.tempoGasto = tempoGasto;
    }

    public float getAltura() {
        return altura;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getLargura() {
        return largura;
    }

    public float getTempoGasto() {        
        return tempoGasto;
    }

    public String getDimensoes() {
        return (int)this.largura + " x " + (int)this.altura;
    }
    public String getInfoDetalhada() {
        return "Ação Executada:  " + getDescricao() + "   Dimensões: " + getDimensoes() + "   Tempo Gasto: " + getTempoGasto()+" (ms)";
    }
    @Override
    public String toString() {
        return "Ação:  " + getDescricao() + " Dimensões: " + getDimensoes() + " Altura: " + getAltura() + " Largura: " + getLargura() + " Tempo Gasto: " + getTempoGasto();
    }

}
