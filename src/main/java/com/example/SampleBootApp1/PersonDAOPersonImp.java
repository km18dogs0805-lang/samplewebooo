package com.example.SampleBootApp1;

import java.util.List;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOPersonImp implements PersonDAO<Person> {
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    public PersonDAOPersonImp() {
        super();
    }

    @Override
    public List<Person> getAll() {
        
        List<Person> list = null;

        // クエリを作成
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        
        // クエリの実行
        CriteriaQuery<Person> query = builder.createQuery(Person.class);

        // Rootの取得
        Root<Person> root = query.from(Person.class);
        query.select(root);

        // result
        list = (List<Person>)entityManager.createQuery(query).getResultList();

        return list;
    }

    @Override
    public Person findById(long id) {
        // TODO Auto-generated method stub
        return (Person)entityManager.createQuery("from Person where id = " + id).getSingleResult();
    }

    @Override
    public List<Person> findByName(String name) {
        // TODO Auto-generated method stub
        return (List<Person>)entityManager.createQuery("from Person where name = '" + name + "'").getResultList();
    }
    
    @Override
    public List<Person> find(String fstr) {
        // 初期値
        List<Person> list = null;
        
        // クエリの取得
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        // クエリの作成
        CriteriaQuery<Person> query = builder.createQuery(Person.class);

        // Rootの取得
        Root<Person> root = query.from(Person.class);

        query.select(root).where(builder.equal(root.get("name"), fstr));

        // 結果の取得
        list = (List<Person>) entityManager.createQuery(query).getResultList();

        return list;
    }
    
}

/* 
    @Override
    public List<Person> find(String fstr) {
        // TODO Auto-generated method stub
        List<Person> list = null;

        // クエリ文字列の作成
        String qstr = "from Person where id = :fid or name like :fname or mail like :fmail";
        Long fid = 0L;
        try {
            fid = Long.parseLong(fstr);
        } catch(NumberFormatException e) {
            // リポジトリに無いデータは、printstackTrace()でエラーを表示する
            e.printStackTrace();
        }

        // Queryの作成
        Query query = entityManager.createQuery(qstr);
        query.setParameter("fid", fid);
        query.setParameter("fname", "%" + fstr + "%");
        query.setParameter("fmail", fstr + "%@%");

        // 結果の取得
        list = query.getResultList();

        return list;
    }       

        @Override
    public List<Person> getAll() {
        // クエリを作成
        Query query = entityManager.createQuery("from Person");
        @SuppressWarnings("unchecked")
        List<Person> list = query.getResultList();
        entityManager.close();
        return list;
    }
*/
