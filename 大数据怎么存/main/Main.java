package main;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.budgets.model.TimeUnit;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.io.monitor.FileAlterationMonitor;


public class Main {
	private final static String bucketName = "test1";
	private final static String filePath   = "E:\\桌面\\uniform";
	private final static String accessKey = "8C5F02219BB6B846D186";
	private final static String secretKey = "WzA5RjZGOTdERkNFQjE4MzhEQzYwMURDRTI0QzYy";
	private final static String serviceEndpoint = 
	"http://scut.depts.bingosoft.net:29997";
	private final static String signingRegion = "";
	
	static S3 s=new S3();

	public static void main(String[] args) throws Exception {
		final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
		final ClientConfiguration ccfg = new ClientConfiguration().
				withUseExpectContinue(true);

		final EndpointConfiguration endpoint = new EndpointConfiguration(serviceEndpoint, signingRegion);

		final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
              .withCredentials(new AWSStaticCredentialsProvider(credentials))
              .withClientConfiguration(ccfg)
              .withEndpointConfiguration(endpoint)
              .withPathStyleAccessEnabled(true)
              .build();
        s.init(s3);//第一步同步bucket到本地

        FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(s.filePath);
        fileAlterationObserver.addListener(new monitor(s3,s));
        FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor(1000,fileAlterationObserver);
        fileAlterationMonitor.start();
        
        
        
	}
}