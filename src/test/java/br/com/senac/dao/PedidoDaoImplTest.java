/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Pedido;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;
import static br.com.senac.util.GeradorUtil.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import org.hibernate.query.Query;

/**
 *
 * @author vitor.kuhnen
 */
public class PedidoDaoImplTest {

    private Session session;
    private Pedido pedido;
    private PedidoDao pedidoDao;

    public PedidoDaoImplTest() {
        pedidoDao = new PedidoDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("Salvar");
        
        Date data = new Date();
        BigDecimal bigDec = new BigDecimal(gerarNumero(3));
        
        ClienteDaoImplTest cdit = new ClienteDaoImplTest();
        pedido = new Pedido(Integer.parseInt(gerarNumero(2)), bigDec, data);
        
        pedido.setCliente(cdit.buscarClienteBd());
        session = HibernateUtil.abrirConexao();
        pedidoDao.saveOrAlter(pedido, session);
        session.close();

        assertNotNull(pedido.getId());

    }

//    @Test
    public void testAlterar() {
        System.out.println("Alterar");
        BigDecimal bigDec = new BigDecimal(gerarNumero(2));
        buscarPedidoBd();
        pedido.setValorTotal(bigDec);
        session = HibernateUtil.abrirConexao();
        pedidoDao.saveOrAlter(pedido, session);
        session.close();
        
        session = HibernateUtil.abrirConexao();
        Pedido pedidoAlt = pedidoDao.pesquisarPorId(pedido.getId(), session);
        session.close();
        assertEquals(pedidoAlt.getId(), pedido.getId());

        System.out.println("ID: " + pedido.getId() + " Valor: R$" + pedido.getValorTotal() + "\nID: " + pedidoAlt.getId() + " Valor: R$" + pedidoAlt.getValorTotal());
    }
    
//    @Test
    public void testExcluir(){
        System.out.println("Excluir");
        buscarPedidoBd();
        session = HibernateUtil.abrirConexao();
        pedidoDao.excluir(pedido, session);
        
        Pedido pedidoExc = pedidoDao.pesquisarPorId(pedido.getId(), session);
        session.close();
        
        assertNull(pedidoExc);
        
    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarPedidoBd();
        session = HibernateUtil.abrirConexao();
        Pedido pedidoAskID = pedidoDao.pesquisarPorId(pedido.getId(), session);
        session.close();
        
        assertNotNull(pedidoAskID);
        System.out.println(pedidoAskID.getId() + " " + pedidoAskID.getCliente().getNome() + " " + pedidoAskID.getValorTotal());
    }

    public Cliente buscarClienteBd() {
        session = HibernateUtil.abrirConexao();
        Query<Cliente> consulta = session.createQuery("from Cliente c");
        List<Cliente> clientes = consulta.getResultList();
        session.close();
        Collections.shuffle(clientes);
        return clientes.get(0);
    }

    public Pedido buscarPedidoBd() {
        session = HibernateUtil.abrirConexao();
        Query<Pedido> consult = session.createQuery("from Pedido p");
        List<Pedido> pedidos = consult.getResultList();
        session.close();
        if (pedidos.isEmpty()) {
            testSalvar();
        } else {
            pedido = pedidos.get(0);
        }
        return pedido;
    }

}
