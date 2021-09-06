package com.udacity.critter.mapper.converter;

import com.udacity.critter.domain.contract.BaseIdEntity;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AbstractEntitiesConverter<TID> extends AbstractConverter<Collection<BaseIdEntity<TID>>, List<TID>> {
    @Override
    protected List<TID> convert(Collection<BaseIdEntity<TID>> baseIdEntities) {
        return baseIdEntities.stream()
                .map(BaseIdEntity::getId)
                .collect(Collectors.toList());
    }
}
