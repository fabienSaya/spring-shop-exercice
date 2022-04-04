package fr.training.samples.spring.shop.exposition.common;

import fr.training.samples.spring.shop.domain.item.Item;
import fr.training.samples.spring.shop.exposition.item.rest.ItemLightDto;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract implementation of a bean mapper
 *
 * @param <E>
 *            The entity class
 * @param <D>
 *            The dto class
 * @param <L>
 *            The light DTO
 *
 * @author 472957
 */
public abstract class AbstractMapper<E, D, L> {

    /**
     * @param entity
     *            entity
     * @return the mapped entity
     */
    public abstract D mapToDto(E entity);

    /**
     * @param dto
     *            dto
     * @return the mapped entity
     */
    public abstract E mapToEntity(D dto);

    /**
     * @param dto
     *            the light dto
     * @return the mapped entity
     */
    public abstract E mapLightDtoToEntity(L dto);

    /**
     * @param entityList
     *            entityList
     * @return a List of the mapped entity
     */
    public List<D> mapToDtoList(final List<E> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * @param entityList
     *            entityList
     * @return a Set of the mapped entity
     */
    public Set<D> mapToDtoSet(final Set<E> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toSet());
    }

    /**
     * @param dtoList
     *            dtoList
     * @return a List of the mapped entity
     */
    public List<E> mapToEntityList(final List<D> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toList());
    }

    /**
     * @param dtoList
     *            dtoList
     * @return a Set of the mapped entity
     */
    public Set<E> mapToEntitySet(final Set<D> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toSet());
    }

}