package xiaochen.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class IClientProfile implements InitializingBean {

    private static IAcsClient client;

    @Override
    public void afterPropertiesSet() throws Exception {
        String regionid = "cn-hangzhou";
        String accessKeyId = "*** Provide your AccessKeyId ***";
        String accessKeySecret = "*** Provide your AccessKeySecret ***";
        // Create a new IClientProfile instance
        DefaultProfile profile = DefaultProfile.getProfile(regionid, accessKeyId, accessKeySecret);
        this.client = new DefaultAcsClient(profile);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "afs", "afs.aliyuncs.com");
        System.out.println("------> IAcsClient Init!");
    }

    public static IAcsClient getClient() {
        return client;
    }
}
