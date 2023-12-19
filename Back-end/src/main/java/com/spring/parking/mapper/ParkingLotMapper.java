package com.spring.parking.mapper;


import com.spring.parking.dto.ParkingLotDto;
import com.spring.parking.entity.ParkingLot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CarParkingInfoMapper.class)
public interface ParkingLotMapper {
    @Mapping(source = "carParkingInfoDto",target = "carParkingInfo")
    ParkingLot toParkingLotEntity(ParkingLotDto parkingLotDto);
    @Mapping(source = "carParkingInfo",target = "carParkingInfoDto")
    ParkingLotDto toParkingLotDto(ParkingLot parkingLot);

    List<ParkingLot> toParkingLotEntites(List<ParkingLotDto> parkingLotDtos);
    List<ParkingLotDto> toParkingLotDtos(List<ParkingLot> parkingLots);

}
