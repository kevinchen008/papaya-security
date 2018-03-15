package com.papaya.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.papaya.Exception.UserNotExistException;
import com.papaya.entity.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }


    @GetMapping
    @JsonView(User.userSimpleView.class)
    @ApiOperation(value="用户查询服务")
    public List<User> query(){
        List<User> users = new ArrayList<>();

        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.userDetailView.class)
    public User getUser(@ApiParam(value ="用户id") @PathVariable String id){
       /* throw new UserNotExistException(id);*/
       User user = new User();
        user.setUsername("kevin");
        return user;
    }

    @PostMapping
    public User Create(@Valid @RequestBody User user, BindingResult errors){

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error ->System.out.println(error.getDefaultMessage()) );
        }
        user.setId("1");
        return  user;
    }

    @GetMapping("/getInfo")
    public User getInfo() throws InterruptedException {
        User user = new User();
        Thread.sleep(10000);
        user.setUsername("kevin");
        return user;
    }



    private ConcurrentLinkedDeque<DeferredResult<String>> deferredResults =
            new ConcurrentLinkedDeque<DeferredResult<String>>();


    @RequestMapping("/getResult")
    @ResponseBody
    public DeferredResult<String> getDeferredResultController(){

        //设置 5秒就会超时
        final DeferredResult<String> stringDeferredResult = new DeferredResult<String>(1000L);

        //将请求加入到队列中
        deferredResults.add(stringDeferredResult);

        final String message = "{username:112233}";

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(10010);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //业务处理
                System.out.println("业务处理");
                stringDeferredResult.setResult(message);
            }
        });


        //setResult完毕之后，调用该方法
        stringDeferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("异步调用完成");
                //响应完毕之后，将请求从队列中去除掉
                deferredResults.remove(stringDeferredResult);
            }
        });

        stringDeferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                System.out.println("业务处理超时");
                stringDeferredResult.setResult("error:timeOut");
            }
        });
        return stringDeferredResult;
    }

    //开启线程定时扫描队列，响应客户端
    @Scheduled(fixedRate = 1000)
    public void scheduleResult(){
        System.out.println(new Date());
        for(int i = 0;i < deferredResults.size();i++){
            DeferredResult<String> deferredResult = deferredResults.getFirst();
            deferredResult.setResult("result:" + i);
        }
    }


}
