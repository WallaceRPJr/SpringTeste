package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.models.parkingSpotModel;
import com.api.parkingcontrol.parkingSpotDto.parkingSpotDto;
import com.api.parkingcontrol.services.ParkingSpotService;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")

public class parkingSpotControler {

    final ParkingSpotService parkingSpotService;

    public parkingSpotControler(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

@PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid parkingSpotDto parkingSpotDto){
        var parkingSpotModel = new parkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationTime(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }



    
    
}
