package te;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class div {
    private final static String bucketName = "tanglei-new";
    private final static String accessKey = "8C5F02219BB6B846D186";
    private final static String secretKey = "WzA5RjZGOTdERkNFQjE4MzhEQzYwMURDRTI0QzYy";
    private final static String serviceEndpoint =
            "http://scut.depts.bingosoft.net:29997";

    private final static String signingRegion = "";

    public void upload(String path) {
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        final ClientConfiguration ccfg = new ClientConfiguration().
                withUseExpectContinue(false);

        final EndpointConfiguration endpoint = new EndpointConfiguration(serviceEndpoint, signingRegion);

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider((AWSCredentials) credentials))
                .withClientConfiguration(ccfg)
                .withEndpointConfiguration(endpoint)
                .withPathStyleAccessEnabled(true)
                .build();

        System.out.format("Uploading %s to S3 bucket %s...\n", path, bucketName);
        final String keyName = "UP/"+Paths.get(path).getFileName().toString();
        final File file = new File(path);

        for (int i = 0; i < 2; i++) {
            try {
                s3.putObject(bucketName, keyName, file);
                break;
            } catch (AmazonServiceException e) {
                if (e.getErrorCode().equalsIgnoreCase("NoSuchBucket")) {
                    s3.createBucket(bucketName);
                    continue;
                }
                System.err.println(e.toString());
                System.exit(1);
            } catch (AmazonClientException e) {
                try {
                    // detect bucket whether exists
                    s3.getBucketAcl(bucketName);
                } catch (AmazonServiceException ase) {
                    if (ase.getErrorCode().equalsIgnoreCase("NoSuchBucket")) {
                        s3.createBucket(bucketName);
                        continue;
                    }
                } catch (Exception ignore) {
                }

                System.err.println(e.toString());
                System.exit(1);
            }
        }

        System.out.println("Done!");
    }

    public void save(String p, String ss, String s) {
        synchronized (this){
            try {
                FileOutputStream fos = new FileOutputStream(p + "//" + ss + ".txt", true);//true表明会追加内容
                PrintWriter pw = new PrintWriter(fos);
                pw.write(s + "\n");
                pw.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void upfile(String pp) {
        synchronized (this){
        File file = new File(pp);
        File[] array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                // only take file name
                upload(pp+"//"+array[i].getName());
            }
        }
        }
    }
}