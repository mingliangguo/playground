package ming.playground.boot.services;

import com.ibm.cloud.objectstorage.services.s3.AmazonS3;
import com.ibm.cloud.objectstorage.services.s3.model.Bucket;
import com.ibm.cloud.objectstorage.services.s3.model.ObjectMetadata;
import com.ibm.cloud.objectstorage.services.s3.model.PutObjectRequest;
import com.ibm.cloud.objectstorage.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CosService {
  @Autowired
  private AmazonS3 client;

  @Value("cos.default-bucket-name")
  private String defaultBucketName;

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

  public boolean uploadFile(String bucketName, MultipartFile file) throws IOException {
    Assert.notNull(file, "file must not be null!");
    bucketName = Optional.ofNullable(bucketName).orElse(defaultBucketName);
    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.getSize());
    metadata.setContentType(file.getContentType());
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, file.getOriginalFilename(),
      file.getInputStream(), metadata);
    client.putObject(putObjectRequest);
    return true;
  }
}
