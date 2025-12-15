package com.influncer.education.Teacher.repo;



import com.influncer.education.Teacher.entity.Teacher;
import jakarta.persistence.EntityManager;//java Persitance JPA
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherRepo{

   private final EntityManager em;

    public TeacherRepo(final EntityManager em) {
        this.em = em;
    }

    public List<Teacher> getList() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> cr = cb.createQuery(Teacher.class);
        Root<Teacher> root = cr.from(Teacher.class);
        cr.select(root);
       final TypedQuery<Teacher> query = em.createQuery(cr);
        List<Teacher> results = query.getResultList();
        return results;
    }

    public void update(Teacher obj) {
//
    }

    @Transactional
    public void delete(Integer id) {
        Teacher t = em.getReference(Teacher.class, id); // <-- find yox, getReference!
        em.remove(t);
    }


    @Transactional
    public void insert(Teacher obj) {
    em.persist(obj);
    }

    public Teacher findById(int Id) {
        return em.find(Teacher.class, Id);
    }


    public List<Teacher> getList(String name, String surname , String email, Integer universityId ,Integer age) {


      final  CriteriaBuilder cb = em.getCriteriaBuilder();//Createri aciriq
        final CriteriaQuery<Teacher> cq = cb.createQuery(Teacher.class);//sonra ise Query duzelmek ucun baslangic

       final Root<Teacher> root = cq.from(Teacher.class);//koku teyin edirik

      final  List<Predicate> predicates = new ArrayList<>();//Array duzeldirik
        if(name != null && !name.isEmpty()){//
            predicates.add(cb.like(root.get("name"), "%"+name+"%"));
        }
        if(surname != null && !surname.isEmpty()){
            predicates.add( cb.like(root.get("surname"), "%"+surname+"%"));
        }
        if(email != null && !email.isEmpty()){
            predicates.add( cb.like(root.get("email"), "%"+email+"%"));
        }
        if(age != null){
            predicates.add( cb.equal(root.get("age"), age));
        }
        if(universityId != null){
            predicates.add( cb.equal(root.get("university").get("id"), universityId));
        }

        final Predicate and = cb.and(predicates.toArray(new Predicate[0])); //and edir


         cq.where(and);//Where name lik %name%  and surname

         cq.select(root); //select where

        final TypedQuery<Teacher> query = em.createQuery(cq);


        return query.getResultList();
    }

}