package com.xm.trading.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xm.trading.dto.Constant;
import com.xm.trading.services.UtilityServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "UtilityController", description = "REST APIs related to Trading App!!!!")
@RestController
public class UtilityController {
	
	@Autowired
	UtilityServiceImpl utilityServiceImpl; 

	@ApiOperation(value = "start the processing of crypto files ", response = Iterable.class, tags = "start")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully started the process"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@GetMapping("/start")
	ResponseEntity<String> hello() {
		utilityServiceImpl.readAllFileJob();
	    return new ResponseEntity<>("started!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "get all crypto result processed ", response = Iterable.class, tags = "results")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@GetMapping("/results")
	ResponseEntity<HashMap> getAllResult() {
	    return new ResponseEntity<>(Constant.resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "get specific crypto from result ", response = Iterable.class, tags = "result")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@GetMapping("/result")
	ResponseEntity<HashMap> getResultByName(@ApiParam(name = "name", value = "Enter name of crypto", defaultValue = "BTC")
											@RequestParam String name) {		
	    return new ResponseEntity<>(utilityServiceImpl.getResultByName(name), HttpStatus.OK);
	}
	
	@ApiOperation(value = "get sorted results with normalized price", response = Iterable.class, tags = "result-sort")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
	@GetMapping("/result-sort")
	ResponseEntity<HashMap> getResultByOrder(
			@ApiParam(name = "sort", value = "Enter sort preferance ASC or DESC", defaultValue = "DESC")
			@RequestParam String sort) {		
	    return new ResponseEntity<>(utilityServiceImpl.getResultByOrder(sort), HttpStatus.OK);
	}
}
