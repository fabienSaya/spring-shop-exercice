package fr.training.samples.spring.shop.domain.item;


import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;
import org.apache.commons.lang3.Validate;


public class Item extends AbstractBaseEntity {

    private String description;

    private Integer price;

    public Item() {
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * private constructor to enforce Builder usage
     */
    private Item(final Builder builder) {
        if (builder.id != null) {
            id = builder.id;
        }
        description = builder.description;
        price = builder.price;
    }

    /**
     * Builder static assessor
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern to ensure Item is immutable.
     */
    public static class Builder {
        private String id;
        private String description;
        private Integer price;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder description(final String description) {
            this.description = description;
            return this;
        }

        public Builder price(final Integer price) {
            this.price = price;
            return this;
        }

        public Item build() {
            Validate.notNull(description, "Item description is required");
            Validate.notNull(price, "Item price is required");
            return new Item(this);
        }

    }

}