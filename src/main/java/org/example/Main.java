package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entity.Comment;
import org.example.persistence.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String,String> props = new HashMap<>();

        props.put("hibernate.show_sql","true");
        props.put("hibernate.hbm2ddl.auto","update");


//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new PersistenceUnitInfo(),props);
        EntityManager entityManager = emf.createEntityManager();//represent the context
        try {
            entityManager.getTransaction().begin();

            Comment comment = entityManager.find(Comment.class, 1);
            System.out.println(comment.getComment());
            entityManager.remove(comment);
//            Post post = new Post();
//            post.setContent("post 1");
//            post.setTitle("POST");
//
//            Comment comment = new Comment();
//            comment.setComment("comment");
//
//            Comment comment1 = new Comment();
//            comment1.setComment("comment 2");
//
//            post.setComments(List.of(comment,comment1));
//            comment.setPost(post);
//            comment1.setPost(post);
//
//            entityManager.persist(post);
//            entityManager.persist(comment);

            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }
}