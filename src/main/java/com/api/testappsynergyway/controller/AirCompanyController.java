package com.api.testappsynergyway.controller;

import com.api.testappsynergyway.dto.AirCompanyDTO;
import com.api.testappsynergyway.dto.ValidGroup;
import com.api.testappsynergyway.entity.AirCompany;
import com.api.testappsynergyway.service.AirCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Api(tags = "Endpoints about air companies")
@RestController
@FieldDefaults(level = PRIVATE)
@RequestMapping(value = "airCompany/")
public class AirCompanyController {

    final AirCompanyService airCompanyService;

    public AirCompanyController(AirCompanyService airCompanyService) {
        this.airCompanyService = airCompanyService;
    }

    @ApiOperation(value = "Find all air companies")
    @GetMapping(value = "findAll")
    public List<AirCompanyDTO.FindAll> findAll() {
        return airCompanyService.findAll();
    }

    @ApiOperation(value = "Find company by id")
    @GetMapping(value = "id/{id}")
    public AirCompany findById(@PathVariable(value = "id") Long id) {
        return airCompanyService.findById(id);
    }

    @ApiOperation(value = "Create new air company or get if such is already present")
    @PutMapping(value = "createOrGet")
    public ResponseEntity<AirCompany> createOrGet(
            @RequestBody @Validated(value = ValidGroup.AIR_COMPANY_CREATE_OR_GET.class) AirCompanyDTO.CreateUpdate dto) {
        return ResponseEntity.ok(airCompanyService.createOrGet(dto));
    }

    @ApiOperation(value = "Update air company name or type or throw exception if updated company exists")
    @PatchMapping(value = "update")
    public AirCompany update(
            @RequestBody @Validated(value = ValidGroup.AIR_COMPANY_UPDATE.class) AirCompanyDTO.CreateUpdate dto) {
        return airCompanyService.update(dto);
    }

    @ApiOperation(value = "Delete air company by id")
    @DeleteMapping(value = "delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        airCompanyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
