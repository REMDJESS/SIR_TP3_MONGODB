/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3_sir_mongoDb;

import java.util.ArrayList;
import java.util.List;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author ANANI
 */
@Entity
public class Article {
@Id
    private ObjectId objectId;
    private String name;
    private int stars;
    private List<Person> acheteurs;

    public Article() {
    }

    Article(String name, int stars) {
        this.name = name;
        this.stars = stars;
        acheteurs = new ArrayList<Person>();
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<Person> get() {
        return acheteurs;
    }

    boolean addAchat(Person buyer) {
        if (buyer == null)
            return false;
        else
            acheteurs.add(buyer);
            
        return true;
    }

    public void setAchat(List<Person> acheteurs) {
        this.acheteurs = acheteurs;
    }

    BSONObject toBson() {
        BasicBSONObject res = new BasicBSONObject();
        res.put("objectId", objectId.toString());
        res.put("name", name);
        res.put("stars", stars);
        BasicBSONObject acheteursBSON = new BasicBSONObject();
        for (Person p : acheteurs)
            acheteursBSON.put(p.getObjectId().toString(), p.toBson());
        res.put("acheteurs", acheteursBSON);
        return res;
    }
}
