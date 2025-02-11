# receiptProcess
Need to process the receipt and assign points to the particular receipt. Need to fetch the points for a particular receipt provided the receipt id.
# END POINTS
## POST API:
* Path `http://localhost:8080/receipts/process`
* Request Body: 
```json
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}
```

* Success Response: 
```JSON
{
    "id": "6c8f9d3e-f08b-4fd9-b951-c9119f8eaebc"
}
```



## GET API
* Path: `http://localhost:8080/receipts/{id}/points`
* Example URL: `http://localhost:8080/receipts/54e5a6c0-04f9-4664-80d4-935c5b83a73b/points`

* Success Response: 
 ```json
  {
    "points": 28
  }
```

* Failed Response:  Status Code: 404 Not Found
```json
{
    "message": "No receipt found for that ID"
}
```
