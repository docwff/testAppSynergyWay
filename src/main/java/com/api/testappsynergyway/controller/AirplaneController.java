package com.api.testappsynergyway.controller;

import com.api.testappsynergyway.dto.AirplaneDTO;
import com.api.testappsynergyway.entity.Airplane;
import com.api.testappsynergyway.service.AirplaneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;

@Api(tags = "Endpoints about airplanes")
@RestController
@FieldDefaults(level = PRIVATE)
@RequestMapping(value = "airplane/")
public class AirplaneController {

    final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @ApiOperation(value = "Appoint a new air company for airplane")
    @PatchMapping(value = "reAssign")
    public Airplane reAssign(@RequestBody AirplaneDTO.ReAssign dto) {
        return airplaneService.reAssign(dto);
    }

    @ApiOperation(value = "Create new airplane")
    @PostMapping(value = "create")
    public ResponseEntity<Airplane>create(@RequestBody @Valid AirplaneDTO.Create dto) {
        return ResponseEntity.status(CREATED).body(airplaneService.create(dto));
    }


}
