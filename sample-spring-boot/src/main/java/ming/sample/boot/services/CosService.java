package ming.sample.boot.services;

import com.ibm.cloud.objectstorage.services.s3.AmazonS3;
import com.ibm.cloud.objectstorage.services.s3.model.Bucket;
import com.ibm.cloud.objectstorage.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CosService {
  @Autowired
  private AmazonS3 client;

  public boolean isBucketAvailable(String bucket) {
    return client.doesBucketExist(bucket);
  }
  public boolean getBucket(String bucket) {
    client.listObjects(bucket);
    return client.doesBucketExist(bucket);
  }

  public List<String> listBuckets() {
    return client.listBuckets().stream().map(Bucket::getName).collect(Collectors.toList());
  }
  public List<String> listFilesInBucket(String bucket) {
    return client.listObjects(bucket).getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
  }
}
