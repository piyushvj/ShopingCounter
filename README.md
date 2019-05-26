# ShopingCounter
A shopping counter application developed with spring boot and test covered with JUnit test cases.

o	Clone code from git and run with IntelliJ IDEA.
o	GIT URL: https://github.com/piyushvj/ShopingCounter
o	From Post-Man make a POST request with below data [data in table].
o	"A001", "A002", "A003" are the barcodes defined for items of category A
o	"B001", "B002", "B003" are the barcodes defined for items of category B
o	"C001", "C002", "C003" are the barcodes defined for items of category C
o	Response data categories items according to category and calculate tax amount as per quantity and category. Also, it contains the total count of items purchased, total of item price, total of items tax, and grand total which a customer has to pay.


REQUEST URL : http://localhost:9090/counter/product/purchased

REQUEST PAYLOAD:

{
"barcods":["A001", "A001", "C001", "C003", "B003", "B003"]
}

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
