# ShopingCounter
A shopping counter application developed with spring boot and test covered with JUnit test cases.

o	Clone code from git and run with IntelliJ IDEA.
o	GIT URL: https://github.com/piyushvj/ShopingCounter
o   Under Resources folder of project there are two json file named barcodeitems.json & category.json for configuring items and category     respectively.
o   Please provide path for both the files in application.properties
o	From Post-Man make a POST request with below data 

REQUEST URL : http://localhost:9090/counter/generateInvoice

HTTP METHOD : POST

EQUEST PAYLOAD:
[
"A001",
"A001",
"B001"
]

o	Response data categories items according to category and calculate tax amount as per quantity and category. Also, it contains the total count of items purchased, total of item price, total of items tax, and grand total which a customer has to pay.

RESPONSE PAYLOAD:

{
    "invoiceRowList": [
        {
            "serialNumber": 1,
            "itemName": "Jeans",
            "itemPrice": 3000,
            "taxAmount": 600,
            "itemQuantity": 2
        },
        {
            "serialNumber": 2,
            "itemName": "Guitars",
            "itemPrice": 20000,
            "taxAmount": 0,
            "itemQuantity": 1
        },
        {
            "serialNumber": 3,
            "itemName": "Trumpet",
            "itemPrice": 10000,
            "taxAmount": 0,
            "itemQuantity": 1
        },
        {
            "serialNumber": 4,
            "itemName": "Cauliflower",
            "itemPrice": 40,
            "taxAmount": 16,
            "itemQuantity": 2
        }
    ],
    "totalItem": 6,
    "totalPrice": 36080,
    "totalTax": 616,
    "totalBillAmount": 36696
}
