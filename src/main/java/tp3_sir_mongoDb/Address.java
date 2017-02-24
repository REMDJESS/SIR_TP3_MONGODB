
package tp3_sir_mongoDb;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author ANANI
 */
@Embedded
public class Address  {
    private String street;
    private String city;
    private String postCode;
    private String country;

    public Address() {
    }

    Address(String street, String city, String postCode, String country) {
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    static Address copy(Address address) throws CloneNotSupportedException {
//        return (Address) address.clone();
//    }

    BSONObject toBson(){
        BasicBSONObject coord = new BasicBSONObject();
        coord.put("rue", street);
        coord.put("ville", city);
        coord.put("code postal", postCode);
        coord.put("pays", country);
        return coord;
    }
}
