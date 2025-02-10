package com.fetch.receiptProcessor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fetch.receiptProcessor.exception.ReceiptNotFoundException;
import com.fetch.receiptProcessor.model.GetPointsResponse;
import com.fetch.receiptProcessor.model.PostResponseBody;
import com.fetch.receiptProcessor.model.SimpleReceipt;
import com.fetch.receiptProcessor.service.ReceiptProcessService;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessorController {

	@Autowired
	ReceiptProcessService receiptProcessService;

	@PostMapping("/process")
	public PostResponseBody receiptProcess(@RequestBody SimpleReceipt simpleReceipt) {
		PostResponseBody id = receiptProcessService.receiptProcess(simpleReceipt);
		return id;
	}

	@GetMapping("/{id}/points")
	public ResponseEntity<GetPointsResponse> getPoints(@PathVariable("id") String receiptId) throws ReceiptNotFoundException {
		GetPointsResponse points = receiptProcessService.getPoints(receiptId);
		return new ResponseEntity<GetPointsResponse>(points, HttpStatus.OK);
	}
}
