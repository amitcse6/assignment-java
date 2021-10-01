#1<br>
#Configure mysql connection from application.properties file. 
#spring.datasource.url

#2<br>
#Import 'spring-boot-assignment.postman_collection.json' file into your postman

#3<br>
#Run your project.
#Signup & signin required. 'http://localhost:8899/auth/signup', 'http://localhost:8899/auth/signin' 

#4<br>
#Parent Add
#Create a person 'http://localhost:8899/person'
#[NOTE: if any person is top of a tree then it's parent object would be null]
#
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
#Child Add
#It is same as person insertion.
#[NOTE: Here parent object wouldn't be null. And there contain at least parent id]
#{
#   "firstName": "Atul2",
#   "lastName": "Mondol",
#   "parent": {
#       "id": 1
#   }
#}
