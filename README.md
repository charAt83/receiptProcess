# receiptProcess
Need to process the receipt and assign points to the particular receipt. Need to fetch the points for a particular receipt provided the receipt id.
# Language and Framework Selection: 
For this project, I have used Java Language and Springboot framework. Springboot eliminates the boilerplate code and provides built-in functionalities allowing me to quickly setup and develop applications with minimal configurations.
## Rest API Development Made easy
Springboot simplifies RESTful API creation with minimal effort.
* Example: A REST API endpoint for Post mapping (POST API).
  ```
  @RestController
  @RequestMapping("/receipts")
  public class ReceiptController {

  @PostMapping("/process")
	public PostResponseBody receiptProcess(@RequestBody SimpleReceipt simpleReceipt) {
		PostResponseBody id = receiptProcessService.receiptProcess(simpleReceipt);
		return id;
	}
  }
  ```
  With this we can expose any API with minimal or no configuration.

# DOCKER SETUP AND RUN CONFIGURATIONS:
For the sake of easy  evaluation, I have containarized this application in docker. I have pushed the docker image to my public docker hub. 
* Docker hub user name: `saigude`
* Docker image Name: `venkata_gude_receipt_process`
* Image Tag: `rp_image1`
 ## Run Configurations:
 * Pull the image to your local docker hub.
 * Then run the container and run configurations. In the run configurations, you need to select Host Port Mapping as `localhost` or `8080`.
 * And run the container and Hit the end points with the following request body for POST api.
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


