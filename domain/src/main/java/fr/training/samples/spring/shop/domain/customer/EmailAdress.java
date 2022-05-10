package fr.training.samples.spring.shop.domain.customer;

import java.io.Serializable;
import java.util.Objects;

public class EmailAdress implements Serializable {
    private static final long serialVersionUID = 1L;

    private String value;

    public EmailAdress() {
    }

    public EmailAdress(String value) {
        this.value = value;
    }

    public static EmailAdress of(final String value) {
        return new EmailAdress(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAdress)) return false;
        EmailAdress that = (EmailAdress) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "EmailAdress{" +
                "value='" + value + '\'' +
                '}';
    }
}
