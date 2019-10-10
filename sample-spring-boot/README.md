# README

A sample spring boot application, which currently supports:

- Cassandra auto-configuration, keyspace auto-creation, schema update
- IBM COS auto-configuration
 
 ## IBM Cloud Object Storage (COS) configuration
 
 Login to your IBM Cloud account and then navigate to cloud storage page. And find the `Service credentials` at the left hand navigation list. Find the service credential used for the COS bucket. There are two different ways to authenticating with IBM Cloud for COS:
 - service id and `apikey` combination
 - hmac keys, which use `access_key_id` and `secret_access_key` combination.
