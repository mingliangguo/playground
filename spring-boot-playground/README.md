# README

A sample spring boot application, which currently supports:

- Cassandra auto-configuration, keyspace auto-creation, schema update
- IBM COS auto-configuration
 
 ## IBM Cloud Object Storage (COS) configuration
 
 Login to your IBM Cloud account and then navigate to cloud storage page. And find the `Service credentials` at the left hand navigation list. Find the service credential used for the COS bucket. There are two different ways to authenticating with IBM Cloud for COS:
 - service id and `apikey` combination
 - hmac keys, which use `access_key_id` and `secret_access_key` combination.
 
 
## Remote debug running in docker 

Refer the `Dockerfile` to see what need to be configured to enalbe the remote debugging. 

Run the following to build and start the docker image:

```bash
./build-docker.sh
```

Then in Intellij, create a `Remote` debug configuration, and configure the remote port to the java debugging port exposed in the docker container. After the configuration is started, it will attached to the remote port and then step-by-step debug is enabled in Intellij.
