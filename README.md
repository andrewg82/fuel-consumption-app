Project  API description

Base Entity endpoints
    
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

Statistics endpoints:
