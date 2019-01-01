package com.baizhi.serviceimpl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.entity.Error;
import com.baizhi.entity.User;
import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import com.baizhi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class TestInterface {
    /**
     * 这里是一级界面的接口文档，用于协同操作的
     * 需要有用户id
     * 需要有type用来选择请求的数据类型  是所有、闻、思
     * 还要有sub_type当type为si时候要用来进行 显示上师言教  和显密法要的
     * 其中需要很多的业务逻辑判断
     */
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private BannerService bannerService;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object homePage(String id, String type, String sub_type) {
        //如果id或者type有一个为空就没有办法进行请求显示
        if (id == null || type == null) {
            return new Error("参数不能为空");
        } else {
            if (type.equals("all")) {
                Map<String, Object> map = new HashMap<>();
             /*   map.put("banner", "轮播图集合");
                map.put("album", "专辑集合");
                map.put("article", "文章集合");
                return map;*/
                map.put("banner", bannerService.queryAllImg());
                map.put("album", albumService.queryAllAlbum());
                map.put("article", "文章集合");
                return map;
            } else if (type.equals("wen")) {
                Map<String, Object> map = new HashMap<>();
                map.put("album", albumService.queryAllAlbum());
                return map;
            } else {
                if (sub_type != null) {
                    if (sub_type.equals("ssyj")) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("album", "上师文章集合");
                        return map;
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("album", "其他上师文章集合");
                        return map;
                    }

                } else {
                    return new Error("思的类型不能为空");
                }
            }
        }

    }

    /**
     * 下面的方法是问的详情接口
     * 需要的参数专辑的id用来显示它所有的章节
     * 需要用户uid来显示用户下载的哪些音频的状态
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object albumDetail(Integer id, Integer uid) {
        if (id != null && uid != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("album", "专辑详情");
            map.put("chapter", "章节集合");
            return map;
        } else {
            return new Error("专辑id和用户id都不能为空");
        }
    }

    /**
     * 这是一个用来用来登陆的接口文档
     * 登陆的方式可以进行选择 1、密码登陆 2、短信验证码登陆
     * 所以需要的参数用户手机号 phone  password  code type
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object login(String phone, String password, String code, String type) {
        //是否为空可以直接在前端判断好
        if (phone != null) {
            if (type.equals("password")) {
                User user = userService.getOneUser(phone);
                if (user != null) {
                    String pwd = DigestUtils.md5Hex(password + user.getSalt());
                    if (pwd.equals(user.getPassword())) {
                        return "rigth";
                    } else {
                        return "error";
                    }

                } else {
                    return new Error("用户不存在");
                }
            } else {
                User user = userService.getOneUser(phone);
                String sign = (String) verifyPhoneCode(null, phone, code);
                if (sign.equals("success")) {
                    if (user != null) {
                        return user;

                    } else {
                        return new Error("该用户不存在");

                    }

                } else {
                    return new Error("验证码错误");
                }
            }
        } else {
            return new Error("账号不能为空");
        }

    }

    /**
     * 这个是用户注册的接口
     * 需要的参数 phone   MD5加密后的密码
     * 需要用到手机发送验证码，当发送完验证码后 此时用户已经入库，
     * 只是没有输入正确验证码时候处于一种无法正常使用的状态，
     * 此时要设置一个用户使用的时间限制，当多长时间处于不能用的装态时候就进行删除
     * 然后还可以在用这个手机号在进行注册，而且，验证码页由一定的时效性，多长时间过期
     * 一般都是存在于redix当中，
     */
    public Object regist(User user) {
        //是否为空前台判断  这里进行判断只是更加安全
        if (user.getPhone() == null || user.getPassword() == null) {
            return new Error("关键信息不能为空");
        } else {
            User user1 = userService.getOneUser(user.getPhone());
            if (user1 != null) {
                return new Error("该用户已经存在");
            } else {
                String salt = UUID.randomUUID().toString().replaceAll("-", "");
                user.setSalt(salt);
                String password = DigestUtils.md5Hex(user.getPassword() + salt);
                user.setPassword(password);
                userService.insert(user);
                return null;
            }

        }
    }

    /**
     * 修改个人信息的接口
     * 需要一个用户象将信息进行覆盖
     */
    public Object update(User user) {
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String pwd = DigestUtils.md5Hex(salt + user.getPassword());
        user.setPassword(pwd);
        Object update = userService.update(user);
        if (update.equals("fail")) {
            return new Error("修改失败");
        } else {
            return user;
        }
    }

    /**
     * 这个方法是获取手机短信验证码的
     */
    public void phoneCode(HttpSession session, String phone) throws ClientException {
        Random random = new Random();
        Integer code = random.nextInt(9999 - 1000 + 1) + 1000;
        String phoneCode = code.toString();
        session.setAttribute("phoneCode", phoneCode);
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defau	ltReadTimeout", "10000");
//初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//替换成你的AK
        final String accessKeyId = "LTAIFBm4MggRQCE6";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "QvZuybTABR8Xmrkt2QH3cmex5Nqs2Q";//你的accessKeySecret，参考本文档步骤2
//初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("何腾飞");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_141606919");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"phoneCode\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
//请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//请求成功
        }
    }

    /**
     * 短信验证码验证的接口
     * 需要手机号   phone      验证码code
     */

    public String verifyPhoneCode(HttpSession session, String phone, String code) {
        String phoneCode = (String) session.getAttribute("phoneCode");
        if (code.equals(phoneCode)) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 获取会员列表【 就是获取用户id的好友】
     * 随机查找除了本用户的其他用户
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Object getFriends(Integer id) {
        if (id != null) {
            List<User> userList = userService.getAllUser(id);
            return userList;
        } else {
            return new Error("查找失败");
        }
    }
}
