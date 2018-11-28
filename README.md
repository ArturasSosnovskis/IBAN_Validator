# IBAN_Validator

Source code for a program that validates [IBAN](https://en.wikipedia.org/wiki/International_Bank_Account_Number)'s.

IBAN Validator startup:
<img src="/resources/Exec.PNG"/>

1st option:
Checks an IBAN from console input, tells the user if the IBAN is valid or not.
<img src="/resources/1.PNG"/>

2nd option:
Asks for path and name of a text file, which contains IBANs written in a column. Generates a file in the location of the input text file, with the same name and an .out extension, where after each IBAN a boolean value is added about the IBAN validity.

<img src="/resources/2.PNG"/>

### Example
```


IBANs.txt                               IBANs.out

AA051245445454552117989    --->         AA051245445454552117989;false
CC051245445454552117989    --->         CC051245445454552117989;false
LT517044077788877777       --->         LT517044077788877777;true
...

```



      



