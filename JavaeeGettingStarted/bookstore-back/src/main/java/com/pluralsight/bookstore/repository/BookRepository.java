package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.Book;
import java.util.List;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.*;

@Transactional(Transactional.TxType.SUPPORTS)
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager em;

    
    public Book find(@NotNull Long id) {
        return em.find(Book.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book create(@NotNull Book book) {
        em.persist(book);
        return book;
    }

     @Transactional(Transactional.TxType.REQUIRED)
    public void delete(@NotNull Long id) {
        em.remove(em.getReference(Book.class, id));
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ORDER BY b.title", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("SELECT count(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }
}
