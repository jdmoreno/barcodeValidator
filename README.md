# Barcode Validator

This is a REST API with one method to validate the given barcode is correct according to the barcode specification. It validates the length, service indicator code,
checkdigit and the country code. It also calculates the checkdigit using the input serial number to verify its value.

The validation GET operation can be requested by passing the barcode as request param.

http://localhost:8080/validate?barcode={barcode}

## Notes
- Implements a RestExceptionHandler as an example of a common exception handler
- Uses slf4j to log to console
