package com.example.mapper;

import com.example.dto.ResponseNumber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NumberMapper {

    ResponseNumber toSuccessResponse(Integer data);

}
