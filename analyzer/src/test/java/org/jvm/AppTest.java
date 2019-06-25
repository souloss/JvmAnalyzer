package org.jvm;

import org.junit.Test;

import static org.junit.Assert.*;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void testApp() {
        System.out.println("Hello world");
        assertTrue(true);
    }

    public static void main(String[] args) {
		String ip = "192.168.31.128";
		String psw = "sftp$123";
		String user  = "sftp";
		int port = 22;
        
		Session session = null;
		JSch jsch = new JSch();
		try {
			if (port <= 0) {
	             // 连接服务器，采用默认端口
	             session = jsch.getSession(user, ip);
	         } else {
	             // 采用指定的端口连接服务器
	             session = jsch.getSession(user, ip, port);
	         }

	         // 如果服务器连接不上，则抛出异常
	         if (session == null) {
	            System.err.println("服务器连不上啊我草");
	         }

	         // 设置登陆主机的密码
	         session.setPassword(psw);// 设置密码
	         // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
	         session.setConfig("StrictHostKeyChecking", "no");
	         // 设置session登陆超时时间
             session.connect(30000);
             System.out.println(session.isConnected());

            ChannelSftp channelSftp = (ChannelSftp)session.openChannel("sftp");
            channelSftp.connect(30000);
            channelSftp.setFilenameEncoding("UTF-8");

	         System.out.println(channelSftp);
		} catch (Exception e) {
            System.err.println("异常了" + e);
			//关闭ssh的session连接
        	if (session != null ) {
        		session.disconnect();
			}
		}
    }
}
