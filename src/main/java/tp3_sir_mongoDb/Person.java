package tp3_sir_mongoDb;

import static com.oracle.jrockit.jfr.ContentType.Address;
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
public class Person {
 @Id
    private ObjectId objectId;
    private String name;
    private Address address;

    Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    void createAddress(String street, String city, String postCode, String country) {
        address = new Address(street, city, postCode, country);
    }

//    public void createAddress(Address address) throws CloneNotSupportedException {
//        this.address = Address.copy(address);
//    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    BSONObject toBson() {
        BasicBSONObject res = new BasicBSONObject();
        if (objectId != null)
            res.put("objectId", objectId.toString());
        res.put("name", name);
        res.put("address", address.toBson());
        return res;
    }

    
    
    
}
