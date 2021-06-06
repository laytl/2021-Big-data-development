package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;

import java.util.List;
public class S3 {
	public final static String bucketName = "test1";
	public final static String filePath   = "E:\\桌面\\uniform";
	public final static String accessKey = "8C5F02219BB6B846D186";
	public final static String secretKey = "WzA5RjZGOTdERkNFQjE4MzhEQzYwMURDRTI0QzYy";
	public final static String serviceEndpoint = 
	"http://scut.depts.bingosoft.net:29997";
	private static long partSize = 5 << 20;
	private static long maxSize = 20 << 20;
	public final static String signingRegion = "";
	
	public static void init(AmazonS3 ss) {
		ObjectListing ol = ss.listObjects(bucketName);
	    List<S3ObjectSummary> objects = ol.getObjectSummaries();
	    String[] str;						//bucket文件目录
	    str = new String[20];
        int gg=0;
        int i=0;
        for (S3ObjectSummary os: objects) {
        	str[i]=os.getKey();
            i=i+1;
        }
		System.out.println("开启文件同步");
        for(String fl:str) {
        	if(fl!=null) {
    			final String keyName = fl;

    			File file = new File(filePath+"//"+fl);
    			S3Object o = null;
    			S3ObjectInputStream s3is = null;
    			FileOutputStream fos = null;
    			
				ObjectMetadata oMetaData = ss.getObjectMetadata(bucketName, keyName);
				final long contentLength = oMetaData.getContentLength();
				final GetObjectRequest downloadRequest = 
				new GetObjectRequest(bucketName, keyName);
    			
    			if(contentLength>maxSize) {
    			try {
        			System.out.println("正在下载"+fl);
        			
    				// Step 1: Initialize.
        			fos = new FileOutputStream(file);
    				// Step 2: Download parts.
    				long filePosition = 0;
    				for (int ij = 1; filePosition < contentLength; ij++) {
    					// Last part can be less than 5 MB. Adjust part size.
    					partSize = Math.min(partSize, contentLength - filePosition);

    					// Create request to download a part.
    					downloadRequest.setRange(filePosition, filePosition + partSize);
    					o = ss.getObject(downloadRequest);

    					// download part and save to local file.
    					System.out.format("Downloading part %d\n", ij);

    					filePosition += partSize+1;
    					s3is = o.getObjectContent();
    					byte[] read_buf = new byte[64 * 1024];
    					int read_len = 0;
    					while ((read_len = s3is.read(read_buf)) > 0) {
    						fos.write(read_buf, 0, read_len);
    					}
    				}

    				// Step 3: Complete.
    				System.out.println("Completing download");

    				System.out.format("save %s to %s\n", keyName, filePath);
    			} catch (Exception e) {
    				System.err.println(e.toString());
    				
    				System.exit(1);
    			} finally {
    				if (s3is != null) try { s3is.close(); } catch (IOException e) { }
    				if (fos != null) try { fos.close(); } catch (IOException e) { }
    			}
    			System.out.println("Done!");
    			}
    			else {
				try {			
    				S3Object o1 = ss.getObject(bucketName, keyName);
    				s3is = o1.getObjectContent();
    				fos = new FileOutputStream(new File(filePath+"\\"+fl));
    				byte[] read_buf = new byte[64 * 1024];
    				int read_len = 0;
    				while ((read_len = s3is.read(read_buf)) > 0) {
    					fos.write(read_buf, 0, read_len);
    				}
    				System.out.format("Downloading %s to S3 bucket %s...\n", keyName, bucketName);
    			}catch (AmazonServiceException e) {
    				System.err.println(e.toString());
    				System.exit(1);
    			} catch (IOException e) {
    				System.err.println(e.getMessage());
    				System.exit(1);
    			} finally {
    				if (s3is != null) try { s3is.close(); } catch (IOException e) { }
    				if (fos != null) try { fos.close(); } catch (IOException e) { }
    			}
    			}
        }
        }
	}
	
	
	public static void upload(AmazonS3 ss,String path) {
		final String keyName = Paths.get(path).getFileName().toString();
		ArrayList<PartETag> partETags = new ArrayList<PartETag>();
		
		File file = new File(path);
		long contentLength = file.length();
		String uploadId = null;
		if(contentLength > maxSize) {
			try {
				System.out.println(keyName);
				// Step 1: Initialize.
				InitiateMultipartUploadRequest initRequest = 
						new InitiateMultipartUploadRequest(bucketName, keyName);
				uploadId = ss.initiateMultipartUpload(initRequest).getUploadId();
				System.out.format("Created upload ID was %s\n", uploadId);

				// Step 2: Upload parts.
				long filePosition = 0;
				for (int i = 1; filePosition < contentLength; i++) {
					// Last part can be less than 5 MB. Adjust part size.
					partSize = Math.min(partSize, contentLength - filePosition);

					// Create request to upload a part.
					UploadPartRequest uploadRequest = new UploadPartRequest()
							.withBucketName(bucketName)
							.withKey(keyName)
							.withUploadId(uploadId)
							.withPartNumber(i)
							.withFileOffset(filePosition)
							.withFile(file)
							.withPartSize(partSize);

					// Upload part and add response to our list.
					System.out.format("Uploading part %d\n", i);
					partETags.add(ss.uploadPart(uploadRequest).getPartETag());

					filePosition += partSize;
				}

				// Step 3: Complete.
				System.out.println("Completing upload");
				CompleteMultipartUploadRequest compRequest = 
						new CompleteMultipartUploadRequest(bucketName, keyName, uploadId, partETags);

				ss.completeMultipartUpload(compRequest);
			} catch (Exception e) {
				System.err.println(e.toString());
				if (uploadId != null && !uploadId.isEmpty()) {
					// Cancel when error occurred
					System.out.println("Aborting upload");
					ss.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, keyName, uploadId));
				}
				System.exit(1);
			}
			System.out.println("Done!");
		}
		//小文件传输
		else {
		try {
			ss.putObject(bucketName, keyName, path);
        }catch (AmazonServiceException e) {
        if (e.getErrorCode().equalsIgnoreCase("NoSuchBucket")) {
        	ss.createBucket(bucketName);
        }
        	System.err.println(e.toString());
        	System.exit(1);
        }catch (AmazonClientException e) {
        try{
          // detect bucket whether exists
        	ss.getBucketAcl(bucketName);
        }catch (AmazonServiceException ase) {
        	if (ase.getErrorCode().equalsIgnoreCase("NoSuchBucket")) {
        		ss.createBucket(bucketName);
        	}
        }catch (Exception ignore) {
     }
      System.err.println(e.toString());
      System.exit(1);
	}
}
}    

	
	public static void delete(AmazonS3 ss,String path) {
		final String keyName = Paths.get(path).getFileName().toString();
		System.out.println("需要删除"+keyName);
		if(ss.doesBucketExist(bucketName) == false) {
            System.out.println(bucketName + " does not exists!");
            return;
        }
        System.out.println("deleting " + keyName +"* in " + bucketName + " ...");
        int pre_len = keyName.length();
        ObjectListing objectListing = ss.listObjects(bucketName);
        for(S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            String key = objectSummary.getKey();
            int len = key.length();
            if(len < pre_len) {
                continue;
            }
            int ii;
            for(ii=0;ii<pre_len;ii++) {
                if(key.charAt(ii) != keyName.charAt(ii)) {
                    break;
                }
            }
            if(ii < pre_len) {
                continue;
            }
            ss.deleteObject(bucketName, key);
        }
	}
}
