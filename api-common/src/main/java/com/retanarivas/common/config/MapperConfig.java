package com.retanarivas.common.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // This 'STRICT' strategy is safer for DTOs;
        // it prevents accidental mapping of fields with similar but non-exact names.
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true); // Won't overwrite existing data with nulls during updates

        return modelMapper;
    }
}
