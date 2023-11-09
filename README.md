To run the MongoDB on docker, use this command:
```
docker run -d -p 27017:27017 --name mymongodb \
  -e MONGO_INITDB_ROOT_USERNAME=myuser \
  -e MONGO_INITDB_ROOT_PASSWORD=mypassword \
  mongo
```
