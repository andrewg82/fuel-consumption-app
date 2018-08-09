**Show Fuel Consumption**
----
  Returns json data about a single fuel consumption.

* **URL**

  /api/fuel_consumption/id

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

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

* **Code:** 404 NOT FOUND <br />
    **Content:** `{ "message": "No FuelConsumption found with id: 50" }`
  
**Get All Fuel Consumption**
----
  Returns json data about all fuel consumptions.

* **URL**

  /api/fuel_consumption/

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

  /api/fuel_consumption/id

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

* **Code:** 422 Unprocessable Entity <br />
    **Content:** `{
                      "errorMessage": "Validation failed. 1 error(s)",
                      "errors": [
                          "pricePerLitter: must not be 0"
                      ]
                  }`

