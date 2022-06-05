/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Fornecedor;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class FornecedorDaoImpl extends BaseDaoImpl<Fornecedor, Long> implements FornecedorDao, Serializable {

    @Override
    public Fornecedor pesquisarPorId(Long id, Session session) throws HibernateException {
        return session.find(Fornecedor.class, id);
    }

    @Override
    public List<Fornecedor> askPerName(String nome, Session session) throws HibernateException {
        Query<Fornecedor> consult = session.createQuery("from Fornecedor f where f.nome like :nome");
        consult.setParameter("nome","%" + nome + "%");
        return consult.getResultList();
    }
    
    @Override
    public Fornecedor askPerTell(String telefone, Session session) throws HibernateException {
        Query<Fornecedor> consult = session.createQuery("from Fornecedor f where f.telefone = :telefone");
        consult.setParameter("telefone", telefone);
        return consult.getSingleResult();
    }

    
}
