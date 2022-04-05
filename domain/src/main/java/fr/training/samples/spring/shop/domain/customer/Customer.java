package fr.training.samples.spring.shop.domain.customer;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import org.apache.commons.lang3.Validate;

import java.util.HashSet;
import java.util.Set;


public class Customer extends AbstractBaseEntity {


    private String name;

    private String password;

    private Set<RoleTypeEnum> roles = new HashSet<>();

    public Customer() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public Set<RoleTypeEnum> getRoles() {
        return roles;
    }

    public void addRole(final RoleTypeEnum role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    /**
     * private constructor to enforce Builder usage
     */
    private Customer(final Builder builder) {
        if (builder.id != null) {
            id = builder.id;
        }
        name = builder.name;
        password = builder.password;
        roles = builder.roles;
    }

    /**
     * Builder static assessor
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern to ensure Customer is immutable.
     */
    public static class Builder {
        private String id;
        private String name;
        private String password;
        private Set<RoleTypeEnum> roles = new HashSet<>();

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder password(final String password) {
            this.password = password;
            return this;
        }

        public Builder addRole(final RoleTypeEnum role) {
            roles.add(role);
            return this;
        }

        public Customer build() {
            Validate.notNull(name, "customer's name is required");
            Validate.notNull(password, "customer's password is required");
            return new Customer(this);
        }

    }
}
