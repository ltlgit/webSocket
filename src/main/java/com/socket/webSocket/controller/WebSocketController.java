package com.socket.webSocket.controller;

import com.socket.webSocket.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @program: springboot
 * @description:
 * @author: tinglin.liu_c
 * @create: 2021-10-28 16:04
 **/
@Controller
@RequestMapping("/open/socket")
public class WebSocketController {

    @Value("${mySocket.myPwd}")
    public String myPwd;

    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 手机客户端请求接口
     * @param id    发生异常的设备ID
     * @param pwd   密码（实际开发记得加密）
     * @throws IOException
     */
    @PostMapping(value = "/onReceive")
    @ResponseBody
    public void onReceive(String id,String pwd) throws IOException {
        if(pwd.equals(myPwd)){  //密码校验一致（这里举例，实际开发还要有个密码加密的校验的），则进行群发
            webSocketServer.broadCastInfo(id);
        }
    }

    @RequestMapping(value = "/webSocker")
    public String webSocker() throws IOException {
        return "webSocker";
    }
}
