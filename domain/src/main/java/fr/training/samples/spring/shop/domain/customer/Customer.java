package fr.training.samples.spring.shop.domain.customer;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;

import java.util.HashSet;
import java.util.Set;


public class Customer extends AbstractBaseEntity {


    private String name;

    private String password;

    Set<RoleTypeEnum> roles = new HashSet<>();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    public Set<RoleTypeEnum> getRoles() {
        return roles;
    }

    public void addRole(final RoleTypeEnum role) {
        roles.add(role);
    }

}
