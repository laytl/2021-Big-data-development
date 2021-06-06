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
      System.out.println("�۲��ļ��仯����ʼ....");
   }

   @Override
   public void onFileCreate(File file) {
	   System.out.println("�ļ��ϴ�"+file.getAbsolutePath());
	   s2.upload(s1,file.getAbsolutePath());
      }

	
    @Override
    public void onFileChange(File file) {
    	System.out.println("�ļ�����"+file.getAbsolutePath());
    	s2.upload(s1,file.getAbsolutePath());
    }
    
    @Override
    public void onFileDelete(File file) {
    	System.out.println("�ļ�ɾ��"+file.getAbsolutePath());
    	s2.delete(s1,file.getAbsolutePath());
    }

}
