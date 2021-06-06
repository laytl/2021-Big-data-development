package main;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.budgets.model.TimeUnit;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.io.monitor.FileAlterationMonitor;


public class monitor extends FileAlterationListenerAdaptor{
	public static S3 s2;
	public static AmazonS3 s1;
	public monitor(AmazonS3 ss,S3 s) {
		s1=ss;
		s2=s;
	}

   @Override
   public void onStart(FileAlterationObserver observer) {
      System.out.println("观察文件变化任务开始....");
   }

   @Override
   public void onFileCreate(File file) {
	   System.out.println("文件上传"+file.getAbsolutePath());
	   s2.upload(s1,file.getAbsolutePath());
      }

	
    @Override
    public void onFileChange(File file) {
    	System.out.println("文件更改"+file.getAbsolutePath());
    	s2.upload(s1,file.getAbsolutePath());
    }
    
    @Override
    public void onFileDelete(File file) {
    	System.out.println("文件删除"+file.getAbsolutePath());
    	s2.delete(s1,file.getAbsolutePath());
    }

}
