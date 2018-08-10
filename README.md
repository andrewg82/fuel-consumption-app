**Show Fuel Consumption**
----
  Returns json data about a single fuel consumption.

* **URL**

  /api/fuel_consumption/id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[Long]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{
                       "fuelType": "PETROL_95",
                       "pricePerLitter": 1,
                       "volume": 500,
                       "date": "2015-05-15",
                       "driverId": 1
                   }`
* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
        **Content:** `{ "message": "No FuelConsumption found with id: 50" }`
  
**Get All Fuel Consumption**
----
  Returns json data about all fuel consumptions.

* **URL**

  /api/fuel_consumption

* **Method:**

  `GET`
  
*  **URL Params**

   None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[
                      {
                          "fuelType": "PETROL_95",
                          "pricePerLitter": 1,
                          "volume": 500,
                          "date": "2015-05-15",
                          "driverId": 1
                      },
                      {
                          "fuelType": "PETROL_98",
                          "pricePerLitter": 1.3,
                          "volume": 600,
                          "date": "2015-05-18",
                          "driverId": 2
                      }
                   ]`

**Create Fuel Consumption**
----
  Create new fuel consumption.

* **URL**

  /api/fuel_consumption

* **Method:**

  `POST`
  
*  **URL Params**

   None

* **Data Params**

  `{
       "fuelType": "PETROL_95",
       "pricePerLitter": 1,
       "volume": 500,
       "date": "2018-05-15",
       "driverId": 1
   }`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{
                        "fuelType": "PETROL_95",
                        "pricePerLitter": 1,
                        "volume": 500,
                        "date": "2018-05-15",
                        "driverId": 1
                   }`

* **Error Response:**

    * **Code:** 422 Unprocessable Entity <br />
        **Content:** `{
                          "errorMessage": "Validation failed. 1 error(s)",
                          "errors": [
                              "pricePerLitter: must not be 0"
                          ]
                      }`
                      
**Create Bulk of Fuel Consumptions**
----
  Create new fuel consumptions.

* **URL**

  /api/fuel_consumption/bulk

* **Method:**

  `POST`
  
*  **URL Params**

   None

* **Data Params**

    Multipart form data (Content-Type: multipart/form-data)
    
  `payload: [
              {
                "fuelType": "PETROL_95",
                  "pricePerLitter": 1,
                  "volume": 500,
                  "date": "2015-05-15",
                  "driverId": 1
              },
              {
                  "fuelType": "PETROL_98",
                  "pricePerLitter": 1.3,
                  "volume": 600,
                  "date": "2015-05-18",
                  "driverId": 2
              }
           ]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[
                {
                  "fuelType": "PETROL_95",
                    "pricePerLitter": 1,
                    "volume": 500,
                    "date": "2015-05-15",
                    "driverId": 1
                },
                {
                    "fuelType": "PETROL_98",
                    "pricePerLitter": 1.3,
                    "volume": 600,
                    "date": "2015-05-18",
                    "driverId": 2
                }
             ]`

* **Error Response:**

    * **Code:** 422 Unprocessable Entity <br />
        **Content:** `{
                          "errorMessage": "Validation failed. 1 error(s)",
                          "errors": [
                              "pricePerLitter: must not be 0"
                          ]
                      }`

**Update Fuel Consumption**
----
  Updates existing fuel consumption.

* **URL**

  /api/fuel_consumption/id

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**
 
   `id=[Long]`

* **Data Params**

  `{
                         "fuelType": "PETROL_95",
                         "pricePerLitter": 1,
                         "volume": 500,
                         "date": "2015-05-15",
                         "driverId": 1
                     }`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{
                       "fuelType": "PETROL_95",
                       "pricePerLitter": 1,
                       "volume": 500,
                       "date": "2015-05-15",
                       "driverId": 1
                   }`
* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
        **Content:** `{ "message": "No FuelConsumption found with id: 50" }`
        
    * **Code:** 422 Unprocessable Entity <br />
            **Content:** `{
                              "errorMessage": "Validation failed. 1 error(s)",
                              "errors": [
                                  "pricePerLitter: must not be 0"
                              ]
                          }`
  

**Delete Fuel Consumption**
----
  Deletes fuel consumption by Id.

* **URL**

  /api/fuel_consumption/id

* **Method:**

  `DELETE`
  
*  **URL Params**

    **Required:**
     
   `id=[Long]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
        None

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
        **Content:** `{
                          "message": "No FuelConsumption found with id: 243"
                      }`
                      
**Get total spent amount of money**
----
  Shows total spent amount of money grouped by month. By all drivers or by specified driver.

* **URL**

  /api/statistics/total_spent_amount

* **Method:**

  `GET`
  
*  **URL Params**

    **Required:**
     
    None
   
   **Non-required:**
    
    `driverId=[Int]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
  `{
       "2015-05-01": 1500,
       "2015-06-01": 1665,
       "2016-05-01": 1500,
       "2016-06-01": 1665
   }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
        **Content:** `{
                          "message": "There are no records matched your query"
                      }`


**Get list of fuel consumption records**
----
  Shows list of fuel consumption records for specified month. By all drivers or by specified driver.

* **URL**

  /api/statistics/month_statistics

* **Method:**

  `GET`
  
*  **URL Params**

    **Required:**
     
    `date=[LocalDate], e.g 2015-06-01`
   
   **Non-required:**
    
    `driverId=[Int]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
  `[
       {
           "fuelType": "PETROL_95",
           "pricePerLitter": 1,
           "volume": 500,
           "date": "2015-05-15",
           "driverId": 1,
           "totalPrice": 1.5
       },
       {
           "fuelType": "PETROL_95",
           "pricePerLitter": 2,
           "volume": 500,
           "date": "2015-05-20",
           "driverId": 1,
           "totalPrice": 1.5
       }
   ]`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
        **Content:** `{
                          "message": "There are no records matched your query"
                      }`
                      
                      
                      
**Get aggregated statistics**
----
  Shows statistics for each month, list fuel consumption records grouped by fuel type. By all drivers or by specified driver.

* **URL**

  /api/statistics//aggregated_statistics

* **Method:**

  `GET`
  
*  **URL Params**

    **Required:**
     
    None
   
   **Non-required:**
    
    `driverId=[Int]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
  `{
       "2015-05-01": [
           {
               "fuelType": "PETROL_95",
               "totalPrice": 1.5,
               "averagePrice": 1.5
           }
       ],
       "2015-06-01": [
           {
               "fuelType": "PETROL_98",
               "totalPrice": 1.8,
               "averagePrice": 1.8
           },
           {
               "fuelType": "PETROL_95",
               "totalPrice": 1.9,
               "averagePrice": 1.9
           }
       ],
       "2016-05-01": [
           {
               "fuelType": "PETROL_95",
               "totalPrice": 1.5,
               "averagePrice": 1.5
           }
       ],
       "2016-06-01": [
           {
               "fuelType": "PETROL_98",
               "totalPrice": 1.8,
               "averagePrice": 1.8
           },
           {
               "fuelType": "PETROL_95",
               "totalPrice": 1.9,
               "averagePrice": 1.9
           }
       ]
   }`

* **Error Response:**

    * **Code:** 404 NOT FOUND <br />
        **Content:** `{
                          "message": "There are no records matched your query"
                      }`