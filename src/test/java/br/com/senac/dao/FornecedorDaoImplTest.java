/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.Fornecedor;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import static br.com.senac.util.GeradorUtil.*;
import java.util.ArrayList;
import org.hibernate.query.Query;

/**
 *
 * @author vitor.kuhnen
 */
public class FornecedorDaoImplTest {
    private Session session;
    private Fornecedor fornecedor;
    private FornecedorDao fornecedorDao;
    
    
    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    }
    
//    @Test
    public void testSalvar(){
        System.out.println("Salvar");
        
        fornecedor = new Fornecedor(gerarCnpj(), "testando bd", gerarNome(), gerarNome() + "@gmail.com", gerarCelular());
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(gerarEndereco());
        fornecedor.setEnderecos(enderecos);
        
        for (Endereco endereco : enderecos) {
            endereco.setPessoa(fornecedor);
        }
        session = HibernateUtil.abrirConexao();
        fornecedorDao.saveOrAlter(fornecedor, session);
        session.close();
        
        assertNotNull(fornecedor.getId());
        
    }
    
//    @Test
    public void testAlterar(){
        System.out.println("Alterar");
        buscaFornecerdoBd();
        fornecedor.setInscricao_estadual("Atualizado em 2022");
        session = HibernateUtil.abrirConexao();
        fornecedorDao.saveOrAlter(fornecedor, session);
        
        Fornecedor fornecedorAlt = fornecedorDao.pesquisarPorId(fornecedor.getId(), session);
        session.close();
        
        assertEquals(fornecedorAlt.getId(), fornecedor.getId());
    }
    
//    @Test
    public void testExcluir(){
        System.out.println("Excluir");
        buscaFornecerdoBd();
        session = HibernateUtil.abrirConexao();
        fornecedorDao.excluir(fornecedor, session);
        
        Fornecedor fornecedorExc = fornecedorDao.pesquisarPorId(fornecedor.getId(), session);
        session.close();
        
        assertNull(fornecedorExc);
    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscaFornecerdoBd();
        session = HibernateUtil.abrirConexao();
        Fornecedor fornecedorAskId = fornecedorDao.pesquisarPorId(fornecedor.getId(), session);
        session.close();
        
        assertNotNull(fornecedorAskId);
        System.out.println("Nome: " + fornecedorAskId.getNome());
    }

//    @Test
    public void testAskPerName() {
        System.out.println("askPerName");
        buscaFornecerdoBd();
        session = HibernateUtil.abrirConexao();
        List<Fornecedor> fornecedores = fornecedorDao.askPerName(fornecedor.getNome(), session);
        session.close();
        
        assertFalse(fornecedores.isEmpty());
        System.out.println("Nome: " + fornecedores.get(0).getNome());
    }
    
    @Test
    public void testAskPerTell() {
        System.out.println("PesquisaPorTelefone");
        buscaFornecerdoBd();
        session = HibernateUtil.abrirConexao();
        Fornecedor fornecedorTell = fornecedorDao.askPerTell(fornecedor.getTelefone(), session);
        session.close();
        assertNotNull(fornecedorTell);
        
        System.out.println("Telefone: " + fornecedorTell.getTelefone());
    }
    
    public Fornecedor buscaFornecerdoBd(){
        session = HibernateUtil.abrirConexao();
        Query<Fornecedor> consult = session.createQuery("from Fornecedor f");
        List<Fornecedor> fornecedores = consult.getResultList();
        session.close();
        if (fornecedores.isEmpty()){
            testSalvar();
        } else {
            fornecedor = fornecedores.get(0);
        }
        return fornecedor;
    }

}
