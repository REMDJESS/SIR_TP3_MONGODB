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
public class AppTest1 {
    public static void main( String[] args ) throws UnknownHostException
    {
        Morphia morphia = new Morphia();
        MongoClient mongo = new MongoClient();
        morphia.map(Person.class).map(Address.class);
        Datastore ds = morphia.createDatastore(mongo, "tp3_database");

        Person tintin = new Person();
        tintin.setName("Tintin");
        //set address
        tintin.createAddress("123 Some street","Some city","123 456","Some country");
        ds.save(tintin);

        Person Anani = new Person();
        Anani.setName("Anani");
        //set address
       Anani.createAddress("27 avenue charles foulon","rennes","35000","France");
        ds.save(Anani);
        /*****************************/
        Person vasquez = new Person();
        Anani.setName("vasquez");
        //set address
        Anani.createAddress("33 avenue","rennes","35000","France");
        ds.save(vasquez);

        // Save the POJO
//        Article article = new Article("tp3_sir",3);
//        article.addAchat(Anani);
//        article.addAchat(vasquez);
//        article.addAchat(tintin);

       // ds.save(article);
        for (Article a : ds.find(Article.class))
            System.err.println(a.toBson());
    } 
}
