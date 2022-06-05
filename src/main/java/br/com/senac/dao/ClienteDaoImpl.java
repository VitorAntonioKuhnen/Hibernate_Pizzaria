/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author vitor.kuhnen
 */
public class ClienteDaoImpl extends BaseDaoImpl<Cliente, Long> implements ClienteDao, Serializable {

    @Override
    public Cliente pesquisarPorId(Long id, Session session) throws HibernateException {
        return session.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> askPerName(String nome, Session session) throws HibernateException {
        Query<Cliente> consult = session.createQuery("from Cliente c join fetch c.enderecos where c.nome like :nome");
        consult.setParameter("nome", "%" + nome + "%");
        return consult.getResultList();
    }

    @Override
    public Cliente askPerTell(String telefone, Session session) throws HibernateException {
        Query<Cliente> consult = session.createQuery("from Cliente c join fetch c.pedidos where c.telefone = :telefone");
        consult.setParameter("telefone", telefone);
        return consult.getSingleResult();
    }
    
}
