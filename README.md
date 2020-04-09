# Ecommerce API

Simple Ecommerce API for basic operation

### Used Technologies
* Spring Rest
* Spring Data
* Spring Security
* Docker

### How to use
* run the latest image    
    `docker run -p8080:8080 ahmedzaher54/ecommerce_api:latest `
* explore store anonymously     
    `curl  http://localhost:8080/store`
    
* login to get token    
    ` curl -d '{"username":"albert", "password":"pass"}' -H "Content-Type: application/json" -X POST http://localhost:8080/authenticate`
    
* add item to cart  
    `curl -d '{"itemId":"1"}' -H "Content-Type: application/json" -H "Authorization: Bearer <JWT> "  -X POST http://localhost:8080/cart/item`
    
* remove item from cart
    `curl  -H "Content-Type: application/json" -H "Authorization: Bearer <JWT> "  -X DELETE localhost:8080/cart/{cartId}/item/{itemId}`
