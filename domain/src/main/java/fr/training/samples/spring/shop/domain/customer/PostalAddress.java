package fr.training.samples.spring.shop.domain.customer;

import java.io.Serializable;
import java.util.Objects;

public class PostalAddress implements Serializable {
    private static final long serialVersionUID = 1L;

    private String street;
    private String city;
    private String country;
    private String postalCode;


    public PostalAddress() {
    }

    public PostalAddress(String street, String city, String country, String postalCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostalAddress)) return false;
        PostalAddress that = (PostalAddress) o;
        return Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getPostalCode(), that.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getCity(), getCountry(), getPostalCode());
    }

    @Override
    public String toString() {
        return "PostalAddress{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
