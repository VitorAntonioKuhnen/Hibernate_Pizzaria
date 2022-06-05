/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.util;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.Pedido;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author silvio.junior
 */
public class GeradorUtil {

    public static String gerarNumero(int qtde) {
        String numeroGerado = "";
        int indice;
        for (int i = 0; i < qtde; i++) {
            indice = (int) (Math.random() * 10);
            numeroGerado += indice;
        }
        return numeroGerado;
    }

    public static String gerarCpf() {
        return gerarNumero(3) + "." + gerarNumero(3) + "." + gerarNumero(3) + "-" + gerarNumero(2);
    }

    public static String gerarCnpj() {
        return gerarNumero(2) + "." + gerarNumero(3) + "." + gerarNumero(3) + "/" + gerarNumero(4)
                + "-" + gerarNumero(2);
    }

    public static String gerarTelefoneFixo() {
        return "(48)3" + gerarNumero(3) + "-" + gerarNumero(4);
    }

    public static String gerarCelular() {
        return "(48)9" + gerarNumero(4) + "-" + gerarNumero(4);
    }

    public static String gerarCep() {
        return gerarNumero(5) + "-" + gerarNumero(3);
    }

    public static String gerarNome() {
        String[] nomes = {"Junior", "Marcos", "Ana", "Maria", "Silvio", "Suelen", "Joana", "Mateus",
            "Lúcio", "João", "Leandro", "Soeli"};
        int indice = (int) (Math.random() * nomes.length);
        return nomes[indice] + " " + gerarSobrenome();
    }

    private static String gerarSobrenome() {
        String[] sobrenomes = {"Pereira", "Oliveira", "Antunes", "da Silva", "Santos", "Rocha", "Moura",
            "Dias", "Mendes", "Albino", "Dutra", "Mendonça"};
        int indice = (int) (Math.random() * sobrenomes.length);
        return sobrenomes[indice];
    }

    public static String gerarCidade() {
        String[] cidades = {"São José", "Palhoça", "Florianópolis", "Criciuma", "Chapecó", "Curitiba",
            "Porto Alegre", "São Paulo", "Máceio", "Biguaçú", "Belo Horizonte", "Pinhais"};
        int indice = (int) (Math.random() * cidades.length);
        return cidades[indice];
    }

    public static String gerarLogin() {
        String nome = gerarNome();
        return nome.toLowerCase() + "@";
    }

    public static String gerarSenha(int qtde) {
        String[] letras = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0",
            "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int indice;
        String senha = "";
        for (int i = 0; i < qtde; i++) {
            indice = (int) (Math.random() * letras.length);
            senha += letras[indice];
        }
        return senha;
    }

    public static String gerarBairro() {

        List<String> listaBairros = Arrays.asList("Centro Histórico",
                "Ponta de Baixo", "Distrito Industrial", "Picadas do Sul",
                "Flor de Nápolis", "São Luiz", "Roçado", "Potecas", "Serraria");

        Collections.shuffle(listaBairros);

        return listaBairros.get(0);

    }

    public static String gerarPais() {

        List<String> paises = Arrays.asList("brasil", "argentina", "uruguai",
                "paraguai", "estados unidos", "canadá", "mexico", "bolivia",
                "peru", "chile");

        Collections.shuffle(paises);

        return paises.get(0);

    }

    public static String gerarEstado() {

        List<String> paises = Arrays.asList("Santa carartina", "Parana", "São paulo",
                "Rio grande do sul", "Paraiba", "Pernanbuco", "Amazonia", "Acre",
                "Recife", "Rio de Janeiro");

        Collections.shuffle(paises);

        return paises.get(0);

    }

    public static Endereco gerarEndereco() {
        Endereco end = new Endereco("Rua Eliane Gerlach", gerarBairro(), gerarNumero(2), gerarCidade(), "Casa", gerarCep());
        return end;
    }

    public static Pedido gerarPedido() {
        Date data = new Date();
        
        BigDecimal bigDec = new BigDecimal(gerarNumero(3));
        Pedido pedido = new Pedido(Integer.parseInt(gerarNumero(2)), bigDec, data);

        return pedido;
    }

    public static void main(String[] args) {
        System.out.println("Cidade: " + gerarCidade());
    }
}
