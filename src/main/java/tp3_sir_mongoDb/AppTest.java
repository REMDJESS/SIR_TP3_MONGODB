/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3_sir_mongoDb;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author ANANI
 */
public class AppTest {
public static void main( String[] args ) throws UnknownHostException
    {
        Morphia morphia = new Morphia();
        MongoClient mongo = new MongoClient();
        morphia.map(Person.class).map(Address.class);
        Datastore ds = morphia.createDatastore(mongo, "tps_sir_database");

        Person p = new Person();
        p.setName("Tintin");
        //set address
        p.createAddress("123 Some street","Some city","123 456","Some country");
        ds.save(p);

        Person toto = new Person();
        toto.setName("coucou");
        //set address
        toto.createAddress("321 Any street","Any city","654 321","Any country");
        ds.save(toto);

        // Save the POJO
        Article article = new Article("Sir",5);
        article.addAchat(p);
        article.addAchat(toto);

        ds.save(article);
        for (Article a : ds.find(Article.class))
            System.err.println(a.toBson());
    }
}
