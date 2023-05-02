#!/bin/bash

# Device of any type	$4 per device
# Antivirus for Windows	$5 per device
# Antivirus for Mac		$7 per device
# Backup				$3 per device
# Screen Share			$1 per device

# Device Type	Antivirus	Backup	Screen Share
# ----------------------------------------------
# Windows	        2	       	1	   	2
# Mac				3			2		2
# Total				31			9		4
#
# Total cost: 64

## creating customer
curl --location 'http://localhost:8080/api/customer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Tiago Melo",
    "email": "tiagoharris@gmail.com"
}' > /dev/null 2>&1

echo "Created customer 'Tiago Melo'"

## creating devices
curl --location 'http://localhost:8080/api/customer/1/device' \
--header 'Content-Type: application/json' \
--data '{
    "systemName": "Windows 7",
    "typeId": 1
}' > /dev/null 2>&1

echo "Created Windows device named 'Windows 7'"

curl --location 'http://localhost:8080/api/customer/1/device' \
--header 'Content-Type: application/json' \
--data '{
    "systemName": "Windows 10",
    "typeId": 1
}' > /dev/null 2>&1

echo "Created Windows device named 'Windows 10'"

curl --location 'http://localhost:8080/api/customer/1/device' \
--header 'Content-Type: application/json' \
--data '{
    "systemName": "MacOS v1",
    "typeId": 3
}' > /dev/null 2>&1

echo "Created MacOS device named 'MacOS v1'"

curl --location 'http://localhost:8080/api/customer/1/device' \
--header 'Content-Type: application/json' \
--data '{
    "systemName": "MacOS v2",
    "typeId": 3
}' > /dev/null 2>&1

echo "Created MacOS device named 'MacOS v2'"

curl --location 'http://localhost:8080/api/customer/1/device' \
--header 'Content-Type: application/json' \
--data '{
    "systemName": "MacOS v3",
    "typeId": 3
}' > /dev/null 2>&1

echo "Created MacOS device named 'MacOS v3'"

## adding "Antivirus" service

### Windows devices
curl --location --request POST 'http://localhost:8080/api/customer/1/device/1/service/1' > /dev/null 2>&1
echo "Added Antivirus service to Windows device 'Windows 7'"
curl --location --request POST 'http://localhost:8080/api/customer/1/device/2/service/1' > /dev/null 2>&1
echo "Added Antivirus service to Windows device 'Windows 10'"

### MacOS devices
curl --location --request POST 'http://localhost:8080/api/customer/1/device/3/service/1' > /dev/null 2>&1
echo "Added Antivirus service to MacOS device 'MacOS v1'"
curl --location --request POST 'http://localhost:8080/api/customer/1/device/4/service/1' > /dev/null 2>&1
echo "Added Antivirus service to MacOS device 'MacOS v2'"
curl --location --request POST 'http://localhost:8080/api/customer/1/device/5/service/1' > /dev/null 2>&1
echo "Added Antivirus service to MacOS device 'MacOS v3'"

## adding "Backup" service

### Windows devices
curl --location --request POST 'http://localhost:8080/api/customer/1/device/1/service/2' > /dev/null 2>&1
echo "Added Backup service to Windows device 'Windows 7'"

### MacOS devices
curl --location --request POST 'http://localhost:8080/api/customer/1/device/3/service/2' > /dev/null 2>&1
echo "Added Backup service to MacOS device 'MacOS v1'"
curl --location --request POST 'http://localhost:8080/api/customer/1/device/4/service/2' > /dev/null 2>&1
echo "Added Backup service to MacOS device 'MacOS v2'"

## adding "Screen share" service

### Windows devices
curl --location --request POST 'http://localhost:8080/api/customer/1/device/1/service/3' > /dev/null 2>&1
echo "Added Screen share service to Windows device 'Windows 7'"
curl --location --request POST 'http://localhost:8080/api/customer/1/device/2/service/3' > /dev/null 2>&1
echo "Added Screen share service to Windows device 'Windows 10'"

### MacOS devices
curl --location --request POST 'http://localhost:8080/api/customer/1/device/3/service/3' > /dev/null 2>&1
echo "Added Screen share service to MacOS device 'MacOS v1'"
curl --location --request POST 'http://localhost:8080/api/customer/1/device/4/service/3' > /dev/null 2>&1
echo "Added Screen share service to MacOS device 'MacOS v2'"

echo ""
echo "Total cost for customer 'Tiago Melo':"
curl --location 'http://localhost:8080/api/customer/1/invoice'
echo ""
echo ""