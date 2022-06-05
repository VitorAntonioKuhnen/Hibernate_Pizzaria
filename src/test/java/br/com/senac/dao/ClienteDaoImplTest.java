/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.Pedido;
import static br.com.senac.util.GeradorUtil.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vitor.kuhnen
 */
public class ClienteDaoImplTest {

    private Session session;
    private Cliente cliente;
    private ClienteDao clienteDao;

    public ClienteDaoImplTest() {

        clienteDao = new ClienteDaoImpl();
    }

//    @Test
    public void testSalvar() {
        System.out.println("Salvar");
        cliente = new Cliente(false, gerarNome(), gerarNome() + "@gmail.com", gerarNumero(9));
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(gerarEndereco());
        cliente.setEnderecos(enderecos);

        for (Endereco endereco : enderecos) {
            endereco.setPessoa(cliente);
        }

        session = HibernateUtil.abrirConexao();
        clienteDao.saveOrAlter(cliente, session);
        session.close();
        assertNotNull(cliente.getId());

    }
    
//    @Test
    public void testAlterar(){
        System.out.println("Alterar");
        buscarClienteBd();
        cliente.setEmail(gerarNome() + "@gmail.com");
        session = HibernateUtil.abrirConexao();
        clienteDao.saveOrAlter(cliente, session);
        session.close();
        
        assertNotNull(cliente.getId());
        System.out.println("Email: " + cliente.getEmail());
    }
    
//    @Test
    public void testExcluir(){
        System.out.println("Excluir");
        buscarClienteBd();
        session = HibernateUtil.abrirConexao();
        clienteDao.excluir(cliente, session);
        
        Cliente clienteExc = clienteDao.pesquisarPorId(cliente.getId(), session);
        session.close();
        assertNull(clienteExc);
    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarClienteBd();
        session = HibernateUtil.abrirConexao();
        Cliente clienteAskId = clienteDao.pesquisarPorId(cliente.getId(), session);
        session.close();
        
        assertNotNull(clienteAskId);
        System.out.println(clienteAskId.getId() + " " + clienteAskId.getNome());
    }

//    @Test
    public void testAskPerName() {
        System.out.println("askPerName");
        buscarClienteBd();
        session = HibernateUtil.abrirConexao();
        List<Cliente> clienteName = clienteDao.askPerName(cliente.getNome(), session);
        session.close();
        assertFalse(clienteName.isEmpty());
        
        // Descobrir como pegar o endereço da lista de endereço
        System.out.println(cliente.getId() + " " + cliente.getNome());
        
    }

    

    @Test
    public void testAskPerTel() {
        System.out.println("askPerTel");
        buscarClienteBd();

        session = HibernateUtil.abrirConexao();
        Cliente clienteTel = clienteDao.askPerTell(cliente.getTelefone(), session);
        session.close();

        assertNotNull(clienteTel);
        assertTrue(!clienteTel.getPedidos().isEmpty());

        System.out.println(cliente.getNome());
    }

    public Cliente buscarClienteBd() {
        session = HibernateUtil.abrirConexao();
        Query<Cliente> consult = session.createQuery("from Cliente c");
        List<Cliente> clientes = consult.getResultList();
        session.close();
        if (clientes.isEmpty()) {
            testSalvar();
        } else {
            cliente = clientes.get(0);
        }
        return cliente;
    }

}
