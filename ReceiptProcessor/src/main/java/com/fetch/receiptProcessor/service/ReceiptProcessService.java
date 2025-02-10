package com.fetch.receiptProcessor.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fetch.receiptProcessor.exception.ReceiptNotFoundException;
import com.fetch.receiptProcessor.model.GetPointsResponse;
import com.fetch.receiptProcessor.model.Item;
import com.fetch.receiptProcessor.model.PostResponseBody;
import com.fetch.receiptProcessor.model.SimpleReceipt;

@Service
public class ReceiptProcessService {

	Map<String, Integer> hashMap = new HashMap<>();

	public PostResponseBody receiptProcess(SimpleReceipt simpleReceipt) {

		PostResponseBody body = new PostResponseBody();
		GetPointsResponse getBody = new GetPointsResponse();
		Integer total = 0;
		total = processor(simpleReceipt);
		String id = UUID.randomUUID().toString();
		body.setId(id);
		getBody.setPoints(total);
		hashMap.put(id, total);

		return body;
	}

	public GetPointsResponse getPoints(String receiptId) throws ReceiptNotFoundException {

		Integer points = null;
		if (hashMap.isEmpty()) {
			throw new ReceiptNotFoundException("No receipt found for that ID");
		}
		if (hashMap.containsKey(receiptId)) {
			points = hashMap.get(receiptId);
		} else {
			throw new ReceiptNotFoundException("No receipt found for that ID");
		}
		GetPointsResponse pointsBody = new GetPointsResponse();
		pointsBody.setPoints(points);
		return pointsBody;
	}

	private Integer processor(SimpleReceipt simpleReceipt) {
		int points = 0;

		points += simpleReceipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

		BigDecimal totalAmount = new BigDecimal(simpleReceipt.getTotal());

		if (totalAmount.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
			points += 50;
		}

		if (totalAmount.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
			points += 25;

		}

		points += (simpleReceipt.getItems().size() / 2) * 5;

		for (Item item : simpleReceipt.getItems()) {

			if (item.getShortDescription().trim().length() % 3 == 0) {

				BigDecimal withOutRound = new BigDecimal(item.getPrice()).multiply(new BigDecimal("0.2"));

				points += withOutRound.setScale(0, RoundingMode.CEILING).intValue();
			}
		}

		LocalDate date = LocalDate.parse(simpleReceipt.getPurchaseDate());

		if (date.getDayOfMonth() % 2 != 0) {
			points += 6;
		}

		LocalTime time = LocalTime.parse(simpleReceipt.getPurchaseTime(), DateTimeFormatter.ofPattern("HH:mm"));

		if (time.getHour() >= 14 && time.getHour() <= 16) {
			points += 10;
		}

		return points;
	}

}
