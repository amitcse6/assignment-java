#1<br>
#Configure mysql connection from application.properties file.<br> 
#spring.datasource.url<br>
#<br>
#2<br>
#Import 'spring-boot-assignment.postman_collection.json' file into your postman<br>
#<br>
#3<br>
#Run your project.<br>
#Signup & signin required. 'http://localhost:8899/auth/signup', 'http://localhost:8899/auth/signin'<br> 
#<br>
#4<br>
#Parent Add<br>
#Create a person 'http://localhost:8899/person'<br>
#[NOTE: if any person is top of a tree then it's parent object would be null]<br>
#<br>
#   {
#       "firstName": "Atul1",
#       "lastName": "Mondol",
#       "address": {
#           "street": "Zazira",
#           "city": "Dhaka",
#           "zip": "1421"
#        }
#       "parent": null
#   }
#

#5
#Child Add<br>
#It is same as person insertion.<br>
#[NOTE: Here parent object wouldn't be null. And there contain at least parent id]<br>
#<br>
#   {
#       "firstName": "Atul2",
#       "lastName": "Mondol",
#       "parent": {
#           "id": 1
#       }
#   }
#
