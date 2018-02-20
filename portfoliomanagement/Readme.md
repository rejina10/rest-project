## PORTFolio Management

### Pre-requisite to run application: 
1)	Maven V 3.2 or higher
2)	Java V 1.8 or higher

### Steps to run the application: 
1)	GOTO: {rootDirectory} of the application in command line.
2)	In a command line: mvn spring-boot:run

Since this is a spring boot application you don’t have to deploy war in external tomcat. It should run the application in embedded tomcat. After the app is up it will run in port 8090.
When the application boots. Two default advisors are created for you in h2 database (in-memory database). 

Name	    Username	Password
advisor1	advisor1	pass1
advisor2	advisor2	pass2 

Two models are created for advisor1 and one model is created for advisor2. 

### Get Models for advisor:
**/v1/advisor/{advisorId}/model**
This call will return all the model of the advisor if the advisor is authenticated and has access to the models.

Example Calls:
In chrome:  http://localhost:8090/v1/advisor/1/model  fill username: advisor1 and password: pass1. 

Post man:  In postman  
1.	use  http://localhost:8090/v1/advisor/1/model as URL. 
2.	Set Method as GET
3.	Set Authorization Type: Basic Auth
4.	Set username: advisor1 and  password: pass1

CURL: Run following CURL command:
```
  curl -X GET \
  http://localhost:8090/v1/advisor/1/model \
  -H 'authorization: Basic advisor1:pass1' \
  
 ```

### Test Cases:

  1)	Forbidden (403): If advisor tries to access models that are not associated with logged in or authenticated; 403 Forbidden status         code is returned. 

      Replication:

      Post man: 
      1. use  http://localhost:8090/v1/advisor/1/model as URL.  Set method as GET.
      2. Set Authorization Type: Basic Auth
      3. Set username: advisor2 and  password: pass2

      CURL: 
      
        curl -X GET \
        http://localhost:8090/v1/advisor/1/model \
        -H 'authorization: Basic advisor2:pass2' \

  2)	Not Found (404): If user tries to get models of advisor that is not in database then 404 is returned. 

      Replication: 

      Post man: 
      1.	use  http://localhost:8090/v1/advisor/10000/model as URL.  Set method as GET.
      2.	Set Authorization Type: Basic Auth
      3.	Set username: advisor2 and  password: pass2
      
      CURL: 
      ```
        curl -X GET \
        http://localhost:8090/v1/advisor/10000/model \
        -H 'authorization: Basic advisor2:pass2' \
      ```

### PUT Models for advisor:
**/v1/advisor/{advisorId}/model**
This call adds or update model for an advisor if the advisor is authenticated and has access to the models.

  Post man:  
  1.	use  http://localhost:8090/v1/advisor/1/model as URL. 
  2.	Set Method as PUT
  3.	Set Authorization Type: Basic Auth
  4.	Set username: advisor1 and  password: pass1
  5.	Set Headers; Content-Type: application/json; Accept: application/json
  6.	Set Body as raw and use following model to add new model.
  {
    {
            "name": "model4",
            "description": "example model3 with tech stocks",
            "cashHoldingPercentage": 10,
            "driftPercentage": 10,
            "modelType": "TAXABLE",
            "rebalanceFrequency": "QUARTERLY",
            "assetAllocationList": [
                {
                    "symbol": "AAPL",
                    "percentage": 30
                },
                {
                    "symbol": "GOOG",
                    "percentage": 60
                }
            ]
        }
     }
    
      CURL: 
      curl -X PUT \
        http://localhost:8090/v1/advisor/1/model \
        -H 'accept: application/json' \
        -H 'authorization Basic advisor1:pass1'' \
        -H 'content-type: application/json' \
        -d '{
              "name": "model4",
              "description": "example model3 with tech stocks",
              "cashHoldingPercentage": 10,
        "driftPercentage": 10,
        "modelType": "TAXABLE",
        "rebalanceFrequency": "QUARTERLY",
        "assetAllocationList": [
            {
                "symbol": "AAPL",
                "percentage": 30
            },
            {
                "symbol": "GOOG",
                "percentage": 60
            }
        ]  }
        
        
### Test Cases:
1)	BAD_REQUST(400) : The request body is validated with following rules: <br />
       - "name": Required, <br />
       - "description": Required, <br />
       - "cashHoldingPercentage": Required, <br />
       - "driftPercentage": Required, <br />
       - "modelType": Required, <br />
       - "rebalanceFrequency": Required, <br />
       - modelType must one of QUALIFIED,TAXABLE,  <br />
       - rebalanceFrequency must  be one of MONTHLY, QUARTERLY, SEMI_ANNUAL, ANNUAL <br />
       - The sum of percentages in assetAllocationList and cashHoldingPercentage must be 100 <br /> 
      If any of these validation fails gives Bad request(400).<br /><br />
      
      Replication if name is null: 
      1.	use  http://localhost:8090/v1/advisor/1/model as URL. 
      2.	Set Method as PUT
      3.	Set Authorization Type: Basic Auth
      4.	Set username: advisor1 and  password: pass1
      5.	Set Headers; Content-Type: application/json; Accept: application/json
      6.	Set Body as raw and use following model to add new model.
      
      ```
        {
                "description": "example model3 with tech stocks",
                "cashHoldingPercentage": 10,
                "driftPercentage": 10,
                "modelType": "TAXABLE",
                "rebalanceFrequency": "QUARTERLY",
                "assetAllocationList": [
                    {
                        "symbol": "AAPL",
                        "percentage": 30
                    },
                    {
                        "symbol": "GOOG",
                        "percentage": 60
                    }
                ]
            }
      ```

      Replication if total asset is not 100: 
      1.	use  http://localhost:8090/v1/advisor/1/model as URL. 
      2.	Set Method as PUT
      3.	Set Authorization Type: Basic Auth
      4.	Set username: advisor1 and  password: pass1
      5.	Set Headers; Content-Type: application/json; Accept: application/json
      6.	Set Body as raw and use following model to add new model.
      ```
      {
              "name": "model4",
              "description": "example model3 with tech stocks",
              "cashHoldingPercentage": 100,
              "driftPercentage": 10,
              "modelType": "TAXABLE",
              "rebalanceFrequency": "QUARTERLY"
          }
      ```    

2)	Forbidden (403): If advisor tries to access models that are not associated with logged in or authenticated; 403 Forbidden status code is returned. 

   Replication:
   
    Post man:   
        1.	use  http://localhost:8090/v1/advisor/1/model as URL.  
        2.	Set Method as PUT
        3.	Set Authorization Type: Basic Auth
        4.	Set username: advisor2 and  password: pass2
        5.	Set Headers; Content-Type: application/json; Accept: application/json
        6.	Set Body as raw and use following model to add new model.
        ```
          {
                  "name": "model4",
                  "description": "example model3 with tech stocks",
                  "cashHoldingPercentage": 100,
                  "driftPercentage": 10,
                  "modelType": "TAXABLE",
                "rebalanceFrequency": "QUARTERLY"
              }
         ```  
         <br/>
3)	Not found (404): If user tries to get models of advisor that is not in database then 404 is returned. 

  Replication: 

   Post man:

      1.	use  http://localhost:8090/v1/advisor/10000/model as URL.  
      2.	Set Method as PUT
      3.	Set Authorization Type: Basic Auth
      4.	Set username: advisor2 and  password: pass2
      5.	Set Headers; Content-Type: application/json; Accept: application/json
      6.	Set Body as raw and use following model to add new model.
        ```
        {
              "name": "model4",
              "description": "example model3 with tech stocks",
              "cashHoldingPercentage": 100,
              "driftPercentage": 10,
              "modelType": "TAXABLE",
              "rebalanceFrequency": "QUARTERLY"
          }
        ```
4)	Create vs Update: If client passes valid advisor with valid request body and name already in database. Then new model is not created instead model with same name is updated:

  Post man: 

    1.	use  http://localhost:8090/v1/advisor/1/model as URL. 
    2.	Set Method as PUT
    3.	Set Authorization Type: Basic Auth
    4.	Set username: advisor1 and  password: pass1
    5.	Set Headers; Content-Type: application/json; Accept: application/json
    6.	Set Body as raw and use following model to add new model.
       ```
        {
                "name": "model2",
                "description": "update model2 description",
                "cashHoldingPercentage": 10,
                "driftPercentage": 100,
                "modelType": "TAXABLE",
                "rebalanceFrequency": "QUARTERLY",
                "assetAllocationList": [
                    {
                        "symbol": "AAPL",
                        "percentage": 30
                    },
                    {
                        "symbol": "FOO",
                        "percentage": 60
                    }
                ]
            }
         ```

  CURL: 
    ```
    curl -X PUT \
      http://localhost:8090/v1/advisor/1/model \
      -H 'accept: application/json' \
      -H 'authorization Basic advisor1:pass1'' \
      -H 'content-type: application/json' \
      -d '{
            "name": "model2",
            "description": "update model2 description",
            "cashHoldingPercentage": 10,
            "driftPercentage": 100,
            "modelType": "TAXABLE",
            "rebalanceFrequency": "QUARTERLY",
            "assetAllocationList": [
                {
                    "symbol": "AAPL",
                    "percentage": 30
                },
                {
                    "symbol": "FOO",
                    "percentage": 60
                }
            ]
        }'
    ```
